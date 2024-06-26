# Practica Calificada 5

Esta parte corresponde a lo que es la parte 2 de la actividad de `Docker-Kubernetes-MicroServicios`

## Ejercicio 1

* Describe los principios fundamentales de los contenedores Docker y su arquitectura interna.Explica cómo Docker maneja la seguridad y el aislamiento de contenedores.

Docker es un software que permite desplegar aplicaciones en contenedores

* Compara y contrasta Docker con soluciones de virtualización tradicionales, como VMware y VirtualBox. Discute las ventajas y desventajas de cada enfoque.

La principal diferencia entre Docker para contenerizacion y maq virtuales como VMWare es que contener es mas ligero que virtualizar porque el proceso de simular una maquina involucra la carga del SO y eso demanda recursos. Por otro lado, virtualizacion es mas seguro porque se trabaja en un entorno aislado.

Empezamos creando el proyecto gradle y agregando las clases necesarias.

Archivo Dockerfile

```docker
# Version de la imagen de Java y Alpine
FROM openjdk:17-jdk-alpine3.13
# Directorio de trabajo
WORKDIR /app
# Copiamos el contenido de la carpeta actual a la carpeta de trabajo
COPY . /app
# Compilamos el proyecto
RUN javac app/src/main/java/pc5/cc3s2/*.java
# Ejecutamos el proyecto
#CMD ["java", "-cp", "src/main/java","pc5.cc3s2.TowerDefenseGame"]
CMD ["sh"]
```

Para construir la imagen con el comando

```sh
docker build -t tower-defense-game .
```

Veremos en consola:

```txt
[+] Building 3.1s (9/9) FINISHED                                                                                                    docker:default
 => [internal] load build definition from Dockerfile                                                                                          0.0s
 => => transferring dockerfile: 388B                                                                                                          0.0s
 => [internal] load metadata for docker.io/library/openjdk:17-jdk-alpine3.13                                                                  1.2s
 => [internal] load .dockerignore                                                                                                             0.0s
 => => transferring context: 2B                                                                                                               0.0s
 => [1/4] FROM docker.io/library/openjdk:17-jdk-alpine3.13@sha256:7ee07585addded886cfea377f2ff6d03c707509ef2426b70bd6e1d4e8a3fc7e0            0.0s
 => [internal] load build context                                                                                                             0.0s
 => => transferring context: 179.07kB                                                                                                         0.0s
 => CACHED [2/4] WORKDIR /app                                                                                                                 0.0s
 => [3/4] COPY . /app                                                                                                                         0.1s
 => [4/4] RUN javac app/src/main/java/pc5/cc3s2/*.java                                                                                        1.5s
 => exporting to image                                                                                                                        0.1s
 => => exporting layers                                                                                                                       0.1s
 => => writing image sha256:d5607a9e2aba5ccb406d799095bf0824a388cc072c2864f31d273184386b8902                                                  0.0s
 => => naming to docker.io/library/tower-defense-game
```

Ahora para ver el contenedor

```sh
docker run --rm -it --name tower-defense-container tower-defense-game
```

Veremos en consola

```sh
docker run --rm -it --name tower-defense-container tower-defense-game
/app # 
```

Ahora, en otra terminal,realizamos la ejecucion del comando `exec`

```sh
docker exec -it tower-defense-container /bin/bash
#Resultado
OCI runtime exec failed: exec failed: unable to start container 
process: exec: "bin/bash": stat bin/bash: no such file or directory: unknown
```

Si cambiamos `/bin/bash` por `sh` ya no habra errores.

Ademas si listamos los contenedores con

```sh
docker container ls
```

Veremos

```txt
CONTAINER ID   IMAGE                COMMAND   CREATED         STATUS         PORTS     NAMES
3ca47a3dcfd4   tower-defense-game   "sh"      3 minutes ago   Up 3 minutes             tower-defense-container
```

## Ejercicio 2 (redes y volumenes)

## Redes en Docker

Ejecucion del comando

```sh
docker network create game-network
# Resultado
c23c83b51f2980510e3a842ef332f140d9b41eb764d2394a6d4568adfe4e8b3c
```

## Volumenes en Docker

Para crear un volumen en Docker se usa la instruccion

```sh
docker volume create game-data
```

Para listar los volumenes y verificar su creacion con

```sh
docker volume ls
```

Ahora ejcutamos el comando

```sh
docker run -it --name tower-defense-container --network game-network -v game-data:/app/data tower-defense-game
```

Y se abrira una shell interactiva

## Docker compose

Docker Compose es una herramienta para definir y ejecutar aplicaciones Docker multi-contenedor. Con un archivo YAML, se pueden configurar los servicios, redes y volúmenes necesarios para la aplicación.

Se requiere haber instalado docker-compose, verificamos si tenemos docker-compose con

```sh
docker-compose --version
```

Aparece Docker Compose version v2.28.1

Ahora corremos el comando

```sh
docker-compose up -d
```

en la ruta de nuestro trabajo.

Deberiamos ver una salida similar a esta:

```txt
WARN[0000] /home/gabriel/Documents/UNI/24_1/DesarrolloSoftware/CC3S2/PC5-CC3S2/docker-compose.yml: `version` is obsolete 
[+] Running 3/3
 ✔ Network pc5-cc3s2_game-network  Created               0.1s 
 ✔ Volume "pc5-cc3s2_game-data"    Created               0.0s 
 ✔ Container pc5-cc3s2-game-1      Started               0.9s
```

