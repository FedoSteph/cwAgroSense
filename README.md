🌾 AgroSense

A Java desktop application for managing farmers, buyers, seasonal crops and field inspections in an agricultural supply network.

📖 Overview

AgroSense (cwAgroSense) is a coursework project that digitises the day-to-day records of an agricultural operation. An administrator signs in, registers farmer and buyer accounts, records what each farmer has planted and when, logs crop-health inspections, and produces printable reports, all from a single Swing dashboard backed by a MySQL database.

✨ Features
Area	What it does
Authentication	Email and password login against the user table.
Account management	Create, view, update and delete users with the roles ADMIN, FARMER and BUYER. Farmer accounts capture contact number and location. Buyer accounts capture company name.
Seasonal crop management	Record a farmer's crop, planting date, expected yield and status (Planted, Growing, Harvested).
Inspection management	Log an inspection against a seasonal crop with a date, health status (Good, Average, Poor) and remarks.
Reporting	Generate Farmer, Buyer and Seasonal Crop reports in the JasperReports viewer.

🏗️ Architecture

The project follows a layered structure, with the data access objects (DAOs) isolating all SQL from the UI.

cwAgroSense/
├── src/
│   ├── ui/        # Swing frames (Login, Dashboard, AddUser, ViewUsers, SeasonalCrop, Inspection)
│   ├── dao/       # Data access objects: User, Farmer, Buyer, Crop, SeasonalCrop, Inspection
│   ├── model/     # Entities: User, Farmer, Buyer, Crop, SeasonalCrop, Inspection
│   └── util/      # DBConnection (JDBC) and TestDB (connection check)
├── reports/       # JasperReports templates: farmers, buyers, Users (seasonal crops)
├── lib/           # Third-party JARs
├── nbproject/     # NetBeans project configuration
├── build.xml      # Ant build script
└── dist/          # Built cwAgroSense.jar
Data model

Farmer and Buyer extend User. Each has its own table that references user.uid.

user (uid, name, email, password, role)
 ├── farmer (fid, uid, contact_no, location)
 └── buyer  (bid, uid, company_name)

crop (cid, cname, season)

seasonal_crop (scid, fid, cid, planting_date, expected_yield, status)
 └── inspection (iid, scid, inspection_date, result, remarks)

The repository does not include a SQL script, so this schema is inferred from the DAO queries. See Database setup.

🧰 Tech stack
  Language: Java (project source level 24)
  UI: Java Swing, designed with the NetBeans GUI Builder (.form files)
  Database: MySQL, accessed through JDBC (mysql-connector-j 9.5.0)
  Reporting: JasperReports 7.0.3, with Jackson, Commons and Woodstox as supporting libraries
  Build: Apache Ant through NetBeans

🚀 Getting started
Prerequisites
  JDK 24 or later (the project source level is set to 24)
  MySQL Server 8.x
  Apache NetBeans, which is recommended because the UI is built with its GUI Builder

1. Get the code
bash
  git clone https://github.com/<your-username>/cwAgroSense.git
  cd cwAgroSense

3. 🗄️ Database setup

Create a database named cwagrosense. The following schema matches what the code queries. Adjust the column types as needed.

sql
CREATE DATABASE cwagrosense;
USE cwagrosense;

CREATE TABLE user (
  uid      INT PRIMARY KEY,
  name     VARCHAR(100) NOT NULL,
  email    VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role     ENUM('ADMIN','FARMER','BUYER') NOT NULL
);

CREATE TABLE farmer (
  fid        INT AUTO_INCREMENT PRIMARY KEY,
  uid        INT NOT NULL,
  contact_no VARCHAR(20),
  location   VARCHAR(100),
  FOREIGN KEY (uid) REFERENCES user(uid) ON DELETE CASCADE
);

CREATE TABLE buyer (
  bid          INT AUTO_INCREMENT PRIMARY KEY,
  uid          INT NOT NULL,
  company_name VARCHAR(100),
  FOREIGN KEY (uid) REFERENCES user(uid) ON DELETE CASCADE
);

