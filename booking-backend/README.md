## Booking-backend


http://localhost:8080/swagger-ui/index.html
http://localhost:9001/swagger-ui/index.html

### Needed for development:
- java 21+
- run in root: ./mvnw clean install

### Running
~~~bash
./mvnw spring-boot:build-image -DskipTests &&
docker run -d -p 8080:8080 booking-backend-sten:latest
~~~

#### Configure 
Configure data in workshops.json
When running with docker use http://<host-ip> instead of http://localhost

