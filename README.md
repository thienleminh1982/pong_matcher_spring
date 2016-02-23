**ping-pong matching server** 
	This is a Spring Boot application deployed on PWS or Heroku
	,using a PostgreSQL service as database.

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
* This app has been working on both PWS, Heroku and local.

=================================================================================
**Note**: We highly recommend that you use the latest versions of any software required by this sample application. For example, make sure that you are using the most recent verion of maven.

## Running on Pivotal Web Services

Log in.

```bash
cf login -a https://api.run.pivotal.io
```

Target your org / space.

```bash
cf target -o myorg -s myspace
```

Sign up for a PostgreSQL instance, name it: "psql"

```bash
cf create-service elephantsql turtle psql
```

Build the app.

```bash
mvn clean package
```

Push the app. Its manifest assumes you called your ClearDB instance 'mysql'.

```bash
cf push -n mysubdomain
```

Export the test host

```bash
export HOST=http://mysubdomain.cfapps.io
```

## Deployment on Heroku
Add the Procfile containing metadata for Heroku specific deployment.
As the Procfile tells Heroku that this is a web application, a PostgreSQL service will be **automatically created and binded to this application during deployment**.

Edit **application.yml**: must configure the **spring.datasource.url = ${JDBC_DATABASE_URL}**

Then, run these commands:

```bash
heroku create
git push heroku <your-branch>:master
```

## Running locally with PostgreSQL

The following assumes you have a working Java 1.8 SDK installed.

Install and start PostgreSQL:

Create a database user and table:

```sql
CREATE USER springpong with password 'springpong' CREATEDB;
create database pong_matcher_spring_development with owner springpong ENCODING 'UTF8';
grant all privileges on database pong_matcher_spring_development to springpong;
```

Edit **application.yml**: must configure the spring.datasource something like:

```yml
url: jdbc:postgresql://localhost:5432/pong_matcher_spring_development
username: springpong
password: springpong
```
    
Re-build the app.

```bash
mvn clean package
```

Start the application server from your IDE or the command line:

```bash
mvn spring-boot:run
```
=========================================================================
**Note**: some minor notes on migrate the sql scripts from Mysql to Postgresql:
/* lmthien: SERIAL keyword is PostgreSQL specific, equivalent to AUTO_INCREMENT */
/* lmthien: AUTO_INCREMENT keyword is MySQL specific
CREATE TABLE match_request (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  uuid         VARCHAR(255) NOT NULL,
  requester_id VARCHAR(255) NOT NULL,

  CONSTRAINT unique_uuid UNIQUE (uuid)
);
*/

/* 
 * Match is not a reserved keyword in psql
 * http://www.postgresql.org/docs/9.2/static/sql-keywords-appendix.html
 */
/* lmthien: MySQL specific: match is a keyword. Thus have to enclose it with ` 
CREATE TABLE `match` (
  id               VARCHAR(255) PRIMARY KEY,
  match_request1id VARCHAR(255) NOT NULL,
  match_request2id VARCHAR(255) NOT NULL
);
*/

==========================================================
Now follow the interaction instructions interaction.

[acceptance-test]:https://github.com/cloudfoundry-samples/pong_matcher_acceptance
[pws]:https://run.pivotal.io
[interaction]:https://github.com/cloudfoundry-samples/pong_matcher_grails#interaction-instructions






