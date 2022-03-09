# demo-project

## Overview

This app is integrated with https://docs.coincap.io api.
The application reads the 'assets' endpoint every x seconds and persists the read data with a new id and creation dates to generate historical data.
We can read last 10 or N entries and delete entries by name.
Import posman collection demo-project.postman_collection.json and try requests.

## Requirements and Run

* docker, docker-compose
* java  >= 17

```bash 
  cd projectdirectory

 ./mvnw clean install -DskipTests

  docker-compose up

```

## Tests

```bash
./mvnw clean test
```
