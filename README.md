# Attainia User Activity Test Cases

## Introduction and Task

One page of the application should display a table of the values found in `users.json`.  By default this table should display all rows with a white background.  
On the same page there should be a button.  When this button is clicked all user rows with a login count of 0 should have a red background.  
When the button is clicked again all records should have a white background.

A separate page of the application should display a table of the values found in `users.json`.  
By default this table should display all rows with a white background.  On the same page there should be a button.  
When this button is clicked all user rows with a login count greater than or equal to 1 should have a green background.  
When the button is clicked again all records should have a white background.

**Task is to automate test cases using the selenium library**


## Scope

- [x] The scope of my work is to create a basic Page Object Model Framework (POM) and to make it modular enough to add enhancements to the projects.

- [x] The goal is also to cover the test scenarios for this project

## Future Scope
The future enchancements may include the following 

 - Improve the current framework by making it more modular
 - Add Data Reading and writing capabilities and make this a Hybrid Framework- Add libararies like Apache POI to read and write data
 - Add Cucumber to make it into a BDD framework 
 - Add Custom Listerners to change the Behaviour of Test Cases on Failure/Success
 - Add Selenium Grid / Sauce labs and Dockerize the tests to enable parallel testing, cloud based testing 
 - Implement design patterns like Proxy Design Patterns, Factory Design Pattern, Singleton and More.....
 - Add Object Repository and add KeyWord driven capabilities to the framework
 
 ## Tools
 
 The following tools/languages/ libraries have been used
 
 <ul type="square">
<li>Eclipse IDE for writing the code</li>
<li>Java 8 for programming </li>
<li>Selenium 3.X for automating the test cases</li>
<li>TestNG for managing the test cases</li>
<li>Log4j for logging</li>
<li>Extent Reports for creating beautiful reports</li>
<li>Maven for Dependency Management</li>
</ul>

## Installing 

Java and Eclipse can be Installed on Any Machine 

Follow this video link for a step by step instruction - [Java and Eclipse instalaltion for Selenium](https://www.youtube.com/watch?v=lLKXeuRyyfY)


## Test Scenarios and Description
| ID |Test Scenario | Description
|---|------------- | -------------
| 1 |Verify the Table and Links  | When the page loads the table and the hyper link should be visible for both page 1 and Page 2
| 2 |Verify that the links are clickable  | When the user clicks on the 'Go to Page 1' or 'Go to Page 2' the link shall work and take the user 
| 3 |Verify that when logincount is 0 the rows turn red on PG1 | When the user clicks 'Highlight Rows' Button on Page 1 all rows where the login count is 0 turns Red. When the 'Highlight Rows' button is clicked againthe background of the table becomes white again.
| 4 |Verify that when logincount is greater than or equal to 1 the rows turn green on PG 2 | When the user clicks 'Highlight Rows' Button on Page 2 all rows where the login count is greater than or equal to 1 turns Green.When the 'Highlight Rows' button is clicked againthe background of the table becomes white again.
| 5 |Schema Validation | Verify the number of columns and number of rows on Pg1 and Pg2
| 6 |Color of Link changes on click| Verify that the link color changes to Red from Blue on click on both the page

### NOTE - The test cases are detailed in the code

## Design Approach

I have created a framework which will eventually look like the below diagram. 

 - Page Objects - ActivityPage 1 and ActivityPage2 classes are created .Here the PAge Objects and Events like scroll, click are captured
 - Utility - Here I have created a proprty file to read user data. Example - User may want to run Chrome/FireFox? Makes it modular
 - TestBase - Initiated Browser Objects, Drivers, ExtentReport and Loggers and also closed browser after the test cases
 - TestCase- Used to run the testcases
 -Maven - Added all dependecnies in POM.XML 
 -Reports - Created Test reports with Screenshots in Both testNG and ExtentReports
 
Below is screenshot of framework architecture- 
<a href="https://ibb.co/8mv28H7"><img src="https://i.ibb.co/FhZJ7FD/Screen-Shot2.png" alt="Screen-Shot2" border="0"></a>

Below is ExtentReport Screenshot which shows which test passed and which test failed-[go to Testoutput folder to look at ExtentReport.Html]

<a href="https://ibb.co/JCPk0FG"><img src="https://i.ibb.co/t35CkPv/Screen-Shot1.png" alt="Screen-Shot1" border="0"></a>

## Challenges

- The background color of the row is defualting to black when trying to fetch the property 'background-color' of CSS

## Workaround

- I would also fix it by using Jquery or JavaScript but since testing is the scope of this project I have restrained myself to testing the application
- Another option is to assign it to Dev team to look into the CSS/HTML file, since there may be business logic and otehr logic involved which may be affected in case I try to fix it.


## Running the tests

- Once you install the Eclipse and Java on your system pull this project on Eclipse and run it. 
- Also since this is a Django based app the app has to be initialized first as instructed by the Attainia dev team


## Deployment

- We could integrate the Maven project with Jenkins and setup the build to automate the entire process
simple_smile
# Thank You! 
Rajath Rao -The incessant learner! :smile:

