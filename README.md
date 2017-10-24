# Google Language and Translate API on PCF Example
This is some basic code that creates a REST endpoint for Google sentiment API and translation.

## Requirements
1. Pivotal Cloud Foundry
2. [Google Service Broker](https://docs.pivotal.io/partners/gcp-sb/index.html)
3. Sentiment and Translate APIs enabled

## Setup
Create a Google Machine Learning service in Pivotal Cloud Foundry and call it machine-learning:
```
$ cf create-service google-ml-apis default machine-learning
```
Compile this code:
```
$ ./build.sh
```
Push it without starting:
```
$ cf push --no-start
```
Now bind the Google service with the Developer role:
```
$ cf bind-service google-language-api-demo machine-learning -c '{"role": "ml.developer"}'
```
Finally start it!
```
cf start google-language-api-demo
```

## Testing
To test it's working, try the hello world endpoint:
```
$ curl -s http://<your-url>/v1/hello-world | jq
```
You should get a response:
```
{
  "sourceLanguage": "en",
  "sourceText": "Hello world!",
  "desintationLanguage": "fr",
  "translatedText": "Bonjour le monde!"
}
```

You can also use a few more endpoints:

* `/v1/translate/source-language/destination-language/text`
* `/v1/sentiment/text`

## Code
The main Google configuration is in `io.pivotal.mday.examples.googledemo.config.GoogleLanguageConfig.java`. This translates the PCF `VCAP_SERVICES` environment variable to something the Google client APIs understand. After that, it's just plain sailing!
