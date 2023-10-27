# Helpdesk API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

This project is an API built using **Java, Java Spring, MySQL as a database, and Spring Security for authentication control.**

The API was developed with the functionality of a service control platform, with service order opening. ADMIN users can register others
new customers, adding or removing technicians, assigning new service order to be made, monitoring the progress and fulfillment of the same,
Consult the history of service order and the record of technicians and customers.

### (In development... )

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/your-username/project-name.git
```

2. Install dependencies with Maven

3. Install ---
4. Install ---

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080




## API Endpoints
The API provides the following endpoints:

```markdown
GET /chamados - Retrieve a list of all service order.

GET /chamados/{id} - Retrieve a specific service order.

POST /chamados - Register a new service order (ADMIN access required).

PUT /chamados/{id} - Update the information of the service order specified by the id.
```

```markdown
GET /tecnicos - Retrieve a list of all employees.

GET /tecnicos/{id} - Retrieve a specific employee.

POST /tecnicos - Register a new employee in the system.

PUT /tecnicos/{id} - Update the information of the employee specified by the id.

DELETE /tecnicos - Deletes an employee.
```

```markdown
GET /clientes - Retrieve a list of all customers.

GET /clientes/{id} - Retrieve a specific customer.

POST /clientes - Register a new customer in the system.

PUT /clientes/{id} - Update the information of the customer specified by the id.

DELETE /clientes - Deletes an customer.
```

```markdown
POST /login - Login into the App
```

## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
ROLE_CLIENTE - Standard user role (Requesting).
ROLE_TECNICO - Standard user role (employee).
ROLE_ADMIN - Admin role for managing (Register new employees or clients).
```
To access protected endpoints as an ADMIN user, provide the appropriate authentication credentials in the request header.

## Database
The project utilizes [MySQL](https://www.mysql.com/) as the database. The necessary database migrations are managed using [----].





