# Actividad Docker-Kubernetes-Microservicios

Trabajo de la actividad V3.

La idea es implementar microservicios dividiendo el proyecto en componentes mas peque;os.

## Estructura del proyecto

### Implementacion de Dockerfiles

Creamos una carpeta por cada microservicio, cada una incluira un `Dockerfile`.

Archivos Dockerfile:

* Dockerfile de GameService

```docker
FROM openjdk:11
WORKDIR /app
COPY . /app
RUN javac GameService.java
CMD ["java", "GameService"]
```

* Dockerfile de MapService

```docker
FROM openjdk:11
WORKDIR /app
COPY . /app
RUN javac MapService.java
CMD ["java", "MapService"]
```

* Dockerfile del PlayerService

```docker
FROM openjdk:11
WORKDIR /app
COPY . /app
RUN javac PlayerService.java
CMD ["java", "PlayerService"]
```

* Dockerfile de TowerService

```docker
FROM openjdk:11
WORKDIR /app
COPY . /app
RUN javac TowerService.java
CMD ["java", "TowerService"]
```

* Dockerfile de WaveService

```docker
FROM openjdk:11
WORKDIR /app
COPY . /app
RUN javac WaveService.java
CMD ["java", "WaveService"]
```

Una vez implementados,corremos cada `Dockerfile` para construir las imagenes

Comandos empleados:

> docker build -t game-service .
> docker build -t map-service .
> docker build -t player-service .
> docker build -t tower-service .
> docker build -t wave-service .

Verificamos la creacion de imagenes con `docker image ls`

```sh
gabriel@192:~$ docker image ls
REPOSITORY           TAG       IMAGE ID       CREATED          SIZE
game-service         latest    e669b0e15424   5 minutes ago    654MB
map-service          latest    e669b0e15424   5 minutes ago    654MB
player-service       latest    4a3dfe9e62b1   14 minutes ago   654MB
tower-service        latest    0fa4c49fb598   15 minutes ago   654MB
wave-service         latest    7357d6edc8d9   15 minutes ago   654MB
.
.
.
```

Ahora, para poder comunicar contenedores debemos crear una red.

Ejecutamos

```sh
docker network create game-network
```

Salida en consola:

```sh
gabriel@192:MapService$ docker network create game-network
e93bd8a43d5194acb45e8692ee3f73858186e954e73fb669555918994fed3afc
gabriel@192:MapService$
```

Verificamos que se haya creado con `docker network ls | grep game`

```sh
gabriel@192:MapService$ docker network ls | grep game
e93bd8a43d51   game-network             bridge    local
gabriel@192:MapService$
```

Revisamos la informacion de nuestra red con `docker network inspect`

```sh
gabriel@192:microservicios$ docker network inspect game-network 
[
    {
        "Name": "game-network",
        "Id": "e93bd8a43d5194acb45e8692ee3f73858186e954e73fb669555918994fed3afc",
        "Created": "2024-07-02T21:19:00.840837847-05:00",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": {},
            "Config": [
                {
                    "Subnet": "172.19.0.0/16",
                    "Gateway": "172.19.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {},
        "Options": {},
        "Labels": {}
    }
]
gabriel@192:microservicios$ 
```

## Importante

En este punto encontre diversos errores, por ejemplo, cuando eejcutaba
`docker run -it --name <contenedor-service> game-network` no se agregaba.

Eso era porque el Dockerfile no detectaba el metodo `main` en ciertas clases, lo corregi agregango en cada Dockerfile la linea

```docker
CMD ["tail", "-f", "/dev/null"]
```

Lo que pasaba es que al correr la imagen, se cerraba porque no encontraba el metodo main, despues de eso ya pude corregir y se conecta correctamente.

### Kubernetes

Docker-compose es utilizado para la gestion de aplicaciones multi-contenedor.

Estructura del archivo `docker-compose.yml`

```yml
version: '3'
services:
  game:
    build: ./GameService
    networks:
    - game-network
    volumes:
    - game-data:/app/data
  map:
    build: ./MapService
    networks:
    - game-network
  player:
    build: ./PlayerService
    networks:
    - game-network
  tower:
    build: ./TowerService
    networks:
    - game-network
  wave:
    build: ./WaveService
    networks:
    - game-network
networks:
  game-network:
    driver: bridge
volumes:
  game-data:
    driver: local
```

