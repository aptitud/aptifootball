
# Aptifotball

## Current status
[![Build Status](https://travis-ci.org/aptitud/aptifootball.svg?branch=master)](https://travis-ci.org/aptitud/aptifootball)


## Building

To build the project you'll need to have maven and java8 installed.

```
mvn clean install
```
To build the [dropwizard](http://www.dropwizard.io/0.9.2/docs/) executable jar. The maven task will execute an npm build that puts dependencies in `src\main\resources\assets\node_modules` via the [frontend plugin](https://github.com/eirslett/frontend-maven-plugin ). Having these assets there (rather than in the target folder directly) enables us to start the project from within intellij.

```
java -jar  target/aptifootball-1.0-SNAPSHOT.jar server {properties}.yml
```
Starts the service which should now be accessible on http://localhost:8080/index.html .

### Configuration

There are a few configuration parameters that are required for the application to run.

* Server: - Root path :  When bundling static assets with dropwizard, it is recommended that the rootPath of the rest resources is altered. THis needs to be `/api/` exactly (unless you change the js code callin it). It has to be configured either in the .yml file or command line `-Ddw.server.rootPath="/api/"`
* collectionName Specifies which collection in the mongoDB is used to store the documents. This is handy to use while testing etc `-Ddw.collectionName=...` Defaults to *aptifootball*
* accessKey: The XML soccer access key that is associated with the account `-Ddw.accessKey=...`
* url : The url to the XML soccerAPI `-Ddw.url=$XS_URL`. Defaults to *http://www.xmlsoccer.com/FootballDataDemo.asmx*
* databaseUrl : The mongo DB url `-Ddw.databaseUrl=` it is highy recommended running a local docker image for testing. `docker run -p 27017:27017 -d mongo` will start one on your local docker host

There are loads of other options that are useful when running locally (like logging settingss)

This is a sample, minimal, configuration :
```
databaseUrl: mongodb://192.168.59.103
accessKey: #secret. But you can get a demo accesskey for free at http://xmlsoccer.com/Register.aspx#
server:
  rootPath: /api/
```

To add more config, read the [manual](http://www.dropwizard.io/0.9.2/docs/manual/configuration.html) But know that parameters need to be added to the procfile for them to be included in the deployed app, as heroku does not (!) have support for uploading config files. All parameters to dropwizard needs to be passed on the commandline.  (dont forget the _dw._ prefix)


## Deployment

The application is deployed to [heroku](http://heroku.com/) via [travis-ci](https://travis-ci.org/aptitud/aptifootball) This is handled using two config files: the `travis.yml` file tells travis that its to deploy to the heroku app [aptitud/aptifootball-api](https://dashboard.heroku.com/apps/aptifootball-api/resources) It also sends a notification to slack.
This command uploads the jar file and uses the `Procfile` as the start command for the service.  You can also look at this file to see the configuration that is used in heroku.
