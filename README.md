# weatherreport

Hystrix demo.

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