## Docker Swarm

Docker Swarm es una herramienta nativa de Docker para la orquestación de contenedores, permitiendo
la gestión de un clúster de Docker.

## Orquestacion con Kubernetes

### Uso de Minikube

Lo usaremos para crear un cluster de Kubernetes en un contenedor.

Comando

```sh
minikube start
```

En caso de error(me paso) puedes poner lo sgte

```sh
minikube stop
minikube delete
minikube start
```

Veamos si podemos acceder al cluster con

```sh
kubectl config get-contexts
```

Salida en consola

```sh
kubectl config get-contexts
CURRENT   NAME       CLUSTER    AUTHINFO   NAMESPACE
*         minikube   minikube   minikube   default
```

```sh
kubectl get nodes
NAME       STATUS   ROLES           AGE     VERSION
minikube   Ready    control-plane   3m30s   v1.30.0
```

Antes de la ejecucion de los files `.yaml`, su configuracion:

* deployment.yaml

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: tower-defense-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: tower-defense-game
  template:
    metadata:
      labels:
        app: tower-defense-game
    spec:
      containers:
        - name: tower-defense-game
          image: tower-defense-game
          ports:
            - containerPort: 8080
```

* services.yaml

```yaml
apiVersion: v1
kind: Service
metadata:
  name: tower-defense-service
spec:
  selector:
    app: tower-defense-game
  ports:
  - protocol: TCP
    port: 80
    targetPort: 8080
  type: LoadBalancer
```

Ahora la ejecucion en el cluster con los files de extension `.yaml`

```sh
kubectl apply -f service.yaml
kubectl apply -f deployment.yaml
```

Deberia aparecer algo como esto:

```sh
gabriel@192:PC5-CC3S2$ kubectl apply -f service.yaml 
service/tower-defense-service created
gabriel@192:PC5-CC3S2$ kubectl apply -f deployment.yaml 
deployment.apps/tower-defense-deployment created
```

## Pods

```sh
gabriel@192:PC5-CC3S2$ kubectl get pods
NAME                                        READY   STATUS             RESTARTS   AGE
tower-defense-deployment-749d7bbdcd-8tmb5   0/1     ImagePullBackOff   0          4m5s
gabriel@192:PC5-CC3S2$ kubectl get services
NAME                    TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE
kubernetes              ClusterIP      10.96.0.1       <none>        443/TCP        15m
tower-defense-service   LoadBalancer   10.105.150.15   <pending>     80:30602/TCP   7m12s
gabriel@192:PC5-CC3S2$ kubectl get deployments
NAME                       READY   UP-TO-DATE   AVAILABLE   AGE
tower-defense-deployment   0/1     1            0           7m20s
```

## Deployment

Usamos el comando `kubectl get pods` y extraemos el nombre del pod, en este caso es `tower-defense-deployment-749d7bbdcd-8tmb5`

```sh
kubectl expose pod tower-defense-deployment-749d7bbdcd-8tmb5 --type=NodePort --port=80
```

Nos debe salir en consola algo similar a

```sh
service/tower-defense-deployment-749d7bbdcd-8tmb5 exposed
```

Ahora corremos el comando

```sh
kubectl get services
```

Debe salir algo como

```sh
NAME                                        TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE
kubernetes                                  ClusterIP      10.96.0.1       <none>        443/TCP        29m
tower-defense-deployment-749d7bbdcd-8tmb5   NodePort       10.101.75.207   <none>        80:31569/TCP   2m18s
tower-defense-service                       LoadBalancer   10.105.150.15   <pending>     80:30602/TCP   21m
```

Luego colocamos el comando

```sh
minikube service tower-defense-deployment-749d7bbdcd-8tmb5
```

## Pruebas unitarias y de integracion con Mockito

### Teoria

* Explica los conceptos de mocks, stubs y fakes. Discute cuándo y cómo se deben utilizar estos patrones en las pruebas unitarias.
* * Mocks sirven para verificar llamadas a ciertos metodos.
* * Stubs brindan respuestas a las llamadas por prueba.
* * Fakes funcionan de forma independiente pero no necesariamente simula toda la funcionalidad del objeto.

Implementacion de los test con Mockito

```java
@BeforeEach
    public void setUp() {
        towerDefenseGame = new TowerDefenseGame();
        mockMap = Mockito.mock(Map.class);
        player = Mockito.mock(Player.class);
        wave = Mockito.mock(Wave.class);
    }

    // Pruebas para map
    @Test
    public void testPlaceTower() {
        Tower tower = new Tower('T');
        towerDefenseGame.placeTower(tower, 0, 0);
        verify(mockMap).placeTower(eq(tower), eq(0), eq(0));
    }
    // Pruebas para player
    @Test
    public void testGameState() {
        when(player.getScore()).thenReturn(0);
        when(player.getBaseHealth()).thenReturn(100);
        towerDefenseGame.gameState();
        verify(player).getScore();
        verify(player).getBaseHealth();
    }
    // Pruebas para waves
    @Test
    public void testStartWave() {
        towerDefenseGame.startWave();
        verify(wave).start();
    }
```

Resultados de las pruebas :

![mocktest](/images/MockTest.png)

## Pruebas de mutacion

## Dise;o por contrato
