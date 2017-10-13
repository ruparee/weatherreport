# weatherreport

Hystrix demo.

## Preliminaries

- Install MySQL, and run the script `src/main/resources/create-db.sql` to create the database.
- Go to [OpenWeatherMap](https://openweathermap.org/) and sign up for an API key.
- Copy the file `application.yml.sample` to `application.yml`, substituting in the values for your database configuration.
  You'll also need to provide your OpenWeatherMap API key.

## Build it

```
$ ./gradlew bootRepackage
```

## Run it

Development:

```
$ ./gradlew bootRun
```

To run the JAR:

```
$ java -cp src/main/resources -jar -Dspring.profiles.active=dev build/libs/weatherreport.jar
```

## TODO

Look into using Spring Cloud Hystrix:

- http://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/1.3.5.RELEASE/multi/multi__circuit_breaker_hystrix_clients.html
- https://spring.io/guides/gs/circuit-breaker/
- http://www.baeldung.com/spring-cloud-netflix-hystrix
