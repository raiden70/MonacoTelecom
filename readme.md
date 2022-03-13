# Monaco Telecom

* The producer app will send a tag to the consumer queue (rabbitmq).

* The tag of the producer will be consumed by the consumer app and it will send an http request to dbapp with the consumed tag.

* The dbapp will send a response message to consumer app and it will be stored in the producer queue.

* the producer consume the message from producer queue and it will be shown in the logs.


## Create docker images:

Execute ``mvn clean install``  in each application to generate **-jar**  then execute those commands respectfully in their main application folder:

```terminal
$ sudo docker build -t sb-producer .
```

```terminal
$ sudo docker build -t sb-consumer .
```

```terminal
$ sudo docker build -t sb-dbapp .
```

## Run the application

You'll need to execute to launch the containers:

```terminal
$ sudo docker-compose up
```
## Some endpoints:
* **http://localhost:8080/services** : returns a list of pre-created services stored in an H2 database.
* **http://localhost:8080/services/{tag}** : returns the id of the service using it's tag.
* **http://localhost:8081/produce/{tag}**: send a GET parameter to the consumer queue.
* **http://localhost:8081/produce**: send a Post parameter tag to the consumer queue.
