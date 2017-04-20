# How to start services
1. Please make sure that all service have built to .jar. If each of services aren't build yet, You have to build those services manually because I'm lazy now. By the way, You have to type commands as shown below. (You have to build all of services)

```
$ cd service-directory
$ mvn clean package
```

3. Initialize swarm

```
$ docker swarm init
```

3. Then, create docker network named "soa".

```
$ docker network create -d overlay soa
```

4. The easiest part has already arrived. move your current path to /KMITL_Residence and finally, You have to type the last one command in your dumb terminal.

```
$ docker stack deploy -c stack.yml stack
```
