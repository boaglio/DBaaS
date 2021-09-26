# DataBase as a Service

DataBase as a Service

## Why

This is an API REST based app ready for you to fork and code.
It is an easy and fast way to create queries as a service for databases, specially the legacy ones. 

## Features

* Java 11 based
* Spring Boot 2.5 based
* Swagger ready 
* Simple API
* Simple Unit test
* Simple Home Page
* ready to use
* Postman collection available

## Shots

### Homepage

![Home](doc/api1.png)

### Swagger

![Swagger](doc/api2.png)

### Postman

![Postman](doc/postman.png)

## Using

Using service:

```
$ curl -X POST localhost:8080/api/q -H 'Content-type:application/json' -d '{ "service": "s1","method":"m1","version": "v1" }'
 
[{"VERSION()":"10.6.4-MariaDB"}]

```