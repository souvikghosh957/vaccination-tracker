package com.vaccination.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.vaccination.models.Area;
import com.vaccination.models.Family;
import com.vaccination.models.Person;
import com.vaccination.models.VaccinationRequest;
import com.vaccination.util.Pair;
import com.vaccination.util.Triplate;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static Triplate<List<Area>, List<Family>, List<Person>> excelToEntity(InputStream inp) {
		try {
			Workbook workbook = new XSSFWorkbook(inp);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<Person> persons = new ArrayList<Person>();
			List<Area> areas = new ArrayList<Area>();
			List<Family> familys = new ArrayList<Family>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Person person = new Person();
				Family family = new Family();
				Area area = new Area();
				Iterator<Cell> cellsInRow = currentRow.iterator();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
					case 0:
						break;
					case 1:
						family.setMemberCount((int) currentCell.getNumericCellValue());
						break;
					case 2:
						person.setName(currentCell.getStringCellValue());
						break;
					case 3:
						person.setAge((int) currentCell.getNumericCellValue());
						break;
					case 4:
						String adharId = "";
						if (currentCell.getCellType() == CellType.STRING)
							adharId = currentCell.getStringCellValue();
						else if (currentCell.getCellType() == CellType.NUMERIC)
							adharId = String.valueOf(currentCell.getNumericCellValue());
						person.setAdharId(adharId);
						break;
					case 5:
						String voterId = "";
						if (currentCell.getCellType() == CellType.STRING)
							voterId = currentCell.getStringCellValue();
						else if (currentCell.getCellType() == CellType.NUMERIC)
							voterId = String.valueOf(currentCell.getNumericCellValue());
						person.setVoterId(voterId);
						break;
					case 6:
						String panId = "";
						if (currentCell.getCellType() == CellType.STRING)
							panId = currentCell.getStringCellValue();
						else if (currentCell.getCellType() == CellType.NUMERIC)
							panId = String.valueOf(currentCell.getNumericCellValue());
						person.setPanId(panId);
						break;
					case 7:
						person.setVaccinCount((int) currentCell.getNumericCellValue());
						break;
					case 8:
						String familyId = "";
						if (currentCell.getCellType() == CellType.STRING)
							familyId = currentCell.getStringCellValue();
						else if (currentCell.getCellType() == CellType.NUMERIC)
							familyId = String.valueOf((int) currentCell.getNumericCellValue());
						person.setFamilyId(familyId);
						family.setFamilyId(familyId);
						break;
					case 9:
						area.setPinCode((int) currentCell.getNumericCellValue());
						person.setPinCode((int) currentCell.getNumericCellValue());
						family.setPinCode((int) currentCell.getNumericCellValue());
						break;
					case 10:
						area.setAreaName(currentCell.getStringCellValue());
						break;
					case 11:
						area.setDistrict(currentCell.getStringCellValue());
						break;
					case 12:
						area.setState(currentCell.getStringCellValue());
						break;
					default:
						break;
					}
					cellIdx++;
				}
				areas.add(area);
				familys.add(family);
				persons.add(person);

			}
			inp.close();
			workbook.close();
			return new Triplate<List<Area>, List<Family>, List<Person>>(areas, familys, persons);
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

	public static List<Person> filterOutExistingRecords(List<Person> existingPersons, List<Person> newPersons) {

		Triplate<Set<String>, Set<String>, Set<String>> extractedIds = extractIds(existingPersons);

		Set<String> existingAdharId = extractedIds.first;
		Set<String> existingPanId = extractedIds.second;
		Set<String> existingVoterId = extractedIds.third;

		List<Person> lstPersonToInsert = newPersons
				.stream().filter(np -> (!existingAdharId.contains(np.getAdharId())
						&& !existingPanId.contains(np.getPanId()) && !existingVoterId.contains(np.getVoterId())))
				.collect(Collectors.toList());

		return lstPersonToInsert;

	}

	public static Triplate<Set<String>, Set<String>, Set<String>> extractIds(List<Person> existingPersons) {
		Set<String> existingAdharId = new HashSet<String>();
		Set<String> existingPanId = new HashSet<String>();
		Set<String> existingVoterId = new HashSet<String>();
		for (Person person : existingPersons) {
			if (!person.getAdharId().trim().equals("NA")) {
				existingAdharId.add(person.getAdharId());
			}

			if (!person.getPanId().trim().equals("NA")) {
				existingPanId.add(person.getPanId());
			}

			if (!person.getVoterId().trim().equals("NA")) {
				existingVoterId.add(person.getVoterId());
			}
		}
		return new Triplate<Set<String>, Set<String>, Set<String>>(existingAdharId, existingPanId, existingVoterId);
	}
	
	public static List<Pair<Triplate<String, String, String>, String>> getRequestTriplate(
			List<VaccinationRequest> vaccinationRequest) {
		List<Pair<Triplate<String, String, String>, String>> trpLst = new ArrayList<Pair<Triplate<String, String, String>, String>>();
		for (VaccinationRequest vr : vaccinationRequest) {
			String adharId = null;
			String panId = null;
			String voterId = null;

			if (!vr.getAdharId().trim().equals("NA")) {
				adharId = vr.getAdharId();
			}

			if (!vr.getPanId().trim().equals("NA")) {
				panId = vr.getPanId();
			}

			if (!vr.getVoterId().trim().equals("NA")) {
				voterId = vr.getVoterId();
			}

			trpLst.add(new Pair<Triplate<String, String, String>, String>(
					new Triplate<String, String, String>(adharId, panId, voterId), String.valueOf(vr.getVaccNo())));
		}

		return trpLst;

	}

}
