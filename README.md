# Java Mentoring Program: Micro services

This maven-based project contains an example of multiple microservices ecosystem with monitoring capabilities (with graphana / prometheus) on a kubernetes cluster. 

0) To run the project one have the following options:

- mvn package. Compiles and builds the docker image of every micro service: <em>micro-collector, micro-recipient, micro-sender, micro-visualizer</em>

1) The following microservice ecosystem is provided:
- **micro-sender** Provides a simple API endpoint: `POST /notification` that puts a notification message in the rabbitmq broker.
- **micro-recipient** Listens to the same channel used by *micro-sender*, when a notification is read from the channel, it saves it in a PostgreSQL DB. It also provides an API endpoint `GET /notification` that retrieves all the notifications saved in the DB (and deletes them).
- **micro-collector** Uses Spring Scheduler to make a recurrent call to the API endpoint from *micro-recipient* `GET /notification`. Every 5 seconds. 
- **micro-visualizer** Provides an endpoint `GET /notification` that retrieves all the notifications from the DB (without deleting them)

For the sake of testing the following docker images are configured in  docker-compose.yaml file (`docker/docker-compose.yaml`)

- rabbitmq. Used by *micro-sender* (who writes in the queue) and *micro-recipient* (who listens from the rabbitmq).
- postgresql. Used by *micro-recipient* and *micro-visualizer* to read stored data.
- prometheus. Scans every 5 seconds the metrics provided by every micro service.
- graphana. Plot user-friendly time based plots from prometheus image. 

To start those images just run: `$docker-compose up -d`. All the images will be started and then the microservices can be executed

2) Every microservice module also a kubernetes descriptor file to deploy the microservice in a kubernetes cluster. The descriptor file is `/kube-deployment.yaml`. 
- Requirements:
- Minikube installed and stared: `$mikikube start`
- Create a tunnel for having access to the kubernetes cluster: `$mikikube tunnel`
- The supporting images (rabbitmq, postgresql, prometheus, graphana) are all included in `kubernetes/kube-deployment.yaml`

- Make the deployment: `kubectl create -f kube-deployment.yaml` (remember to do this for each microservice and for the supporting images)

3) The applications should be access from outside the cluster:

   | micro-service | URL |
   |----------|:-------------:|
   | micro-sender |  http://127.0.0.1:8080 |
   | micro-recipient |  http://127.0.0.1:8081 |
   | micro-collector |  http://127.0.0.1:8082 |
   | micro-visualizer |  http://127.0.0.1:8083 |
   | rabbitmq | http://127.0.0.1:5672 |
   | prometheus | http://127.0.0.1:9090 |
   | graphan1 | http://127.0.0.1:3000 |

4) In the same folder you can find img.png where a example plot in graphana application. 

![Alt text](img.png?raw=true "Title")

5) The microservice *micro-sender* provides also an example of a canary deployment in the file: `kubectl create -f kube-deployment.yaml`.


