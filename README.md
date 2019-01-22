# UserCommunicationPreference_v2
Spring Boot REST API - User Communication Preference - with scheduler sending messages at fixed intervals

ASSUMPTIONS:

	Entities:	user, communication_config, email, sms

				- can be POST, GET, PUT, DELETE through services

	Emails & SMSs(messages) for a user, which are posted through services, are being stored in corresponding tables.
  
	For now

		- A user can have one communication setting for each, an EMAIL and an SMS.

		- The scheduler runs at a fixed interval of time

			for one user (if communication configured), at a time

				send (all) 'unsent' email(s) at once for now, can be concatenated and sent as one email

				send (all) 'unsent' message(s) at once, can be concatenated and sent as one (big) sms

			repeats for next user

------------------------------------------------------------------------------------------

To test the project, please change below accordingly:

In MySQL, 	Create Database (suppose 'db_name').

Download and setup the project.

Project -> 'resources' -> application.properties, change below as required:

	spring.datasource.url = jdbc:mysql://localhost:3306/db_name?useSSL=false

	spring.datasource.username = username

	spring.datasource.password = password

Start as Spring Boot App.

------------------------------------------------------------------------------------------

As of now, below services have been configured:

POST

http://localhost:8080/api/v1/users

	{"firstName":"Abc","lastName":"Def","email":"a.d@abc.com","contact":"1111111111"}

	{"firstName":"Bcd","lastName":"Efg","email":"ab.cd@abc.com","contact":"1212121212"}

http://localhost:8080/api/v1/users/1/communications

	{"freq":"1","timeUnit":"HOUR","typeOfComm":"SMS"}

http://localhost:8080/api/v1/users/2/communications

	{"freq":"1","timeUnit":"HOUR","typeOfComm":"EMAIL"}

	{"freq":"3","timeUnit":"HOUR","typeOfComm":"SMS"}

http://localhost:8080/api/v1/users/2/emails

	{"subject":"Hi!","body":"Hello."}

http://localhost:8080/api/v1/users/2/messages

	{"content":"Hello."}

GET

http://localhost:8080/api/v1/users

http://localhost:8080/api/v1/users/1

http://localhost:8080/api/v1/users/1/communications

http://localhost:8080/api/v1/users/1/emails

http://localhost:8080/api/v1/users/1/messages

PUT

http://localhost:8080/api/v1/users/1

http://localhost:8080/api/v1/users/1/communications/1

DELETE

http://localhost:8080/api/v1/users/1

http://localhost:8080/api/v1/users/1/communications/1