CREATE TABLE crop (
  cid    INT AUTO_INCREMENT PRIMARY KEY,
  cname  VARCHAR(100) NOT NULL,
  season VARCHAR(50)
);

CREATE TABLE seasonal_crop (
  scid           INT AUTO_INCREMENT PRIMARY KEY,
  fid            INT NOT NULL,
  cid            INT NOT NULL,
  planting_date  DATE,
  expected_yield INT,
  status         VARCHAR(20),
  FOREIGN KEY (fid) REFERENCES farmer(fid),
  FOREIGN KEY (cid) REFERENCES crop(cid)
);

CREATE TABLE inspection (
  iid             INT AUTO_INCREMENT PRIMARY KEY,
  scid            INT NOT NULL,
  inspection_date DATE,
  result          VARCHAR(20),
  remarks         VARCHAR(255),
  FOREIGN KEY (scid) REFERENCES seasonal_crop(scid)
);

-- Seed data: a first admin account and a few crops
INSERT INTO user (uid, name, email, password, role)
VALUES (1, 'Admin', 'admin@agrosense.com', 'admin123', 'ADMIN');

INSERT INTO crop (cname, season) VALUES
  ('Paddy', 'Maha'), ('Tomato', 'Yala'), ('Carrot', 'Maha');
  
3. ⚙️ Configure the connection

Edit src/util/DBConnection.java to match your MySQL credentials:

java
return DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/cwagrosense",
    "root",   // username
    ""        // password
);

Run util.TestDB to confirm the connection works.

4. 📊 Configure report paths

The three report buttons in DashboardFrame.java currently load their templates from an absolute path on the original developer's machine:

java
String reportpath = "C:\\Users\\Ameesha\\JaspersoftWorkspace\\MyReports\\farmers.jrxml";

Change each path to point at this repository's reports/ folder, for example reports/farmers.jrxml, or the report buttons will fail on your machine.

5. ▶️ Run

With NetBeans: open the project, then choose Run → Run Project. The main class is ui.LoginFrameNew.

With Ant:

bash
  ant clean jar
  java -jar dist/cwAgroSense.jar

With the prebuilt JAR: dist/cwAgroSense.jar expects its dependencies in dist/lib/. Before running it, copy the remaining JARs from lib/ (JasperReports and the others) into dist/lib/.

Sign in with the admin account you seeded in step 2.

🖥️ Usage
  1.Login. Enter your email and password.
  2.Dashboard. Choose a module:
    Add Accounts registers a new Admin, Farmer or Buyer. Fields change with the selected role.
    Account Management views, edits or deletes users.
    Seasonal Crop Management records what a farmer has planted.
    Inspection Management logs a health inspection for a seasonal crop.
    Reports (View) opens the Farmer, Seasonal Crop or Buyer report.

📁 Reports
  Template	Dashboard label	Source table
  reports/farmers.jrxml	--> Farmer Report	--> farmer
  reports/Users.jrxml -->	Seasonal Crop Report	--> seasonal_crop
  reports/buyers.jrxml	--> Buyer Report	--> buyer

⚠️ Known limitations and roadmap

This is a coursework project. The items below are the main things to address before any real-world use.

  [ ] Passwords are stored and compared in plain text. Switch to a salted hash such as bcrypt or Argon2.
  [ ] Database credentials are hard-coded, and root is used with an empty password. Move them to a config file or environment variables.
  [ ] Report paths are hard-coded to a local Windows folder. Load templates relative to the project.
  [ ] Role-based access is not enforced. The dashboard is the same for every role that can sign in.
  [ ] Some DAO methods do not close their connections. Use try-with-resources throughout.
  [ ] No SQL schema or migration script ships with the repository. The schema above is inferred.
  [ ] Build artefacts (build/, dist/, nbproject/private/) are committed and should be added to .gitignore.
  [ ] Add edit and delete for seasonal crops and inspections, plus automated tests.

👤 Author
Steph Fernando

📄 License

No license has been specified for this project. Add a LICENSE file, such as MIT, before sharing or reusing it publicly.
