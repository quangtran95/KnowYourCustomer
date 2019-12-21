Project: KnowYourCustomer
1. Required Enviroment
	-Java 1.8 181 64 bit
	-Maven 3.2.1 (can use a attach file)
	-MySql 5.7.22 (can use a attach file)

2. Prepare DataBase
-Create Database with name: atg
	"CREATE DATABASE databasename;"
-Create table: CUSTOMER_INFO	
	"create table CUSTOMER_INFO
	(
	  ID BIGINT	not null,
	  FIRST_NAME VARCHAR(100) DEFAULT '' not null,
		LAST_NAME VARCHAR(100) DEFAULT '' not null,
		EMAIL VARCHAR(100) DEFAULT '' not null,
		ID_NUMBER VARCHAR(100) DEFAULT '' not null,
		TELEPHONE_NUMBER VARCHAR(100) DEFAULT '' not null,
		ADDRESS VARCHAR(100) DEFAULT '' not null,
		CREATE_TIME TIMESTAMP(3) DEFAULT NOW(3),
		UPDATE_TIME TIMESTAMP(3) DEFAULT NOW(3),
		PRIMARY KEY (ID)
	) ;"
	
3. Config DataBase Connection
-Edit file: KnowYourCustomer\src\main\resources\database.properties
	jdbc.url: address of mysql server abd DB name.
	jdbc.username: connection username
	jdbc.password: connection password
	
4. Build Project: 
-Go to a root folder: ..\KnowYourCustomer
-Open cmd and type: "mvn clean package"
-Wait until the build process finish.

5. Copy Webapps folder
-Go to folder: ..\KnowYourCustomer\src\main\webapps
-Copy all .jsp, .js, .css files.
-Patse them on ..\KnowYourCustomer\target\KnowYourCustomer\

6. Start Server
-Go to a root folder: ..\KnowYourCustomer
-Open cmd and type: "mvn jetty:run-exploded"

7. Use the Project
-Open your browser
-Access link: http://localhost:8080/KnowYourCustomer/

NOTE: Excel file is stored in path %JETTY_HOME%\KYC
