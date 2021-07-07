# technohrms-backend
# A RESTFul Web Service that implements a Human Resource Management System.

## Setup

- To setup the application simply clone the project into your intellij IDE.
- Mention your database connection url in application.properties file and run the application.

## APIs 
#### NOTE
Dont forget to include the JWT token in the Authoriation request header as Bearer <your token> 

### Auth API
1. For authentication - POST http://localhost:8081/authenticate

- #### Request Body
```` 
{
    "username":"sapx3",
    "password":"3ratcat3"
}

````
2. #### For Registration - POST http://localhost:8081/register
- #### Request Body
````
{
    "username":"admin",
    "password":"3ratcat3",
    "roles":"ROLE_ADMIN",
    "active":true,
    "employee":{
        "id":1
    }
    
}

````
### Employee Management APIs
1. #### For Adding new Employee - POST http://localhost:8081/employee-mgmt/employee
- #### Request Body
````
{
    "name":"Souvik Bhowmick",
    "department":{
        "id":1
    },
    "designation":{
        "id":1
    },
    "email":"bhowmick95@gmail.com",
    "mobile":"9153226168",
    "workType":"Intern"
    
}

````

2. #### For getting Employee list - GET http://localhost:8081/employee-mgmt/employees

3. #### For getting employee by employee id - GET http://localhost:8081/employee-mgmt/employee?id=1

4. #### For updating an employee detail - PATCH http://localhost:8081/employee-mgmt/employee/1

### Deparment Management APIs

1. #### Create new Department - POST http://localhost:8081/department

- Request body
````
{
    "name":"FINANCE",
    "description":"Department for finance related stuffs"
}

````

2. #### Get all departments - GET http://localhost:8081/departments

3. #### Update a department - PATCH http://localhost:8081/department-mgmt/department/11

### Designation Management APIs

1. #### Create a new designation - POST http://localhost:8081/designation-mgmt/designation

- Request body
````
{
    "name":"SDE-1",
    "description":"Software Development Engineer , handles coding to delivery of the product"
}

````
2. #### Get all designations - GET http://localhost:8081/designations

### Attendance Management APIs

1. #### Make attendance - POST http://localhost:8081/attendance
- Request body
````
{
    "employeeId":1,
    "date":"2021-07-07",
    "time":1624615320000
}

````
2. #### Get all attendace by date range - GET http://localhost:8081/attendances?start=2021-06-29&end=2021-06-29
3. #### Get all attendance by date range and employee - GET http://localhost:8081/attendances/employee?id=1&start=2021-06-22&end=2021-06-29

### Leave Management APIs

1. #### Leave setup for Employee - POST http://localhost:8081/leave-mgmt/leave
- Request body
````
{
    "employee":{
        "id":1
    },
    "totalLeave":11,
    "leaveName":"SL"
}

````
2. #### Create a leave request - POST http://localhost:8081/leave-mgmt/leave/employee/{employee_id}

- Request body
````
{
    "leaveName":"Annual Leave",
    "startDate":"2021-06-08",
    "endDate":"2021-06-12",
    "reason":"Weeding ceremony"
}

````
3. #### Get all leave requests - GET http://localhost:8081/leave-mgmt/leave/employee/requests
4. #### Get all leave requests by employee id - GET http://localhost:8081/leave-mgmt/leave/employee/request?id=2
5. #### Approve a leave request - PATCH http://localhost:8081/leave-mgmt/leave/employee/request
- Request body
````
{
    "id":38,
    "status":"APPROVED"
}

````
6. #### Update a leave request 
- Request body
````
{
    "startDate":"2021-06-02",
    "endDate":"2021-06-07",
    "reason":"Family vacation"
}
````

### Training Management APIs

1. #### Add new training - POST http://localhost:8081/training-mgmt/training
- Request body
````
{
    "name":"Spring Boot Master class",
    "description":"This training will help to learn springboot development"
}
````
2. #### Get all trainnigs - GET http://localhost:8081/training-mgmt/trainings
3. #### Assign a training to an employee - POST http://localhost:8081/training-mgmt/training/employee
- Request body
````
{
    "training":{
        "id":41
    },
    "employee":{
        "id":1
    },
    "startDate":"2021-06-24",
    "endDate":"2021-07-24"
}
````

4. #### Get all assigned trainings - GET http://localhost:8081/training-mgmt/training/employees/assigned
5. #### Update an assigned training - PATCH http://localhost:8081/training-mgmt/training/employee/assigned/{assigned_id}
- Request body
````
{

    "training":{
       "id":41
    },  
    "employee":{
        "id":1
    },
    "rating":9,
    "completionStatus":true,
    "startDate":"2021-06-24",
    "endDate":"2021-12-24"
}
````


