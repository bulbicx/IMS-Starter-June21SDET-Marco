# IMS

This project will allow users to add, edit, read and delete data for customers, items, orders and orders-item tables. The application will handle all possible errors, redirecting the user to choose the correct options. The project alllows to test the application using JUnit.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to first use the application for running it and testing it, We need to have Java(v1.8 onward), Eclipse and Maven installed on our computer. For this guide we will use Windows OS.

JAVA 

To check if you have the java installed on your machine and what version you are using, open the command prompt(cmd) and type:

```
java -version
```
If there is any java version installed on your computer the response will be something like java version "14.0.1". If the command is not recognized then you need to download Java from the link below.

https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html

After downloading it we need to install it. 

1. Follow the instructions and click next, all pre-compiled setting will be fine. When it is time to select the destination folder, select the C:/Program Files/Java path.

2. Now, It is time to edit our environment variables by clicking onto the windows button and searching path. It will appear "EDIT the system environment variables", click onto it. 

3. When it opens up a window you will need to click onto "Environment Variables" and it will open another window. 

4. From this screen click onto "New..." and type "JAVA_HOME" on variable name, and insert the Java path on variable value (where it is installed), which should be something like "C:\Program Files\Java\jdk-14.0.1". Click on Ok and you should be able to see the new variable created.

5. Now we need to edit our "Path" variable found on System variables, so click onto it and then click on "Edit...".

6. We need to add our JAVA_HOME variable we created by typing:

```
%JAVA_HOME%\bin. Click on OK.
```

7. To test our Java is recognized, open the cmd and type java. You should be able to see a series of command instructions

ECLIPSE

Download Eclipse from this link if you do not have it installed.

https://www.eclipse.org/downloads/

Follow the pre-compiled settings and install it.

MAVEN

1. Download Maven from the link below.

https://maven.apache.org/download.cgi

2. Install it to your machine.

3. We need to navigate to the "Environment variables" as we did before and create two new variables with the name of "M2_HOME" and "MAVEN_HOME". They both need to link to the path maven is installed.

4. Now, we need to edit our "Path" variable and add:

```
%MAVEN_HOME%\bin;
```

5. To verify we correctly installed Maven. Open the cmd and type:

```
mvn -version
```

### Installing

In order to get our envirnoment setup ready, we need to follow the below instructions.

1. Clone this repository to your local machine and open it with Eclipse.

2. After the project is loaded, you should be able to see it on the Package Explorer window placed on the left hand side.

3. Expand the folder and you will be able to see a series of folders.

![folder (4)](https://user-images.githubusercontent.com/56220535/125768558-551db3b4-40cf-40e1-97b7-96bcab196d47.png) src/main/java --> It is where our application code resides.

![folder (4)](https://user-images.githubusercontent.com/56220535/125768558-551db3b4-40cf-40e1-97b7-96bcab196d47.png) src/main/resources --> We keep the resources needed for our main java code, for example our database properties connection.

![folder (4)](https://user-images.githubusercontent.com/56220535/125768558-551db3b4-40cf-40e1-97b7-96bcab196d47.png) src/test/java --> It is where all the testing resides

![folder (4)](https://user-images.githubusercontent.com/56220535/125768558-551db3b4-40cf-40e1-97b7-96bcab196d47.png) src/test/resources --> We keep the resources needed for our test code.

4. To run the application to our console in Eclipse, right click onto src/main/java folder or just right click onto the runner.java file.


5. The application will run and it will prompt the user to choose 3 entities to play with.

```
Welcome to the Inventory Management System!
Which entity would you like to use?
CUSTOMER: Information about customers
ITEM: Individual Items
ORDER: Purchases of items
STOP: To close the application
```

Selecting one entity will redirect us to the use of the CRUD functionality for the option selected like it is displayed down below.

```
customer
```

```
What would you like to do with customer:
CREATE: To save a new entity into the database
READ_ALL: To read an entity from the database
READ_ONE: To read one entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
```

When we want to add a new customer to the database we can select:

```
create
```

Then, it will ask us the details of the new entry to be added. 

```
Please enter a first name
Javir
Please enter a surname
Polinski
Customer created
```
After an operation has been completed, we will be redirected to the entity menu and it will ask us what we would like to do with the entity. We can choose any other option and see that we will be able to read one entry by providing the id, read all entries, update one entry and delete one entry.
We are able to go back to the main menu by clicking onto
```
return
```
From the main menu we can choose another entity this time and play with it. Do not worry if you mistype a command, as the application will not break and it will actually help you choose the correct option. To quit the application from the main menu type:

```
stop
```

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
