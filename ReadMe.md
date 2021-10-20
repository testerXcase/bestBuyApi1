Task 1 - Best Buy API Testing
=============================

How to execute locally:
After npm is started (http://localhost:3030/ should be activated) then TestRunner can be used to trigger.

The Framework implemented based on Best Buy API which has a couple of services which are *products, stores, services,
categories* and *utilities*. 

I tried to implement reusable and readable Cucumber BDD framework based on Java programing language.
I implemented a couple of test cases based on Services the part of Best Buy API.

As **Dependencies** I used Rest Assured library, Cucumber with Java and JUnit.

With **TestRunner** class execution can be triggered manually or with maven execution can be triggered.
In **TestRunner** class I added *CucumberOptions* such as *glue*, *features*, *dryRun*, *tags* and *plugin*.
<br>*glue* indicates where step definitions are.
<br>*features* indicates where feature files are
<br>*dryRun* indicates and logs snippet methods can be not implemented.
<br>*tags* test cases can be sorted
<br>*plugin* executes runner classes and creates Cucumber HTML report.

*Step Definitions* should be collected under **stepDefs** package which has also the Hook class which can be used 
for some actions before and/or after every execution.

**utilities package** includes some important most common used methods. In this framework I did not add some
classes which has useful methods such as database methods but in the future 
methods can be added when test cases need.

**ConfigPropertyReader** class brings critical values during implementation from **config.properties** file which
can be added credentials and tokens based on QA or DEV environment. 

**features** package has *features* of the application. In this case I created some test cases related to *services*.
Test scenarios cover positive, negative and crud test cases. Based on the requirement I implemented couple of  
test cases.

Which tools used in building
===============================
- IntelliJ - IDE
- Maven (Build automation tool)
- Java
- Cucumber (BDD)
- Gherkin
- JUnit
- Rest Assured Library


