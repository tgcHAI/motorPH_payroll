# MotorPH Payroll System

A comprehensive Java-based payroll management system with role-based access control, designed for MotorPH company.

## Overview

MotorPH Payroll System is a desktop application built with Java Swing that manages employee payroll, attendance, and leave requests. The system features multiple user roles with different access levels and permissions.

## System Architecture

### Technology Stack
- **Language**: Java 21
- **Build Tool**: Maven
- **GUI Framework**: Java Swing
- **Database**: CSV files (for simplicity and portability)
- **Security**: BCrypt password hashing
- **Layout Manager**: AbsoluteLayout (for GUI positioning)

### Project Structure
```
motorPHpayrollsystem/
├── src/main/java/
│   ├── OOP/           # Core business logic and models
│   ├── GUI/           # User interface components
│   └── CSV/           # Data files
├── lib/               # External libraries
└── target/            # Compiled output
```

## Core Classes (OOP Package)

### User (Abstract Base Class)
Defines the common interface for all user types with email, password, and role properties.

### SystemIT (Extends User)
Provides shared authentication functionality including:
- User login validation
- Role-based portal redirection
- Employee data retrieval
- BCrypt password verification

### Employee (Extends User)
Represents employee data with comprehensive fields:
- Personal information (name, birthday, contact)
- Employment details (employee number, status)
- Financial data (salary, allowances, rates)
- Government contribution numbers (SSS, Pag-IBIG, PhilHealth, TIN)

### Admin (Extends SystemIT)
Provides administrative functionality:
- Employee data management
- JTable population and updates
- CSV data handling with proper formatting
- Duplicate checking for employee numbers

### Finance (Extends User)
Handles financial data management:
- Employee salary and contribution data
- Financial calculations and validations
- Data loading from CSV files

### HR (Extends User)
Manages human resources functions:
- Leave request approval/rejection
- Employee status management

### Utility Classes
- **HashUtil**: BCrypt password hashing and verification
- **InputValidator**: Form input validation
- **SessionTimeoutManager**: GUI session timeout management
- **ValidationRule**: Validation rule definitions

## GUI Components

### LogIn (Main Entry Point)
- User authentication interface
- Role-based portal redirection
- Password visibility toggle
- Window dragging functionality

### Role-Specific Portals
- **AdminPortal**: Administrative functions
- **HRPortal**: HR management
- **FinancePortal**: Financial operations
- **EmployeePortal**: Employee self-service

### Support Windows
- **ViewPortal**: Data viewing interface
- **LeaveReqPortal**: Leave request submission
- **PayslipPopup**: Payslip display

## Data Management

### CSV Data Structure
The system uses CSV files for data storage with the following structure:

#### EmpData.csv (Employee Data)
```
#,Last Name,First Name,SSS #,PhilHealth #,TIN #,Pag IBIG #,Birthday,Address,Phone Number,Status,Position,Immediate Supervisor,Basic Salary,Rice Subsidy,Phone Allowance,Clothing Allowance,Gross Semi-Monthly Rate,Hourly Rate,Email,Password
```

#### Login Files
- **AdminLogin.csv**: Admin credentials
- **HRLogin.csv**: HR credentials  
- **FinanceLogin.csv**: Finance credentials

### Data Features
- BCrypt password hashing for security
- Proper CSV formatting with quotes
- Data validation and error handling
- Duplicate checking for employee numbers

## Security Features

### Authentication
- Role-based access control
- BCrypt password hashing (12 rounds)
- Secure password verification
- Session timeout management

### Data Protection
- Encrypted password storage
- Input validation
- Secure data handling practices

## Key Features

### Role-Based Access Control
- **Admin**: Full system access, employee management
- **HR**: Leave management, employee status
- **Finance**: Payroll processing, financial data
- **Employee**: Self-service, personal information

### Employee Management
- Comprehensive employee data storage
- Easy data updates and modifications
- Duplicate checking and validation
- CSV import/export functionality

### Payroll Processing
- Salary calculations
- Allowance management
- Government contribution tracking
- Payslip generation

### Leave Management
- Leave request submission
- Approval workflow
- Status tracking

## Installation and Setup

### Prerequisites
- Java 21 JDK
- Maven build tool

### Build Instructions
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.mycompany.motorphpayrollsystem.MotorPHpayrollsystem"
```

### Dependencies
- **AbsoluteLayout**: GUI layout management
- **jbcrypt**: Password hashing library

## Usage

1. Run the application using Maven
2. Log in with appropriate credentials
3. Access role-specific features
4. Manage employee data and payroll

## File Structure Details

### Core Business Logic
- **User.java**: Base user class
- **SystemIT.java**: Authentication and system management
- **Employee.java**: Employee data model
- **Admin.java**: Administrative functions
- **Finance.java**: Financial operations
- **HR.java**: HR management

### GUI Components
- **LogIn.java**: Main login interface
- **AdminPortal.java**: Admin dashboard
- **HRPortal.java**: HR management interface
- **FinancePortal.java**: Finance operations
- **EmployeePortal.java**: Employee self-service

### Utility Classes
- **HashUtil.java**: Security utilities
- **InputValidator.java**: Form validation
- **SessionTimeoutManager.java**: Session management
- **ValidationRule.java**: Validation definitions

## Data Files
- **EmpData.csv**: Employee information
- **AdminLogin.csv**: Admin credentials
- **HRLogin.csv**: HR credentials
- **FinanceLogin.csv**: Finance credentials
- **Leaves.csv**: Leave records
- **AttendanceRecords**: Attendance data

## Development Notes

- **I have forgotten most on what I did but I think I can still remember them**
- **The development of this started on Comp. Prog. 2, then brought in to Obj. Oriented Programming. Then it was brought into other courses until now**
- **CP2 gave it the design and basic functions, OOP gave it more advanced features, IAS1-2 gave it the CSV hashing, timeout session, input validator, and the QR code function but it was not forked to the main branch.**
- **Much to the headache and tears, I say that this application, for learning purposes, has given me much insight into my career as a programmer**
- **I have learned much, and will learn much more, but I am going to be so fr atp, I am NOT built for this, not yet at least.**
- **Yeah, this also servers as a document I guess, because I really didn't make one and I had deepseek help out on it**

### Design Patterns
- **Inheritance**: Core class hierarchy
- **Polymorphism**: Role-based behavior
- **Singleton**: Session timeout management
- **Utility Classes**: Helper functions

## Support
For support and questions, contact me via email.