<h1>Vaccination Tracker</h1>
<h3> Problem: </h3>
<body>
Suppose your local govt. wants to track and maintain how many people in your area got vaccinated (btw "COVID vaccination") and how many left. They also wants to get the status 
of family and area wise report. Now your area head (could be a BDO) calls you as you are a well known software engineer in the area for solution.
</body>

<h3> Solution: </h3>
<body>
Well, the obvious solution as a good software engineer you would give is to build a software (vaccination-tracker) to do all the above tasks.
</body>

<h3> What it does till now? </h3>

<body>
<div>1) After first round of survey we can upload an excel file with all the required data and the application stores and maintain them all in mutiple MySQL tables.</div>
<div>2) You can call one API to retrive a person details by using any of the identity number (i.e aadhar id, voter id or pan id)</div>
<div>3) Once anyone gets vaccinated one API can update his/her vaccination count </div>
<div> 4) One cool thing it does, once you update any person's vaccination details the family and area wise details also gets updated asynchronously. Thanks to spring @Async. </div>
<div> 5) We can retrive an area's or family's vaccination details too. </div>
<div> 6) Upload and update records can only be done by an administrative user. Fetching a person, family or area details can be be done by both administrative and general users </div>
<div> 7) The individual services also can be containerized.
  
 
  

</div>
</body>

<h3> Architecture: </h3>

![Alt text](/vat-image1.png?raw=true "Vaccination Tracker Architecture")

<h3> DB Details: </h3>

![Alt text](/DB_ER_Diagram.png?raw=true "Database Schema")

<h4>
[DB Script](https://github.com/souvikghosh957/vaccination-tracker/wiki)
  </h4>
 