Debemos levantar los servicios, para ello ejecutamos el comando `docker-compose up -d` en la ruta donde se encuentre el archivo `docker-compose.yml`

Salida en consola:

```sh
gabriel@192:microservicios$ docker-compose up
WARN[0000] /home/gabriel/Documents/UNI/24_1/DesarrolloSoftware/CC3S2/Docker-Kubernetes-Microservicios/app/src/main/java/docker/kubernetes/microservicios/docker-compose.yml: `version` is obsolete 
[+] Building 2.0s (32/32) FINISHED                                    docker:default
 => [map internal] load build definition from Dockerfile                        0.1s
 => => transferring dockerfile: 240B                                            0.0s
 => [game internal] load build definition from Dockerfile                       0.0s
 => => transferring dockerfile: 78B                                             0.0s
 => [player internal] load build definition from Dockerfile                     0.0s
 => => transferring dockerfile: 78B                                             0.0s
 => [wave internal] load build definition from Dockerfile                       0.1s
 => => transferring dockerfile: 132B                                            0.0s
 => [tower internal] load build definition from Dockerfile                      0.1s
 => => transferring dockerfile: 78B                                             0.0s
 => [game internal] load metadata for docker.io/library/openjdk:17              1.2s
 => [wave internal] load metadata for docker.io/library/openjdk:11              1.2s
 => [game internal] load .dockerignore                                          0.0s
 => => transferring context: 2B                                                 0.0s
 => [game 1/3] FROM docker.io/library/openjdk:17@sha256:528707081fdb9562eb8191  0.0s
 => [game internal] load build context                                          0.0s
 => => transferring context: 1.36kB                                             0.0s
 => CACHED [game 2/3] WORKDIR /app                                              0.0s
 => [game 3/3] COPY . /app                                                      0.1s
 => [map internal] load .dockerignore                                           0.1s
 => => transferring context: 2B                                                 0.0s
 => [tower internal] load .dockerignore                                         0.0s
 => => transferring context: 2B                                                 0.0s
 => [player internal] load .dockerignore                                        0.1s
 => => transferring context: 2B                                                 0.0s
 => [wave internal] load .dockerignore                                          0.1s
 => => transferring context: 2B                                                 0.0s
 => [wave 1/3] FROM docker.io/library/openjdk:11@sha256:99bac5bf83633e3c7399ae  0.0s
 => [tower internal] load build context                                         0.1s
 => => transferring context: 362B                                               0.0s
 => [map internal] load build context                                           0.1s
 => => transferring context: 1.08kB                                             0.0s
 => [game] exporting to image                                                   0.1s
 => => exporting layers                                                         0.1s
 => => writing image sha256:fab62793a92a1da45ffdd326f222778761f327f6f7a54e735f  0.0s
 => => naming to docker.io/library/microservicios-game                          0.0s
 => [player internal] load build context                                        0.1s
 => => transferring context: 463B                                               0.0s
 => CACHED [wave 2/3] WORKDIR /app                                              0.0s
 => [tower 3/3] COPY . /app                                                     0.1s
 => [wave internal] load build context                                          0.1s
 => => transferring context: 338B                                               0.0s
 => CACHED [map 3/3] COPY . /app                                                0.0s
 => [player 3/3] COPY . /app                                                    0.1s
 => CACHED [wave 3/4] COPY . /app                                               0.0s
 => CACHED [wave 4/4] RUN javac WaveService.java                                0.0s
 => [tower] exporting to image                                                  0.2s
 => => exporting layers                                                         0.1s
 => => writing image sha256:d27a5c98cc0b62b2ddff92d8599204316c3edb1152883cf506  0.0s
 => => naming to docker.io/library/microservicios-tower                         0.0s
 => [map] exporting to image                                                    0.1s
 => => exporting layers                                                         0.0s
 => => writing image sha256:112470f2cff16f19ab92a6e27d8b058eaff914c77919ac8734  0.0s
 => => naming to docker.io/library/microservicios-map                           0.0s
 => [wave] exporting to image                                                   0.1s
 => => exporting layers                                                         0.0s
 => => writing image sha256:079abc3d0533cb3d5942bd03b2d900155096406965fc270160  0.0s
 => => naming to docker.io/library/microservicios-wave                          0.0s
 => [player] exporting to image                                                 0.2s
 => => exporting layers                                                         0.1s
 => => writing image sha256:9a17f3c74368f1e3c1216fa0acee3acc98696747102ad02e01  0.0s
 => => naming to docker.io/library/microservicios-player                        0.0s
[+] Running 7/7
 ✔ Network microservicios_game-network  Created                                 0.2s 
 ✔ Volume "microservicios_game-data"    Created                                 0.0s 
 ✔ Container microservicios-tower-1     C...                                    0.1s 
 ✔ Container microservicios-game-1      Cr...                                   0.1s 
 ✔ Container microservicios-map-1       Cre...                                  0.1s 
 ✔ Container microservicios-wave-1      Cr...                                   0.1s 
 ✔ Container microservicios-player-1    Created                                 0.1s 
Attaching to game-1, map-1, player-1, tower-1, wave-1
.
.
.
gabriel@192:microservicios$ 
```

