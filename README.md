# Phone Country Detector

## Summary
This service finds Country by Phone Number provided by user from UI or REST API.
Wikipedia [List of Country Calling Codes](https://en.wikipedia.org/wiki/List_of_country_calling_codes#Alphabetical_order) 
data loaded to local database on service start.

---

## Quick start

#### Configure Database

1. Install [PostgreSQL](https://www.postgresql.org/download/)
1. Create empty database with name pcd-default  

#### Make executable jar
```shell
gradlew bootJar
```

#### Run with minimum settings
```shell
cd /build/libs
java -jar pcd-0.0.1-SNAPSHOT.jar
```

#### Make request using WEB UI

1. Open browser
1. Open URL: http://localhost:8088
1. Enter phone number: **11165384765**
1. Press "detect country"
1. "United States, Canada" text appears 

---

## Detailed guide

#### Profiles and Environment variables 

Different application profiles supported:
* default
* dev
* test

Environment variables for application.config example:

```
SPRING_PROFILES_ACTIVE=default;
SERVER_PORT=8088;
APP_ONSTARTUP_LOAD_WIKIDATA=true;
SPRING_DATASOURCE_USERNAME=postgres;
SPRING_DATASOURCE_PASSWORD=postgres;
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/pcd-default;
```

* SPRING_PROFILES_ACTIVE - current profile
* SPRING_DATASOURCE_* - database config
* SERVER_PORT - set custom server port
* APP_ONSTARTUP_LOAD_WIKIDATA - enable/disable automatic loading data from Wiki 

#### Run with parameters
```shell
cd /build/libs
java java -jar pcd-0.0.1-SNAPSHOT.jar --SERVER_PORT=9090 --APP_ONSTARTUP_LOAD_WIKIDATA=false
```

#### Run tests  (has some issues at the moment)
Need to provide env.vars:
* APP_ONSTARTUP_LOAD_WIKIDATA=false
* SPRING_PROFILES_ACTIVE=test

```shell
gradlew test
```



#### Make request using REST

1. Install [Postman](https://www.postman.com/downloads/) or similar tool
1. Create new request 

* Request example:

```
http://localhost:{serverPort}/api/countrydetect/phonenumber/{phoneNumber}
```

```
http://localhost:8088/api/countrydetect/phonenumber/11165384765
```

* Response example (SUCCESS):
```json
{
  "countries": [
    "Canada",
    "United States"
  ],
  "hasWarning": false,
  "warningMsg": ""
}
```

Response example (WARNING):
```json
{
  "countries": [
  ],
  "hasWarning": true,
  "warningMsg": "Phone Number not entered"
}
```

## Known issues and TODO features

#### 1. NPE Not handled for wiki data loading process

* WikiCountryCallingCodesPage

#### 2. Detect Maximum Country Code length automatically on startup
 * CountryDetectService.MAX_CODE_LENGTH

#### 3. Need more user-input validation rules

* CountryDetectValidator

#### 4. Need more unit/integration tests

#### 5. Runbook howto run rests and make reports from console

#### 6. Refactor parsing strategies

#### 7. Refactor database to 2 tables: Country - hasMany - Codes
