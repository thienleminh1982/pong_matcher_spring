**ping-pong matching server** 
	This is a Spring Boot application deployed on PivotalCF, using a mysql service as database.

Original git repo: https://github.com/cloudfoundry-samples/pong_matcher_spring

Modified by: **Minh-Thien Le**

Time spent: **** hours spent in total

Deployed URL: **http://lmt-springpong-origin.cfapps.io**

## User Stories

The following **original** functionality is complete and working in the original git repo:
* [x] PUT {HOST}/match_requests/{id}
* [x] GET {HOST}/match_requests/{id}
* [x] POST {HOST}/results, e.g: request body:
		{
		"match_id":"thematchidyoureceived",
		"winner":"andrew",
		"loser":"navratilova"
		}
* [x] GET {{HOST}}/matches/{id}
* [x] DELETE {HOST}/all

The following **modified** features are implemented by **Minh-Thien Le**
* [x] Add welcome message in the root path:
	{"root_page_welcome":"Welcome to pong-matcher-spring application updated!"}
* [x] GET {HOST}/results : get all results from db table "result"
	

## Video Walkthrough

Here's a walkthrough of implemented user stories:

## Notes
* Upgraded to latest Spring Boot 1.3.2
* Test the APIs by POSTMAN to send REST requests.
* This app has been working on both PWS and local.

=================================================================================
# CF example app: ping-pong matching server

This is an app to match ping-pong players with each other. It's currently an
API only, so you have to use `curl` to interact with it.

It has an [acceptance test suite][acceptance-test] you might like to look at.

**Note**: We highly recommend that you use the latest versions of any software required by this sample application. For example, make sure that you are using the most recent verion of maven.

## Running on [Pivotal Web Services][pws]

Log in.

```bash
cf login -a https://api.run.pivotal.io
```

Target your org / space.

```bash
cf target -o myorg -s myspace
```

Sign up for a cleardb instance.

```bash
cf create-service cleardb spark mysql
```

Build the app.

```bash
brew install maven
mvn package
```

Push the app. Its manifest assumes you called your ClearDB instance 'mysql'.

```bash
cf push -n mysubdomain
```

Export the test host

```bash
export HOST=http://mysubdomain.cfapps.io
```

Now follow the [interaction instructions][interaction].

## Running locally

The following assumes you have a working Java 1.8 SDK installed.

Install and start mysql:

```bash
brew install mysql
mysql.server start
mysql -u root
```

Create a database user and table in the MySQL REPL you just opened:

```sql
CREATE USER 'springpong'@'localhost' IDENTIFIED BY 'springpong';
CREATE DATABASE pong_matcher_spring_development;
GRANT ALL ON pong_matcher_spring_development.* TO 'springpong'@'localhost';
exit
```

Start the application server from your IDE or the command line:

```bash
mvn spring-boot:run
```

Export the test host

```bash
export HOST=http://localhost:8080
```

Now follow the [interaction instructions][interaction].

[acceptance-test]:https://github.com/cloudfoundry-samples/pong_matcher_acceptance
[pws]:https://run.pivotal.io
[interaction]:https://github.com/cloudfoundry-samples/pong_matcher_grails#interaction-instructions