## Despliegue en Kubernetes

Salida en consola

```sh
:microservicios$ docker-compose up -d
[+] Running 5/5
 ✔ Container player-service  Started                                                                                                                                                   1.5s 
 ✔ Container wave-service    Started                                                                                                                                                   1.5s 
 ✔ Container map-service     Started                                                                                                                                                   1.5s 
 ✔ Container tower-service   Started                                                                                                                                                   1.5s 
 ✔ Container game-service    Started                                                                                                                                                   2.2s 
gabriel@192:microservicios$ 
```

## Deployment

Agregamos archivos `servicio-deployment.yaml` a cada carpeta que contiene nuestro microservicio.

## Services

Agregamos archivos `servicio-service.yaml` a cada carpeta que contiene microservicio.

## Minikube y kubectl

Minikube: Herramienta para ejecutar un clúster de Kubernetes local. Es util para desarrollo y pruebas.

kubectl: Herramienta de línea de comandos para interactuar con Kubernetes. Permite gestionar y operar el clúster y sus recursos.

Para esta seccion debemos iniciar el cluster, ejecutamos `minikube start`

Salida en consola:

```sh
minikube start
😄  minikube v1.33.1 on Debian 12.5
✨  Automatically selected the docker driver
📌  Using Docker driver with root privileges
👍  Starting "minikube" primary control-plane node in "minikube" cluster
🚜  Pulling base image v0.0.44 ...
🔥  Creating docker container (CPUs=2, Memory=2200MB) ...
🐳  Preparing Kubernetes v1.30.0 on Docker 26.1.1 ...
    ▪ Generating certificates and keys ...
    ▪ Booting up control plane ...
    ▪ Configuring RBAC rules ...
🔗  Configuring bridge CNI (Container Networking Interface) ...
🔎  Verifying Kubernetes components...
❗  Executing "docker container inspect minikube --format={{.State.Status}}" took an unusually long time: 3.20223148s
💡  Restarting the docker service may improve performance.
    ▪ Using image gcr.io/k8s-minikube/storage-provisioner:v5
🌟  Enabled addons: storage-provisioner, default-storageclass
🏄  Done! kubectl is now configured to use "minikube" cluster and "default" namespace by default
```

Uso del comando `kubectl apply -f` para aplicar configuraciones.

```sh
kubectl apply -f game-deployment.yaml
kubectl apply -f map-deployment.yaml
kubectl apply -f player-deployment.yaml
kubectl apply -f tower-deployment.yaml
kubectl apply -f wave-deployment.yaml
kubectl apply -f game-service.yaml
kubectl apply -f map-service.yaml
kubectl apply -f player-service.yaml
kubectl apply -f tower-service.yaml
kubectl apply -f wave-service.yaml
```

Salida en consola:

```sh
kubectl apply -f app/src/main/java/docker/kubernetes/microservicios/GameService/game-deployment.yaml 
deployment.apps/game-deployment created
gabriel@192:Docker-Kubernetes-Microservicios$ 
```

Nota:**A partir de aca he tenido problemas tecnicos, mi laptop se ponia muy lenta, era imposible continuar porque tenia que reiniciar en cada momento**

## Mockito

Mock: Un objeto simulado que imita el comportamiento de una clase o interfaz real.

Stub: Definir el comportamiento de un método en un mock.

Salida de pruebas

![test](/Docker-Kubernetes-Microservicios/images/test.png)

Se puede ver a la izquierda los checks verdes, es decir , las pruebas pasaron exitosamente.

## Pitest

Resultados de las pruebas

![pitest](/Docker-Kubernetes-Microservicios/images/pitest.png)
