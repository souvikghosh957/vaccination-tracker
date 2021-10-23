package com.vaccination.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vaccination.helper.ExcelHelper;
import com.vaccination.message.ResponseMessage;
import com.vaccination.models.Area;
import com.vaccination.models.AreaDetails;
import com.vaccination.models.Family;
import com.vaccination.models.FamilyDetails;
import com.vaccination.models.PersonDetails;
import com.vaccination.models.PersonDetailsResponse;
import com.vaccination.models.VaccinationRequest;
import com.vaccination.service.VaccinationTrackerService;
import com.vaccination.util.VaccinationUtil;

@CrossOrigin("http://localhost:8081")
@RefreshScope
@RestController
@RequestMapping(value = "/vaccination")
public class VaccinationTrackerController {

	@Value("${test.message}")
	private String messageProp;

	@Autowired
	private VaccinationTrackerService vaccinationService;

	private static String TMP_DIR = System.getProperty("java.io.tmpdir") + "/";

	private static String FILE_URL = "https://github.com/souvikghosh957/vaccination-tracker/raw/master/VaccinationServeyTemplate.xlsx";

	private static String FILE_PATH = TMP_DIR + "VaccinationSurveyTemplate.xlsx";

	/**
	 * Default home page.
	 */
	@GetMapping("/home")
	public String home() {
		return "<h1> Welcome To Vaccination Tracker </h1>";
	}

	/**
	 * Using this API an administrative user can upload the excel file containing
	 * the survey records.
	 * 
	 * @param template excel file containing the local citizen's vaccination
	 *                 details.
	 * @return Upload status
	 * @author Souvik Ghosh
	 */
	@PostMapping("/uploadRecords")
	public ResponseEntity<ResponseMessage> loadRecords(@RequestParam(name = "file") MultipartFile file) {
		String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				vaccinationService.saveSurveyRecords(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	/**
	 * This rest API takes a person's any of the government ID and update the
	 * person's vaccination details. It also re-calculates family and area wise
	 * status.
	 * 
	 * @param VaccinationRequest holds a details of the newly vaccinated person.
	 * @return Response entity with message
	 * @author Souvik Ghosh
	 */
	@PutMapping("/updateVaccination")
	public ResponseEntity<ResponseMessage> updateVaccination(@RequestBody List<VaccinationRequest> vaccinationRequest) {
		String message = "";
		if (!vaccinationRequest.isEmpty()) {
			try {
				List<Integer> ids = vaccinationService.updateRecords(vaccinationRequest);
				message = "Upated Ids: " + ids;
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not update the records!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please provide non empty requests!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	/**
	 * This API takes in any or all of adharId, voterId or panId of a person's in
	 * request parameter and returns back the vaccination details.
	 * 
	 * @param adharId, voterId, panId
	 * @return PersonDetailsResponse
	 * @author Souvik Ghosh
	 */
	@GetMapping("/getPersonVaccinationDetails")
	public ResponseEntity<PersonDetailsResponse> getIdentityDetails(
			@RequestParam(name = "adharId", required = false) String adharId,
			@RequestParam(name = "voterId", required = false) String voterId,
			@RequestParam(name = "panId", required = false) String panId) {
		String message = "";
		PersonDetailsResponse personDetailsResponse = null;
		try {
			if ((adharId != null && !adharId.isEmpty()) || (voterId != null && !voterId.isEmpty())
					|| (panId != null && !panId.isEmpty())) {
				PersonDetails personDetails = vaccinationService.getPersonDetails(adharId, voterId, panId);
				// message = "Success!";
				message = messageProp;
				personDetailsResponse = new PersonDetailsResponse(personDetails, new ResponseMessage(message));
				return ResponseEntity.status(HttpStatus.OK).body(personDetailsResponse);
			} else {
				message = "Please enter any of Aadhar, Voter or Pan Id.";
				personDetailsResponse = new PersonDetailsResponse(null, new ResponseMessage(message));
				return ResponseEntity.status(HttpStatus.OK).body(personDetailsResponse);
			}
		} catch (Exception e) {
			message = "Could not create the records!. Please contact support.";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

	/**
	 * The getAreaDetails API takes in a PIN code as path variable and returns back
	 * the area's vaccination details.
	 * 
	 * @param pincode
	 * @return AreaDetails
	 * @author Souvik Ghosh
	 */
	@GetMapping("/getAreaDetails/{pincode}")
	public ResponseEntity<AreaDetails> getAreaDetails(@PathVariable(name = "pincode") String pincode) {
		String message = "";
		AreaDetails areaDetails = null;
		try {
			if (pincode != null && !pincode.isEmpty() && VaccinationUtil.isInteger(pincode)) {
				Area area = vaccinationService.getAreaDetails(pincode);
				message = messageProp;
				areaDetails = new AreaDetails(area, new ResponseMessage(message));
				return ResponseEntity.status(HttpStatus.OK).body(areaDetails);
			} else {
				message = "Please enter valid pincode";
				areaDetails = new AreaDetails(null, new ResponseMessage(message));
				return ResponseEntity.status(HttpStatus.OK).body(areaDetails);
			}
		} catch (Exception e) {
			message = "Could not retrive area details";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

	/**
	 * The getAreaDetails API takes in a family Id code as path variable and returns
	 * back the Family's vaccination details.
	 * 
	 * @param familyId
	 * @return FamilyDetails
	 * @author Souvik Ghosh
	 */
	@GetMapping("/getFamilyDetails/{familyId}")
	public ResponseEntity<FamilyDetails> getFamilyDetails(@PathVariable(name = "familyId") String familyId) {
		String message = "";
		FamilyDetails familyDetails = null;
		try {
			if (familyId != null && !familyId.isEmpty()) {
				Family family = vaccinationService.getFamilyDetails(familyId);
				message = messageProp;
				familyDetails = new FamilyDetails(family, new ResponseMessage(message));
				return ResponseEntity.status(HttpStatus.OK).body(familyDetails);
			} else {
				message = "Please enter valid familyId";
				familyDetails = new FamilyDetails(null, new ResponseMessage(message));
				return ResponseEntity.status(HttpStatus.OK).body(familyDetails);
			}
		} catch (Exception e) {
			message = "Could not retrive family details";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

	/**
	 * This API enable us to download the survey template, which will be uploaded by
	 * the administrative users to track the vaccination details.
	 * 
	 * @param httpServletResponse
	 * @author Souvik Ghosh
	 */
	@GetMapping("/downloadTemplate")
	public void downloadTemplate(HttpServletResponse httpServletResponse) {
		File file = new File(FILE_PATH);
		if (!file.exists()) {
			try {
				FileUtils.copyURLToFile(new URL(FILE_URL), new File(FILE_PATH), 10000, 10000);
				file = new File(FILE_PATH);
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		httpServletResponse.setContentType("application/octet-stream");
		httpServletResponse.setHeader("Content-Disposition",
				String.format("inline; filename = \"" + file.getName() + "\""));
		httpServletResponse.setContentLength((int) file.length());
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(is, httpServletResponse.getOutputStream());
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
