# kazdemo
Active MQ integration with spring boot and react

# System requirement
- OS: Windows 10 
- Git client
- Spring boot STS Suite 4.4.14.SUITE
- Java >=17
- VS Code IDE or any other IDE
- 200 ok Web Server for Chorome

# How to setup project in local
- Clone the project folder in local repo from: [https://github.com/adibhasan/kazdemo]
- Add 200 ok Web Server for Chorome
- Downlaod ActiveMQ to your local from [https://activemq.apache.org/components/classic/download/] for windows

## Run Front End Project on local machine
- Open 200 ok web server 
- Click "CHOOSE FOLDER"
- Browse the cloned project directory 
- Load the project from kazdemo\frontend\kaz-demo\build directory 
- Click http://127.0.0.1:8887 
- Front end page will be apeared

## Run Active MQ
- Go to Active MQ download folder
- Go to bin folder like /apache-activemq-5.16.5/bin
- Open cmd and run `activemq start`
- Browse http://localhost:8161/
- If any popup is shown, enter user and password as `admin` 
- Then browse Queues and Topics
- Initially all should be zero

## Run Producer Spring Boot Project
- Load the project in the STS IDE
- Run as Spring Boot Web App
- Base URL should be http://localhost:9091
- Message posting(http POST request) url will be http://localhost:9091/producer/message

## Run Consumer Spring Boot Project
- Load the project in the STS IDE
- Run as Spring Boot Web App

# How to test

## Using front project
- Browse http://127.0.0.1:8887 
- Fill up form and post data
- Visit Active MQ http://localhost:8161/
- See the Queue result
- Go to STS Console for checking pull message for consumer project
- Go to kazdemo/consumer/jsonfiles folder for checking generated json file
- For exception json file will contain the error message string 

## Using post man
- Open post man
- Create a post request with the end point http://localhost:9091/producer/message
- Add the following json as payload
  `
    {
        "messageId": "0000002",
        "messageTitle": "BCCC ffMovdsies ccc",
        "messageDataUrl": "https://jsonplaceholder.typicode.com/posts"
    }
  `
- Visit Active MQ http://localhost:8161/
- See the Queue result
- Go to STS Console for checking pull message for consumer project
- Go to kazdemo/consumer/jsonfiles folder for checking generated json file
- For exception json file will contain the error message string 

### Tested Rest API end point success case
- https://jsonplaceholder.typicode.com/posts
- https://api.themoviedb.org/3/movie/popular?api_key=f489b4cf26033b9b2d5b3b8efe4b6cc9&language=en-US&page=1

### Tested Rest API end point fail case
- https://api.themoviedb.org/3/movie/popularxx?api_key=f489b4cf26033b9b2d5b3b8efe4b6cc9&language=en-US&page=1
