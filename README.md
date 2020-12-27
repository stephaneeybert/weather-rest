# Getting Started

### Reference Documentation

* Building and running some integration tests
```
mvn clean install -Denv="test"
```

* Building for production
```
-Denv="prod" (an empty env string is considered as prod)
```

* Running the application
```
mvn clean spring-boot:run
```

* Example requests
```
curl -i "http://localhost:8080/api/current-city-weather?unit=m&city=Stockholm"
curl -i "http://localhost:8080/api/current-city-weather?unit=m&city=Stockholm&lang=en"
curl -i "http://localhost:8080/api/current-city-weather?unit=m&city=Stockholm&lang=fr"
curl -i "http://localhost:8080/api/current-city-weather?unit=m&city=Stockholm&lang=sv"
```

* A weatherstack API request
```
curl -i "http://api.weatherstack.com/current?access_key=[YOUR_ACCESS_KEY]&unit=m&query=Stockholm"
```

### Note on the WeatherStack API

The free version of the API supports only the english language.
Note that the english language code is not part of the API list of languages
and as such it cannot be explicitly specified
so the parameter language=en is an invalid language code.
Any other language code is restricted to non free subscription API usage.
