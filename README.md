# Film Query Project

## Description
Film Query Project is a command-line, menu-based application that retrieves and displays film data from a database. A top priority of this application is to protect data as well as prevent SQL injection vulnerabilities.


## Goals or User Stories
User Story 1
The user is presented with a menu in which they can choose to:

Look up a film by its id.
Look up a film by a search keyword.
Exit the application.
User Story 2
If the user looks up a film by id, they are prompted to enter the film id. If the film is not found, they see a message saying so. If the film is found, its title, year, rating, and description are displayed.

User Story 3
If the user looks up a film by search keyword, they are prompted to enter it. If no matching films are found, they see a message saying so. Otherwise, they see a list of films for which the search term was found anywhere in the title or description, with each film displayed exactly as it is for User Story 2.

User Story 4
When a film is displayed, its language (English,Japanese, etc.) is also displayed.

User Story 5
When a film is displayed, the list of actors in its cast is displayed along with the title, year, rating, and description.

Stretch Goals
Goal 1
When a film is displayed, the user can choose from a submenu whether to:

Return to the main menu.
View all film details.
If they choose to view all details, they will see all column values of the film record they chose.

## Technologies Used
* Java Database Connectivity(JDBC) API
* Prepared Statements
* Binding Variables
* Maven
* MAMP
* SQL Queries
* Classes
* Encapsulation
* Object Oriented Programing
* Inheritance
* Conditionals
* Collections
* Switch menus
* Atom
* Terminal / Command Line
* Eclipse

## Lessons learned

## SQL Injection is a Serious Vulnerability
With improper validation of data an attacker has the ability to submit a SQL statement that can change the logic of query. This change in the  query can allow a hacker to not only access vulnerable data but also modify and even delete data and potentially access the entire system. Prepared statements prevent SQL Injection vulnerabilities but forcing the developer to write the user-provided data and SQL command separately.

In this application the user-provided data is not embedded directly into the SQL query instead the users input data is denoted with a question-mark. This question-mark is called a binding variable that is a temporary variable that acts as a placeholder and protects our database and system.

## Sanity Checks are Necessary
This particular database has 14 tables, making it nearly impossible to know what is where without proper verification. Prior to writing any SELECT/SQL statements I found it necessary to look into the database. This process was started by SELECT-ing the database, SHOW-ing, and requesting to DESCRIBE each table. When the table is described there is a section in the table that identifies which field is the primary key as well as the foreign keys. With this data the process of identifying relationship within the database becomes streamlined. Allowing a link to be made connecting relational fields. 
