# controlplane

## Introduction
The controlplane project contains all services for performing different operations with tasks management and scheduler.

## Run Function in Local
https://github.com/GoogleCloudPlatform/functions-framework-java

## Getting Started
## 1. Configure Database
Install docker and from docker hub pull one of the following supported databases:

### 1.1 mssql-server
Pull mssql-server image (server:2019-latest), start mssql-server container and test the connection with any sql client

Pull mssql-server image from Docker Hub
```
docker pull mcr.microsoft.com/mssql/server:2019-latest
```
Run a container
```
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=yourStrong(!)Password" -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest
``` 
Connect to the MSSQL-Server instance and check connectivity
```
docker exec -it <container_id|container_name> /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P <your_password>
```  
Download the project from the GitHub repo, get a copy from the src/main/resources/application.properties.template for local use and put in /src/main/resources.

In the application.properties modify these properties as needed to match your sqlserver configuration:
``` 
spring.datasource.url=jdbc:sqlserver://localhost:1433;database=<your_database_name>;
spring.datasource.username=SA
spring.datasource.password=<password>
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
```

### 1.2 postgres
Pull postgres image (postgres:latest), start postgres container and test the connection with any pgsql client

Pull postgres image from Docker Hub
```
docker pull postgres:latest
podman pull postgres:latest
```
Run a container
```
docker run --name some-postgres -p 5432:5432 -e POSTGRES_DB=controlplane_db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=mysecretpassword -d postgres
podman run --name some-postgres -p 5432:5432 -e POSTGRES_DB=controlplane_db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=mysecretpassword -d postgres
``` 
Connect to the postgres instance and check connectivity
```
docker run -it --rm postgres:latest psql -h <host> -U <username> -d <database_name>
docker run -it --rm postgres:latest psql -h localhost -U admin -d controlplane_db
podman run -it --rm postgres:latest psql -h localhost -U admin -d controlplane_db
```  
Download the project from the GitHub repo, get a copy from the src/main/resources/application.properties.template for local use and put in /src/main/resources.

In the application.properties modify these properties as needed to match your sqlserver configuration:
``` 
spring.datasource.url=jdbc:postgresql://localhost:5432/controlplane_db
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
```

Do not upload the local version of the application.properties to the repository. Add to .gitignore file.

## 2. Configure Cache
Install docker and from docker hub pull one of the following supported cache tools:

### 2.1 redis
Pull redis image (redis:latest), start redis container and test the connection with any redis client

Pull redis image from Docker Hub
```
docker pull redis:latest
docker pull redis:7.4.0-alpine
podman pull redis:latest
podman pull redis:7.4.0-alpine
```
Run a container
```
docker run --name some-redis -d redis
podman run --name some-redis -d redis
``` 
Connect to the redis instance and check connectivity
```
docker run -it --network some-network --rm redis redis-cli -h some-redis
```  
Download the project from the GitHub repo, get a copy from the src/main/resources/application.properties.template for local use and put in /src/main/resources.

In the application.properties modify these properties as needed to match your sqlserver configuration:
``` 
spring.datasource.url=jdbc:sqlserver://localhost:1433;database=<your_database_name>;
spring.datasource.username=SA
spring.datasource.password=<password>
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
```


### Build and Test
For building and running test:
```
./gradlew test build
```

For starting the app with the debug enabled:
```
./gradlew bootRun -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5050"
```

For running sonarqube (first install sonarqube in docker, and generate a token):
```
./gradlew clean test sonarqube -Dsonar.projectKey=onebank_controlplane -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<your_token>
```