# reloadlyapp
App for reloadly test

**Prequisite** 

 -Ensure MySql Database Server is Installed
 
 -Ensure you have a minimum of jdk 1.8 installed 
 
 -Ensure that GIT and Maven is Installed 
 
**Setting Up the Project**

The First Step is to clone the project using the command 

 git clone https://github.com/ogiolu/reloadlyapp.git
 


There are 3 microservices 

1 Notification Service

2 Transaction Service

3 Account Serivice

In addition to these 3 , there is the Eureka Service (Descovery Service ) and also the gateway.

I will give little discription of each of the service

Eureka Service 
  This service is  in the eureka-service project. It runs on Port 8761.
  
  Kindly run this service on this port. After Running all the other services above, navigate to http://localhost:8761 .This Shows all the services running 
  
  
  ![eureka](https://user-images.githubusercontent.com/17859246/111922823-680ac680-8a9c-11eb-8cb0-9f7a9b4a8a98.png)
  
  
  Gateway Service
   This is the gateway that stands in front of the micro-services. Typically it routes request to each of the microservice. It runs on Port 8004. The routing configuration can be found 
   in the application.yml 
   ![gateway](https://user-images.githubusercontent.com/17859246/111922997-4d851d00-8a9d-11eb-995f-f2862bd48821.PNG)
   
  
Notification Service
  This services send email in asynchronou pattern to a particular 
 
  

  


