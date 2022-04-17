# nomre.bakcell.com-backend
Develop backend side of https://nomre.bakcell.com site where customers can search phone numbers (hereinafter MSISDN) according to specific pattern and reserve it.

### Steps to compile and run project on local machine:

1. `git clone https://github.com/JavidanAlizada/nomre.bakcell.com-backend`
2. `cd nomre.bakcell.com-backend/`
3. `docker-compose up --build -d` **NOTE** Ensure Docker service is running
4. `mvn clean && mvn install && mvn spring-boot:run`

**NOTE**
1. _Why spring boot application wasn't dockerized and using mvn command manually raise application?_ \
**Actually Dockerfile of 
app has been created and added to docker-compose by commented .  Although I tried to connect app with elasticsearch service by using `depends_on` , `links`  and over the 
common `network`, but it throws connection error when docker try to start app service yet.** 
2. **Downloading a larger data set (MSISDN and CATEGORY column csv file) can cause the program to freeze, and the server 
   to respond late. I think the loading process should be optimized, and the errors associated with loading a larger data
   set should be corrected, but it will take at least an extra week to configure correct property settings of the elasticsearch.
   For now, you can use this tested csv file https://drive.google.com/file/d/1MB777GDGUSYyyQgvo8vcPIgOeHEEW2vB/view?usp=sharing 
   which is part of a large database.**
   
After 3. step docker-compose run elasticsearch service and create container \
After 4. step project is running and connects to elastic-search database

# The used technologies are below:

**Spring Boot** -> _Application Context and enterprise web app_\
**MapStruct** _->_ _For_ mapping\
**Elasticsearch** -> _For storing large dataset and searching quickly by index_\
**Scheduler** -> Job applied for controlling reservations exist or not which created date is higher than 5hours

### Endpoint urls with HTTP Methods:

**POST** : **api/v1/upload** : Upload csv file which consists of two columns: MSISDN and CATEGORY \
**GET** : **api/v1/freemsisdn-nomre** :  Get all MSISDN(phone numbers) from Elasticsearch database \
**DELETE** : **api/v1/freemsisdn-nomre** : Delete all MSISDN(phone numbers) from Elasticsearch database \
**GET** : **api/v1/freemsisdn-nomre/search**  : Search MSISDN(phone numbers) by query from Elasticsearch database \
**POST** : **api/v1/freemsisdn-nomre/reserve** : Reserve MSISDN(phone numbers) and create Reserve document at Elasticsearch database \
**GET** : **api/v1/freemsisdn-nomre/reservations** : Get all Reservations from Elasticsearch database \
**DELETE** : **api/v1/freemsisdn-nomre/reservations** : Delete all Reservations(phone numbers) from Elasticsearch database \
