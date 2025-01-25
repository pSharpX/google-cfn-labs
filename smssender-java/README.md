# smssender-java

## Introduction
The smssender function is responsible for sending sms/whatsapp notifications in the tasks management ecosystem.

## Getting Started

## 1. Configure SMS Provider
For sending SMS notifications the following integrations are supported:

### 1.1 Twilio
For Twilio Transactional API integration review the following documentation:
[Message Resource API](https://www.twilio.com/docs/messaging/api/message-resource)

These are the requirements:
- Twilio Account
- API Key

Once account is setup properly configure the following properties:
``` 
task-master.channel.sms.twilio.url=https://api.twilio.com
task-master.channel.sms.twilio.apiVersion=/2010-04-01
task-master.channel.sms.twilio.basePath=https://api.twilio.com/2010-04-01
task-master.channel.sms.twilio.accountSid=your_accountSid
task-master.channel.sms.twilio.credentials.sid=your_apikey_sid
task-master.channel.sms.twilio.credentials.secret=your_apikey_secret
task-master.channel.sms.twilio.msId=your_message_service_id
task-master.channel.sms.twilio.logger.level=NONE
```

### 1.2 Plivo
TBD

### 1.2 AWS SNS
TBD

Do not upload the local version of the application.properties to the repository. Add to .gitignore file.

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
value =  "com.onebank.taskmaster.sendnotification.function.SendSmsNotificationFunctionEntryPoint"
[build]
builder = "gcr.io/buildpacks/builder:google-22"
```

Build image using pack CLI:
```
pack --version
pack build smssender
```
Run a function container image
```
docker run -it -p8080:8080 smssender
podman run -it -p8080:8080 smssender
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
docker run --rm -p 8080:8080 smssender
podman run -it -p8080:8080 smssender
```
In the second terminal, invoke the function by publishing a message. The message data needs to be encoded in base64. 
This example uses the base64 encoded json data:
```
<your_payload>
{
    "id": 1,
    "channel": "SMS",
    "type": "TASK_CREATED",
    "user": "your_user_id",
    "title": "Learn Terraform",
    "message": "Learn Terraform from scratch",
    "recipientPhoneNumber": "your_phone_number",
    "templateName": "your_template_id",
    "vars": {
        "1": "your_name",
        "2": "Learn Terraform",        
        "3": "http://localhost:8080"
    }
}
<your_request_payload>
{
    "data": {
        "data": "<your_payload_in_base64_encoded>",
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