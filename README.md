# nomre.bakcell.com-backend
Develop backend side of https://nomre.bakcell.com site where customers can search phone numbers (hereinafter MSISDN) according to specific pattern and reserve it.

### Steps to compile and run project on local machine:

1. `git clone https://github.com/JavidanAlizada/nomre.bakcell.com-backend`
2. `cd nomre.bakcell.com-backend/`
3. `docker-compose down && docker-compose up --build -d` **NOTE** Ensure Docker service is running
4. `mvn clean && mvn install && mvn spring-boot:run`

After 3. step docker-compose run elasticsearch service and create container \
After 4. step project is running and connects to elastic-search database

# The used technologies are below:

**Spring Boot** -> _Application Context and enterprise web app_\
**MapStruct** _->_ _For_ mapping\
**Elasticsearch** -> _For storing large dataset and searching quickly by index_\
**Scheduler** -> Job applied for controlling reservations exist or not which created date is higher than 5hours

### Endpoint urls with HTTP Methods:

**GET** : **api/v1/freemsisdn-nomre** :  Get all MSISDN(phone numbers) from Elasticsearch database \
**DELETE** : **api/v1/freemsisdn-nomre** : Delete all MSISDN(phone numbers) from Elasticsearch database \
**GET** : **api/v1/freemsisdn-nomre/search**  : Search MSISDN(phone numbers) by query from Elasticsearch database \
**POST** : **api/v1/freemsisdn-nomre/reserve** : Reserve MSISDN(phone numbers) and create Reserve document at Elasticsearch database \
**GET** : **api/v1/freemsisdn-nomre/reservations** : Get all Reservations from Elasticsearch database \
**DELETE** : **api/v1/freemsisdn-nomre/reservations** : Delete all Reservations(phone numbers) from Elasticsearch database \
