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
task-master.notification.kafka.producer.topic=TASK_MASTER_NOTIFICATIONS
task-master.notification.kafka.producer.bootstrap.servers=localhost:9092
```

### 2.2 RabbitMQ
Pull apache/kafka image (apache/kafka:latest), start kafka container and test the connection
https://hub.docker.com/_/rabbitmq/

Pull rabbitmq:latest image from Docker Hub
```
docker pull rabbitmq:latest
podman pull rabbitmq:latest
```
Run a container
```
docker run -d -p 5672:5672 --hostname my-rabbit --name some-rabbit rabbitmq:3
podman run -d -p 5672:5672 --hostname my-rabbit --name some-rabbit rabbitmq:3

docker run -d -p 5672:5672 -p 15672:15672 --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management
podman run -d -p 5672:5672 -p 15672:15672 --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management

docker run -d -p 5672:5672 -p 15672:15672 -v $(pwd -W)/config/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf --hostname my-rabbit --name some-rabbit rabbitmq:3-management
podman run -d -p 5672:5672 -p 15672:15672 -v $(pwd -W)/config/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf --hostname my-rabbit --name some-rabbit rabbitmq:3-management
``` 
Open a shell in the broker container:
```
docker exec -it some-rabbit sh
podman exec -it some-rabbit sh
```
To list queues/topics/channels in a rabbit run the following command in the container:
```
rabbitmqctl list_queues
rabbitmqctl list_channels
rabbitmqctl list_users
rabbitmqctl list_exchanges
rabbitmqctl list_consumers
```

The management plugin ships with a command line tool rabbitmqadmin which can perform some of the same actions as the Web-based UI, and which may be more convenient for automation tasks.
```
rabbitmqadmin -u user -p password list queues messages
```
a) Declare an exchange
```
rabbitmqadmin -u user -p password declare exchange name=my.queue type=topic
rabbitmqadmin -u user -p password declare exchange name=TASK_MASTER_NOTIFICATIONS type=topic
```
b) Declare a queue, with optional parameters
```
rabbitmqadmin -u user -p password declare queue name=my.queue durable=false
rabbitmqadmin -u user -p password declare queue name=TASK_MASTER_NOTIFICATIONS durable=false
```
c) To route messages from an exchange to a queue, you need to create a binding between them
```
rabbitmqadmin -u user -p password declare binding source=my.topic destination_type=queue destination=my.queue routing_key=user.*
rabbitmqadmin -u user -p password declare binding source=TASK_MASTER_NOTIFICATIONS destination_type=queue destination=TASK_MASTER_NOTIFICATIONS routing_key=notification.sent
```
d) Publish a message
```
rabbitmqadmin -u user -p password publish exchange=my.topic routing_key=user.* payload="hello, world"
rabbitmqadmin -u user -p password publish exchange=TASK_MASTER_NOTIFICATIONS routing_key=notification.sent payload="hello, world"
```
Get message from exchange/queue
```
rabbitmqadmin -u user -p password get queue=TASK_MASTER_NOTIFICATIONS ackmode=ack_requeue_false
```

Download the project from the GitHub repo, get a copy from the src/main/resources/application.properties.template for local use and put in /src/main/resources.

In the application.properties modify these properties as needed to match your kafka configuration:
``` 
task-master.notification.rabbit.topic=TASK_MASTER_NOTIFICATIONS
task-master.notification.rabbit.hostname=localhost
task-master.notification.rabbit.port=5672
task-master.notification.rabbit.username=guest
task-master.notification.rabbit.password=guest
task-master.notification.rabbit.virtualhost=/
```

### 2.2.1 Enable OAuth 2.0 using Auth0 (WIP)
[Use auth0.com as OAuth 2.0 server](https://www.rabbitmq.com/docs/oauth2-examples-auth0)
https://manage.auth0.com/dashboard


Create oauth client in Auth0
```
WIP
```
Create oauth client in Auth0 using Terraform (Optional)
```
WIP
```

Enable oauth2.0 in rabbitmq.conf file
```
auth_backends.1 = rabbit_auth_backend_oauth2

log.console.level = debug

management.oauth_enabled = true
management.oauth_client_id = {client_id}
management.oauth_scopes = openid profile rabbitmq.tag:administrator

auth_oauth2.resource_server_id = rabbitmq
auth_oauth2.issuer = {domain_url}
auth_oauth2.https.hostname_verification = wildcard
```

Run/Star container with custom rabbitmq.conf file
```
docker run -d -p 5672:5672 -p 15672:15672 -v $(pwd -W)/config/auth0/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf --hostname my-rabbit --name some-rabbit rabbitmq:3-management
podman run -d -p 5672:5672 -p 15672:15672 -v $(pwd -W)/config/auth0/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf --hostname my-rabbit --name some-rabbit rabbitmq:3-management

docker run -d -p 5672:5672 -p 15672:15672 -v $(pwd -W)/config/enabled_plugins:/etc/rabbitmq/enabled_plugins -v $(pwd -W)/config/auth0/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf --hostname my-rabbit --name some-rabbit rabbitmq:3-management
podman run -d -p 5672:5672 -p 15672:15672 -v $(pwd -W)/config/enabled_plugins:/etc/rabbitmq/enabled_plugins -v $(pwd -W)/config/auth0/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf --hostname my-rabbit --name some-rabbit rabbitmq:3-management
```

Enable OAuth0 in RabbitMQ integration
```
WIP
```


### 2.3 GCP PubSub
[Spring Cloud GCP PubSub Documentation](https://googlecloudplatform.github.io/spring-cloud-gcp/3.1.0/reference/html/index.html#cloud-pubsub)

#### 2.3.1 Running PubSub in Local Environment using emulator
[PubSub Local Emulator](https://medium.com/google-cloud/use-pub-sub-emulator-in-minikube-67cd1f289daf)
TBD

#### 2.3.2 Provision Cloud PubSub in GCP
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
```
<your_payload>
{
    "type": "TASK_CREATED",
    "user": "your_user",
    "title": "Learn Terraform",
    "message": "string",
    "status": "CREATED",
    "taskId": 1,
    "taskTitle": "Learn Terraform",
    "taskDescription": "Learn Terraform",
    "taskDueDate": "10/10/2026",
    "taskPriority": "Low"
}
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