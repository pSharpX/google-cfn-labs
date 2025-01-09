# templatemanager

## Introduction
The templatemanager function is responsible for managing email templates used for the tasks management ecosystem.
Supported operation like create-or-update and delete are included.

## Getting Started

## 1. Configure Email Provider
For managing emails templates the following integrations are supported:

### 1.1 MailChimp
For MailChimp Transactional API integration review the following documentation:
[MailChimp Transactional API](https://mailchimp.com/developer/transactional/guides/quick-start/)

These are the requirements:
- Mailchimp Account
- API Key

Once account is setup properly configure the following properties:
``` 
task-master.channel.email.mailchimp.url=https://mandrillapp.com
task-master.channel.email.mailchimp.apiVersion=/api/1.0
task-master.channel.email.mailchimp.basePath=${task-master.channel.email.mailchimp.url}${task-master.channel.email.mailchimp.apiVersion}
task-master.channel.email.mailchimp.apikey=
task-master.channel.email.mailchimp.config.mergeLanguage=handlebars
task-master.channel.email.mailchimp.from.email=
task-master.channel.email.mailchimp.from.name=
```

### 1.2 Twilio SendGrid
For MailChimp Transactional API integration review the following documentation:
[SendGrid API v3](https://www.twilio.com/docs/sendgrid/api-reference/mail-send/mail-send)

These are the requirements:
- SendGrid Account
- [API Key](https://app.sendgrid.com/login?redirect_to=%2Fsettings%2Fapi_keys)

Once account is setup properly configure the following properties:
``` 
task-master.channel.email.sendgrid.url=https://api.sendgrid.com
task-master.channel.email.sendgrid.apiVersion=/v3
task-master.channel.email.sendgrid.basePath=${task-master.channel.email.sendgrid.url}${task-master.channel.email.sendgrid.apiVersion}
task-master.channel.email.sendgrid.apikey=
task-master.channel.email.sendgrid.from.email=
task-master.channel.email.sendgrid.from.name=
```

### 1.3 Mailgun
TBD
### 1.4 Mailjet
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
value =  "event"
# value =  "event"
# value =  "http"
[[build.env]]
name = "GOOGLE_FUNCTION_TARGET"
value =  "com.onebank.taskmaster.templatemanager.function.ManageTemplateFunctionEntryPoint"
[build]
builder = "gcr.io/buildpacks/builder:google-22"
```

Build image using pack CLI:
```
pack --version
pack build templatemanager
```
Run a function container image
```
docker run -it -p8080:8080 templatemanager
podman run -it -p8080:8080 templatemanager
```
Visit the running function by browsing to localhost:8080 (only when function is trigger by http events)

## 3. Configure Event Sources for CloudEventFunction
The following tools or resources can be created as event source for invoking cloud function:

### 3.1 Run PuSub emulator for local testing
You can trigger a function locally using a push message from the Pub/Sub emulator.
- [Test locally with the Pub/Sub emulator](https://cloud.google.com/functions/docs/local-development)

Make sure you have the pack tool and Docker installed.

### 3.2 Run Cloud Storage emulator for local testing
You can trigger a function locally using a cloud storage emulator.

## 4. Testing Cloud Function locally invoking manually
You can invoke function locally visiting next link http://localhost:8080

### 4.1 PubSub as Event Source (LOCAL)
If you need to emulate message published in a topic use the following request example:
```
curl -X POST \
  -H "Content-Type: application/json" \
  -H "ce-specversion: 1.0" \
  -H "ce-type: google.cloud.pubsub.topic.v1.messagePublished" \
  -H "ce-source: //pubsub.googleapis.com/projects/my-project/topics/my-topic" \
  -H "ce-id: 1234567890123456" \
  -H "ce-time: 2024-12-30T16:22:43.946Z" \
  -d '{
        "message": {
            "attributes": {
                "key1": "value1",
                "key2": "value2"
            },
            "data": "SGVsbG8gd29ybGQh", // Base64-encoded
            "messageId": "123456789012",
            "publishTime": "2025-01-01T12:00:00.123Z"
        }
    }' \
  http://localhost:8080
```

### 4.2 Cloud Storage as Event Source (LOCAL)
If you need to emulate cloud storage events use the following request example:
```
curl -X POST \
  -H "Content-Type: application/json" \
  -H "ce-specversion: 1.0" \
  -H "ce-type: google.cloud.storage.object.v1.finalized" \
  -H "ce-source: //storage.googleapis.com/{bucket-name" \
  -H "ce-id: 1234567890123456" \
  -H "ce-time: 2024-12-30T16:22:43.946Z" \
  -d '{
          "bucket": "my-bucket",
          "contentType": "text/plain",
          "crc32c": "rTVTeQ==",
          "etag": "CNHZkbuF/ugCEAE=",
          "generation": "1579287380533984",
          "id": "my-bucket/my-file.txt/1579287380533984",
          "kind": "storage#object",
          "md5Hash": "HXB937GQDFxDFqUGiKjg==",
          "mediaLink": "https://storage.googleapis.com/download/storage/v1/b/my-bucket/o/my-file.txt",
          "name": "my-file.txt",
          "size": "1024",
          "storageClass": "STANDARD",
          "timeCreated": "2025-01-01T12:00:00.123Z",
          "updated": "2025-01-01T12:00:00.123Z"
        }' \
  http://localhost:8080
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