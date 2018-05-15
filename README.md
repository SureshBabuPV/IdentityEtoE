How to Run:

1. Update Maven dependency
2. Run as Maven Test
3. Reports are generated in reports folder

How this test work:

    1. This test reads data from all xls files - present in testdata folder
    2. And converts xls data in to two dimensional array of hash table to data provider
   3.  hash table is passed as a parameter to testng test.

IDENTITY E2E – Test 4 - Java Exercise

Part 1:

Write a Service layer bean to do the following:

1.	Scan configured directory in file system which will return this information --> filename, file mime type, file size, file extension

2.	Use a directory containing a reasonably large number of files, minimum 10.

3.	Provide a way to retrieve certain supported mime type files: configure excel and csv are supported currently

Part 2:

Write a selenium/cucumber framework to do the following:

1.	Use the above service layer bean to get supported files (excel or csv are supported, from input directory)

2.	Go through the file and read vehicle registration details in the file.

3.	Open webpage : https://www.gov.uk/get-vehicle-information-from-dvla and go through all vehicles from excel/csv file.

4.	On the Vehicle details page assert the details (Make/Color) match with expected output in excel/csv file.




Key Requirements: 

•	Write appropriate Junit tests ( Used TestNg)
•	Use of design patterns where appropriate.
•	Contain clear and precise logging/comments. 
•	Provide screenshot of output when you run Part 2 test locally.
•	Update all code / documentation to your GitHub account

