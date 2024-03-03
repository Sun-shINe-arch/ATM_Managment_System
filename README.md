I am using my database (MySQL) for saving my banking information.
You can also create a database and then connect it to vsCode using 'jar' file of the database.
In my code i have created a table in MySQL using syntax 
[CREATE TABLE CLIENT(Account_Number(VARCHAR 10) PRIMARY KEY,Pin_Number INT NOT NULL,Name VARCHAR(30),Balance FLOAT)].
Then this way you can use your database in vscode : 
     private static final String url = "jdbc:mysql://localhost:3306/atm";
     private static final String username = "****";
     private static final String password = "***********";
