# createnotification-java

## Introduction
The createnotification function is responsible for publishing notifications (email, push, sms) for the tasks management ecosystem.

## Getting Started

## 2. Run function locally
Install docker/podman and buildpacks tools:

### 2.1 Buildpacks
Install buildpacks (depends on docker/podman) and build function image:

#### 2.1.1 Installing buildpacks
- [Installing buidpacks](https://buildpacks.io/docs/for-platform-operators/how-to/integrate-ci/pack/)

#### 2.1.2 Build local image
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

## 3. Run PuSub emulator for local testing (ONLY for CloudEventFunction)
You can trigger a function locally using a push message from the Pub/Sub emulator.
- [Test locally with the Pub/Sub emulator](https://cloud.google.com/functions/docs/local-development)

Make sure you have the pack tool and Docker installed.

### 3.1 Buildpacks
Install buildpacks (depends on docker/podman) and build function image:

#### 3.1.1 Installing buildpacks
- [Installing buidpacks](https://buildpacks.io/docs/for-platform-operators/how-to/integrate-ci/pack/)

### 3.2 Start PubSub emulator (using GCLOUD CLI)

Issue with emulator and function-framework: https://github.com/GoogleCloudPlatform/functions-framework-dotnet/issues/234
[Fix local development with Pub/Sub emulator](https://github.com/GoogleCloudPlatform/functions-framework-nodejs/pull/272)

In the first terminal, start the Pub/Sub emulator on port 8043 in a local project:
```
gcloud beta emulators pubsub start --project=abc --host-port='localhost:8043'
```

In the second terminal, create a Pub/Sub topic and subscription:
```
curl -s -X PUT 'http://localhost:8043/v1/projects/abc/topics/mytopic'
```
Use http://localhost:8080 as the push subscription's endpoint.
```
curl -s -X PUT 'http://localhost:8043/v1/projects/abc/subscriptions/mysub' -H 'Content-Type: application/json' --data '{"topic":"projects/abc/topics/mytopic","pushConfig":{"pushEndpoint":"http://localhost:8080/projects/abc/topics/mytopic"}}'
```
Make sure the function is running on port 8080. This is where the emulator will send push messages:
```
docker run --rm -p 8080:8080 createnotification
podman run -it -p8080:8080 createnotification
```
In the second terminal, invoke the function by publishing a message. The message data needs to be encoded in base64. 
This example uses the base64 encoded json data:
```
<your_payload>
{
    "id": 1,
    "channel": "EMAIL",
    "type": "TASK_CREATED",
    "user": "your_email",
    "title": "Learn Terraform",
    "message": "Learn Terraform from scratch",
    "recipientName": "your_name",
    "recipientEmail": "your_email",
    "templateName": "task-created-template",
    "vars": {
        "application_name": "Task Master",
        "username": "your_name",
        "current_year": "2025",
        "task_title": "Learn Terraform",
        "task_description": "Learn Terraform",
        "task_due_date": "Learn Terraform",
        "task_priority": "Learn Terraform"
    }
}
<your_request_payload>
{
    "data": {
        "data": "<your_payload>",
        "attributes": {
            "Content-Type": "application/json"
        }
    },
    "context": {
        "eventId": "1",
        "eventType": "google.pubsub.topic.publish",
        "resource": {
            "name": "topic",
            "service": "pubsub.googleapis.com",
            "type": "type.googleapis.com/google.pubsub.v1.PubsubMessage"
        },
        "timestamp": "2024-12-30T16:22:43.946Z"
    }
}
```
Now invoke the function by publishing a message:
```
curl -s -X POST 'http://localhost:8043/v1/projects/abc/topics/mytopic:publish' -H 'Content-Type: application/json' --data '<your_request_payload>'
```

### 3.2 Start PubSub emulator (using docker/podman)

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