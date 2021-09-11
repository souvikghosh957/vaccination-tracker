<h1>Vaccination Tracker</h1>
<h3> Problem: </h3>
<body>
Suppose your local govt wants to track and maintain how many people in your area got vaccinated (btw "COVID vaccination") and how many left. They also wants to get the staus 
of family and area wise report. Now your area head (could be a BDO) calls you as you are a well known software engineer in the area for solution.
</body>

<h3> Solution: </h3>
<body>
Well, the obvious solution as a good software engineer you would give is to build a software (vaccination-tracker) to do all the above tasks.
</body>

<h3> What it does till now? </h3>

<body>
1) After first round of survey we can upload an excel file with all the required a data and the application stores and maintain them all in mutiple MySQL tables.
2) You can call one API to retrive a person details by using any of the identity number (i.e aadhar id, voter id or pan id)
3) Once anyone gets vaccinated one API can update his/her vaccination count
4) One cool thing it does, once you update any person's vaccination details the family and area wise details also gets updated asynchronously. Thanks to spring @Async.

</body>
