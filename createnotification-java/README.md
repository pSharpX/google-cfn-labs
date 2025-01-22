# createnotification-java

## Introduction
The createnotification function is responsible for publishing notifications (email, push, sms) for the tasks management ecosystem.

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
task-master.datasource.control-plane.url=jdbc:sqlserver://localhost:1433;database=<your_database_name>;
task-master.datasource.control-plane.username=
task-master.datasource.control-plane.password=
task-master.datasource.control-plane.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
task-master.datasource.control-plane.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
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
task-master.datasource.control-plane.url=jdbc:postgresql://localhost:5432/controlplane_db
task-master.datasource.control-plane.username=
task-master.datasource.control-plane.password=
task-master.datasource.control-plane.driverClassName=org.postgresql.Driver
```
Do not upload the local version of the application.properties to the repository. Add to .gitignore file.

## 2. Configure Broker
Install docker and from docker hub pull one of the following supported brokers:

### 2.1 kafka
Pull apache/kafka image (apache/kafka:latest), start kafka container and test the connection
https://hub.docker.com/r/apache/kafka

Pull apache/kafka:latest image from Docker Hub
```
docker pull apache/kafka:latest
```
Run a container
```
docker run -d -p 9092:9092 --name broker apache/kafka:latest
podman run -d -p 9092:9092 --name broker apache/kafka:latest
``` 
Open a shell in the broker container:
```
docker exec --workdir /opt/kafka/bin/ -it broker sh
podman exec --workdir /opt/kafka/bin/ -it broker sh
```
A topic is a logical grouping of events in Kafka. From inside the container, create a topic called test-topic:
```
./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic test-topic
./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic TASK_MASTER_NOTIFICATIONS
```
To list topics in a kafka cluster run the following command in the container:
```
./kafka-topics.sh --bootstrap-server localhost:9092 --list
```
Write two string events into the test-topic topic using the console producer that ships with Kafka:
```
./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test-topic
```
This command will wait for input at a > prompt. Enter hello, press Enter, then world, and press Enter again. Enter Ctrl+C to exit the console producer.

Now read the events in the test-topic topic from the beginning of the log:
```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic TASK_MASTER_NOTIFICATIONS --from-beginning
```

Download the project from the GitHub repo, get a copy from the src/main/resources/application.properties.template for local use and put in /src/main/resources.

In the application.properties modify these properties as needed to match your kafka configuration:
``` 
task-master.notification.kafka.bootstrap.servers=localhost:9092
```

### 2.2 GCP PubSub
[Spring Cloud GCP PubSub Documentation](https://googlecloudplatform.github.io/spring-cloud-gcp/3.1.0/reference/html/index.html#cloud-pubsub)

#### 2.2.1 Running PubSub in Local Environment using emulator
[PubSub Local Emulator](https://medium.com/google-cloud/use-pub-sub-emulator-in-minikube-67cd1f289daf)
TBD

#### 2.2.2 Provision Cloud PubSub in GCP

Create a topic in GCP PubSub and include a subscription.

Download the project from the GitHub repo, get a copy from the src/main/resources/application.properties.template for local use and put in /src/main/resources.

In the application.properties modify these properties as needed to match your configuration:
``` 
task-master.notification.pubsub.projectId=
task-master.notification.pubsub.topic=
task-master.notification.pubsub.subscriptionId=
task-master.notification.pubsub.serviceAccountKeyPath=classpath:<*-sa.json>
```

Do not upload the local version of the application.properties to the repository. Add to .gitignore file.

## 3. Run function locally
Install docker/podman and buildpacks tools:

### 3.1 Buildpacks
Install buildpacks (depends on docker/podman) and build function image:

#### 3.1.1 Installing buildpacks
- [Installing buidpacks](https://buildpacks.io/docs/for-platform-operators/how-to/integrate-ci/pack/)

#### 3.1.2 Build local image
- [Build a function with buildpacks](https://cloud.google.com/docs/buildpacks/build-function#java)
- [Configure Cloud Run and Cloud Run functions services](https://cloud.google.com/docs/buildpacks/service-specific-configs)
  Configure project.toml file with the following configuration:
```
[[build.env]]
name = "GOOGLE_RUNTIME_VERSION"
value =  "17"
[[build.env]]
name = "GOOGLE_FUNCTION_SIGNATURE_TYPE"
value =  "http"
[[build.env]]
name = "GOOGLE_FUNCTION_TARGET"
value =  "com.onebank.taskmaster.createnotification.function.CreateNotificationFunctionEntryPoint"
[build]
builder = "gcr.io/buildpacks/builder:google-22"
```

Build image using pack CLI:
```
pack --version
pack build createnotification
```
Run a function container image
```
docker run -it -p8080:8080 createnotification
podman run -it -p8080:8080 createnotification
```
Visit the running function by browsing to localhost:8080 (only when function is trigger by http events)


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