# StockMarketAssignment
Solution for JP Morgan Stock Market Assignment

Details

This application uses

1> Object Oriented Programming Concepts of Java.

2> Spring's Dependency injection concept and interfaces to achieve loose coupling and make testing easier.

3> Maven as a build tool and for downloading all the dependencies required by the project like logger,spring framework etc.

4> Logger to log all information and error messages on the console.

5> Junit test cases to test all the methods.

6> Followed structured approach for separating the responsibilities like

   a) Model holds attributes associated with stocks and trades data.
   
   b) Service contains all the methods required for processing the data.
   
   c) Dao contains methods for storing and accessing the data from system memory.
   

How to use this project

The code for is built as an Eclipse project with a embedded version of Maven. To compile the code, download the folder StockMarketAssignment and 
import the project in Eclipse as a maven project. 

Alternatively, on console run the next command, working in the folder StockMarketAssignment:

 maven clean install
 
This will compile the code and will execute the unit test.

Pre-requisites

1> JDK 1.7.0

2> Maven 3.0.2

3> Eclipse Mars(optional)

