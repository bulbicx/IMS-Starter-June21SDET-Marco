# IMS

This project is an Inventory Management System or short for IMS, which will allow us to add, edit, read and delete data for customers, items, orders and orders-item tables. The application will handle all possible errors, redirecting the user to choose the correct options. The project allows to test the application using JUnit.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to first use the application for running it and testing it, We need to have **Java**(v1.8 onward), **MySql**, **Eclipse**(recommended) and **Maven** installed on our computer. Links for downloading them are provided on the **Built With** section down below.

### Installing

In order to get our envirnoment setup ready, we need to follow the below instructions.

(1) Clone this repository to your local machine and open it with Eclipse IDE.

(2) After the project is loaded, you should be able to see it on the **Package Explorer** window placed on the left hand side.

(3) Expand the project folder and you will be able to see a series of folders

* src/main/java --> It is where our application code resides.
 
* src/main/resources --> We keep the resources needed for our main java code, for example our database properties connection.

* src/test/java --> It is where all the testing resides

* src/test/resources --> We keep the resources needed for our test code.

(4) Before running the application, we need to create a database properties connection file as it is not present for security reasons. Click on src/main/resources and create a file named "db.properties" with properties extension.

(5) Inside the file insert the database connection, username and password using the down below template. Remember to modify the respective fields with your own details.

```
db.url=jdbc:mysql://localhost:3306/<database_name>?serverTimezone=UTC
db.user=username
db.password=password

```

(6) Save the file and copy it and paste it on src/test/resources, as we will need to have it for the testing as well.

(7) To construct the tables, you can use the "sql-schema.sql" file to run onto MySql. This step will create all the tables needed for our application.

(8) To run the application to our console in Eclipse, right click onto *src/main/java* folder or just right click onto the *runner.java* file.

(9) The application will run and it will prompt the user to choose 3 entities to play with.

```
Welcome to the Inventory Management System!
Which entity would you like to use?
CUSTOMER: Information about customers
ITEM: Individual Items
ORDER: Purchases of items
STOP: To close the application
```

When we choose an entity:

```
customer
```
It will redirect us to the use of the CRUD functionality for the option selected like it is displayed down below.

```
What would you like to do with customer:
CREATE: To save a new entity into the database
READ_ALL: To read an entity from the database
READ_ONE: To read one entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
```

When we want to add new data to the database we can select:

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
After an operation has been completed, we will be redirected to the entity menu and it will ask us what we would like to do with the entity. We can choose any other option and see that we will be able to read one entry by providing the id, read all entries, update and delete one entry.
We are able to go back to the main menu by typing:

```
return
```
From the main menu we can choose another entity this time and play with it. Do not worry if you mistype a command, as the application will not break and it will actually help you choose the correct option. To quit the application from the main menu type:

```
stop
```

## Running the tests

The application can be tested using JUnit, which is a dependency of Maven. Each JUnit test will tests a piece of code. You may find that a method may have different tests related to it as there are different scenario that may happens and so all of them need to be covered.

### Unit Tests 

To run the whole application, we can right click onto the project from **Package Explorer** and click on **Run As** > **JUnit Test**. It will run all the tests present in *the src/test/java* folder and produce the results. It is possible to run a single file which can be found by navigating the package structure. You will find that the tests files are named after the class they will be testing followed by **Test** keyword, as JUnit will recognize it in this way. I am going to give an example on running a JUnit test for CustomerController.

(1) Open the file from *src/test/java* > *com.qa.ims* > *controllers* > *CustomerControllerTest.java*

(2) You will find that the class is using **Mockito**, a Maven dependency which will help us on creating a dummy object, which it is needed for testing. Notice that when we run tests we do not want to be relying on some other class or method.

(3) Each method will precede the **@Test** annotation. We can test a single method or the whole class by right clicking the method or the class and then click onto **Run As** > **JUnit Test**.

```
	@Test
	public void testCreate() {
		final String F_NAME = "barry", L_NAME = "scott";
		final Customer created = new Customer(F_NAME, L_NAME);

		Mockito.when(utils.getString()).thenReturn(F_NAME, L_NAME);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
```

(4) After the test is run. We will wee a JUnit window opening up and displaying the results of the tests and if any failure have happened they will be displayed in the **Failure Trace** window

(5) We are able to run a JUnit coverage test by right click and then **Coverage As** > **JUnit Test**. With this test it will open up a window, which will show us how much the test class is covered and how much the class tested has been covered.


### Integration Tests 

There are no integration tests for this application

### And coding style tests

There are no coding styles tests for this application

## Deployment

This application is not ready for deployment.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Java](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html) - Programming Language
* [MySql](https://www.mysql.com/downloads/) - Database
* [Eclipse](https://www.eclipse.org/downloads/) - IDE
* [Git](https://git-scm.com/downloads) - Version Control
* [Jira](https://www.atlassian.com/) - Software helping managing the work

## Versioning

We use [Git](https://git-scm.com/downloads) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Marco Castellana** - *Completed work*

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thank you to my teachers Alan and Pawel for being helpful at solving some issues
* Thank you to Stack overflow which is always helpful when dealing with programming issues
* Thanks to also my QA team-mate Leaf, which we have shared some same connectivities issues and we found a solution.