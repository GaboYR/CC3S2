# Play With Docker

Realizaremos la actividad correspondiente al Stage1 y 2 ubicados en `https://training.play-with-docker.com/`

## Stage 1(The basics)

Este stage tiene 2 secciones

* Fundamentals of Docker
* Deploying a multi-service application

### Fundamentals of Docker

Esta seccion conta con 3 partes

* Your First Linux Containers
* Customizing Docker Images
* Deploy and Managing Multiple Containers

#### Your First Linux Containers

Veremos en el navegador

![im1](/images/im1.png)

##### 1.0 Running your first container

En esta seccion, como buena costumbre de *lenguaje de programacion*, ***ejecutaremos*** un `hello-world`.
Nos muestra como ejecutar la imagen `hello-world` que hicimos en clase.

![im2](/images/im2.png)

##### 1.1 Docker Images

Tenemos que traer la imagen de `Alpine` y luego correrla, para traer la imagen ejecutamos el comando

```sh
docker image pull alpine
```

Salida en consola:

![im3](/images/im3.png)

Para ver que imagenes tenemos en el sistema corremos el comando

```sh
docker image ls
```

Salida en consola:

![im4](/images/im4.png)

Podemos ver la imagen traida con `pull` y la de `hello-world` que fue ejecutada previamente.

##### 1.1.1 Docker container run

Ejecutamos el comando

```sh
docker container run alpine ls -l
```

Salida en consola:

![im5](/images/im5.png)

Como funciona este comando

-- Primero se corre la imagen con `docker container run`, y se usara la imagen de `alpine`, la cual es una imagen de Linux. Finalmente, dentro del contenedor se ejecutara los comandos `ls -l` la cual lista los archivos en el directorio de trabajo con suficientes detalles.

Esta instruccion

```sh
[node1] (local) root@192.168.0.13 ~
$ docker container run alpine echo "hello from alpine"
hello from alpine
[node1] (local) root@192.168.0.13
```

De la misma forma que la anterior, corre la imagen de alpine y dentro del contenedor se ejecutara el comando `echo ...`, lo cual imprimira en consola el mensaje que le estamos entregando.

Ahora probaremos con el comando

```sh
docker container run alpine /bin/sh
```

Parece que no paso nada, pero en realidad se inicio una shell, cerro la shell y se detuvo el contenedor, para evitar este *problema*, agregamos `-it` a la instruccion

```sh
docker container run -it alpine /bin/sh
```

En la consola aparecera lo sgte

```sh
[node1] (local) root@192.168.0.13 ~
$  docker container run -it alpine /bin/sh
/ #
```

Estamos en la shell del contenedor, y podemos ejecutar comandos como si fuese una terminal de linux.

Que diferencia hay entre los comandos

```sh
docker container ls
# Y
docker container ls -a
```

```sh
[node1] (local) root@192.168.0.13 ~
$ docker container lsCONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[node1] (local) root@192.168.0.13 ~
$ docker container ls -a
CONTAINER ID   IMAGE         COMMAND                  CREATED          STATUS                      POR
TS     NAMES
b8b3f59e4fc4   alpine        "/bin/sh"                4 minutes ago    Exited (0) 48 seconds ago
       beautiful_wing
eb1dc2daaceb   alpine        "/bin/sh"                6 minutes ago    Exited (0) 6 minutes ago
       relaxed_driscoll
c2d99d535cb8   alpine        "/bin/sh"                10 minutes ago   Exited (0) 6 minutes ago
       gifted_khorana
26c2a50da8eb   alpine        "echo 'hello from al…"   12 minutes ago   Exited (0) 12 minutes ago
       peaceful_darwin
615d8dfa9fa2   alpine        "/bin/sh"                17 minutes ago   Exited (0) 17 minutes ago
       gracious_meitner
ea48ae1df951   alpine        "ls -l"                  18 minutes ago   Exited (0) 18 minutes ago
       agitated_easley
2fad6c2b257c   alpine        "ls -a"                  18 minutes ago   Exited (0) 18 minutes ago
       dreamy_torvalds
07df805bb073   hello-world   "/hello"                 19 minutes ago   Exited (0) 19 minutes ago
       elegant_neumann
[node1] (local) root@192.168.0.13 ~
```

Si ponemos `ls`, solo mostrara los contenedores con `STATUS = Running`, es decir, aquellos contenedores que esten en ejecucion, mientras que `ls -a` mostrara todos los contenedores sin importar el `STATUS` que tenga.

#### Container Isolation

Eejcutamos el comando

```sh
docker container run -it alpine /bin/bash
```

Esto abrira una shell dentro del contenedor, y podemos ejecutar algunos comandos de linux, para este caso usaremos el comando `echo` y haremos una redireccion de salida a un archivo de texto `hello.txt`

```sh
[node1] (local) root@192.168.0.13 ~$ docker container run -it alpine /bin/ash
/ # echo "hello world" > hello.txt
/ # ls
bin        etc        home       media      opt        root       sbin       sys        usr
dev        hello.txt  lib        mnt        proc       run        srv        tmp        var
/ # cat hello.txt
hello world
/ # exit
[node1] (local) root@192.168.0.13
```

El operador `>` redirige la salida al argumento que esta a la derecha de este, si no existe el archivo, lo creara.

Ahora que pasa si salimos de la shell y corremos el contenedor nuevamente.

```sh
docker container run alpine ls
```

Salida en consola:

```sh
root@192.168.0.13 ~$ docker container run alpine ls
bindev
etc
home
lib
media
mnt
opt
proc
root
run
sbin
srv
sys
tmp
usr
var
[node1] (local) root@192.168.0.13 ~
```

No aparece el archivo `hello.txt`.Eso se debe al aislamiento de contenedores de Docker, es decir, que se crea una nueva instancia pero basada en la misma imagen.
Pero como accedemos al contenedor que contiene nuestro archivo de texto.

ejecutamos el comando para listar todos los contenedores existentes en el sistema.

```sh
[node1] (local) root@192.168.0.13 ~
$ docker container ls -a
CONTAINER ID   IMAGE         COMMAND                  CREATED          STATUS                      POR
TS     NAMES
54fbbc6a9032   alpine        "ls"                     2 minutes ago    Exited (0) 2 minutes ago
       goofy_beaver
2410cbdccb21   alpine        "/bin/sh"                5 minutes ago    Exited (0) 5 minutes ago
       flamboyant_brahmagupta
2b19cd550604   alpine        "sh"                     5 minutes ago    Exited (0) 5 minutes ago
       infallible_blackwell
6944609ad140   alpine        "ls"                     6 minutes ago    Exited (0) 6 minutes ago
       focused_einstein
f547c296a156   alpine        "/bin/ash"               16 minutes ago   Exited (0) 15 minutes ago
       sleepy_napier
16c50c2bede6   alpine        "/bin/sh"                16 minutes ago   Exited (0) 16 minutes ago
       stupefied_ganguly
0294809d570d   alpine        "/bin/sh"                20 minutes ago   Exited (0) 20 minutes ago
       dazzling_lehmann
b8b3f59e4fc4   alpine        "/bin/sh"                44 minutes ago   Exited (0) 41 minutes ago
       beautiful_wing
eb1dc2daaceb   alpine        "/bin/sh"                47 minutes ago   Exited (0) 47 minutes ago
       relaxed_driscoll
c2d99d535cb8   alpine        "/bin/sh"                50 minutes ago   Exited (0) 47 minutes ago       gifted_khorana
26c2a50da8eb   alpine        "echo 'hello from al…"   52 minutes ago   Exited (0) 52 minutes ago       peaceful_darwin
615d8dfa9fa2   alpine        "/bin/sh"                57 minutes ago   Exited (0) 57 minutes ago       gracious_meitner
ea48ae1df951   alpine        "ls -l"                  59 minutes ago   Exited (0) 59 minutes ago       agitated_easley
2fad6c2b257c   alpine        "ls -a"                  59 minutes ago   Exited (0) 59 minutes ago       dreamy_torvalds
07df805bb073   hello-world   "/hello"                 59 minutes ago   Exited (0) 59 minutes ago       elegant_neumann
[node1] (local) root@192.168.0.13 ~
```

Para acceder al contenedor que contiene el file `hello.txt` debemos acceder mediante su `CONTAINER ID`.

Esta es la instuciion:

```sh
docker container start <container_id>
```

Tambien podemos ejecutar

```sh
docker container exec <container_id> ls
```

### Customizing Docker images

#### Image creation from a container

Ahora veamos como personalizar imagenes.

Lo primero que haremos es bajar una imagen, en este caso la de `ubuntu`

```sh
[node1] (local) root@192.168.0.8 ~
$ docker container run -ti ubuntu bash
Unable to find image 'ubuntu:latest' locally
latest: Pulling from library/ubuntu
9c704ecd0c69: Pull complete
Digest: sha256:2e863c44b718727c860746568e1d54afd13b2fa71b160f5cd9058fc436217b30
Status: Downloaded newer image for ubuntu:latest
root@b2f0f878487e:/# apt-get update
Get:1 http://security.ubuntu.com/ubuntu noble-security InRelease [126 kB]
Get:2 http://archive.ubuntu.com/ubuntu noble InRelease [256 kB]
Get:3 http://archive.ubuntu.com/ubuntu noble-updates InRelease [126 kB]
Get:4 http://archive.ubuntu.com/ubuntu noble-backports InRelease [126 kB]
Get:5 http://security.ubuntu.com/ubuntu noble-security/multiverse amd64 Packages [12.7 kB]
Get:6 http://security.ubuntu.com/ubuntu noble-security/main amd64 Packages [227 kB]
Get:7 http://security.ubuntu.com/ubuntu noble-security/restricted amd64 Packages [168 kB]
Get:8 http://security.ubuntu.com/ubuntu noble-security/universe amd64 Packages [73.2 kB]
Get:9 http://archive.ubuntu.com/ubuntu noble/multiverse amd64 Packages [331 kB]
Get:10 http://archive.ubuntu.com/ubuntu noble/main amd64 Packages [1808 kB]
Get:11 http://archive.ubuntu.com/ubuntu noble/restricted amd64 Packages [117 kB]
Get:12 http://archive.ubuntu.com/ubuntu noble/universe amd64 Packages [19.3 MB]
Get:13 http://archive.ubuntu.com/ubuntu noble-updates/multiverse amd64 Packages [16.9 kB]
Get:14 http://archive.ubuntu.com/ubuntu noble-updates/main amd64 Packages [260 kB]
Get:15 http://archive.ubuntu.com/ubuntu noble-updates/restricted amd64 Packages [168 kB]
Get:16 http://archive.ubuntu.com/ubuntu noble-updates/universe amd64 Packages [121 kB]
Get:17 http://archive.ubuntu.com/ubuntu noble-backports/universe amd64 Packages [9705 B]
Fetched 23.3 MB in 2s (13.2 MB/s)
Reading package lists... Done
```

Ahora instalamos el package `figlet`.

```sh
root@b2f0f878487e:/# apt-get install -y figlet
Reading package lists... Done
Building dependency tree... Done
Reading state information... Done
The following NEW packages will be installed:
  figlet
0 upgraded, 1 newly installed, 0 to remove and 0 not upgraded.
Need to get 133 kB of archives.
After this operation, 752 kB of additional disk space will be used.
Get:1 http://archive.ubuntu.com/ubuntu noble/universe amd64 figlet amd64 2.2.5-3 [133 kB]
Fetched 133 kB in 0s (1617 kB/s)
debconf: delaying package configuration, since apt-utils is not installed
Selecting previously unselected package figlet.
(Reading database ... 4376 files and directories currently installed.)
Preparing to unpack .../figlet_2.2.5-3_amd64.deb ...
Unpacking figlet (2.2.5-3) ...
Setting up figlet (2.2.5-3) ...
update-alternatives: using /usr/bin/figlet-figlet to provide /usr/bin/figlet (figlet) in auto mode
update-alternatives: warning: skip creation of /usr/share/man/man6/figlet.6.gz because associated file /usr/share/man/man6/figlet-figlet.6.gz (of link group figlet) doesn't exist
```

Ahora mandamos un mensaje usando `figlet`

```sh
root@b2f0f878487e:/# figlet "hello docker"
 _          _ _             _            _
| |__   ___| | | ___     __| | ___   ___| | _____ _ __
| '_ \ / _ \ | |/ _ \   / _` |/ _ \ / __| |/ / _ \ '__|
| | | |  __/ | | (_) | | (_| | (_) | (__|   <  __/ |
|_| |_|\___|_|_|\___/   \__,_|\___/ \___|_|\_\___|_|

root@b2f0f878487e:/#
```

Y salimos de la shell con `exit`

Como nuestro contenedor no esta ene ejecucion, podemos verlo con

```sh
docker container ls -a
```

```sh
[node1] (local) root@192.168.0.8 ~
$ docker container ls -a
CONTAINER ID   IMAGE     COMMAND   CREATED          STATUS                          PORTS     NAMES
b2f0f878487e   ubuntu    "bash"    12 minutes ago   Exited (0) About a minute ago             hopeful_archimedes
```

Vemos que la id es `b2f0...`

Ahora, con `commit` creamos una imagen de Docker a partir de un contenedor.

Ejecutamos el commit con la id del contenedor

```sh
[node1] (local) root@192.168.0.8 ~
$ docker container commit b2f0
sha256:11650bd64865d6db23a3239d2e7e9eed269c0c5b0f362fcba0d9992b4812208b
[node1] (local) root@192.168.0.8 ~
```

Listamos las imagenes con

```sh
$ docker image ls
REPOSITORY   TAG       IMAGE ID       CREATED          SIZE
<none>       <none>    11650bd64865   12 seconds ago   117MB
ubuntu       latest    35a88802559d   3 weeks ago      78.1MB
```

Uso de `tag` para cambiar la etiqueta de una imagen y `ls` para listar las imagenes

```sh
[node1] (local) root@192.168.0.8 ~
$ docker image tag 1165 outfiglet
[node1] (local) root@192.168.0.8 ~
$ docker image ls
REPOSITORY   TAG       IMAGE ID       CREATED              SIZE
outfiglet    latest    11650bd64865   About a minute ago   117MB
ubuntu       latest    35a88802559d   3 weeks ago          78.1MB
[node1] (local) root@192.168.0.8 ~
```

Ahora podemos correr nuestro contenedor basada en la imagen de `outfiglet`

```sh
$ docker container run out
outfiglet         outfiglet:latest
[node1] (local) root@192.168.0.8 ~
$ docker container run outfiglet figlet hello
 _          _ _
| |__   ___| | | ___
| '_ \ / _ \ | |/ _ \
| | | |  __/ | | (_) |
|_| |_|\___|_|_|\___/

[node1] (local) root@192.168.0.8 ~
```

#### Image creation using a Dockerfile

Ahora automatizaremos la creacion de imagenes mediante Dockerfile, que permite ello mediante una secuencia de instrucciones dadas.

Entonces primero instalamos `nano` con

```sh
apk add nano
```

Creamos un directorio `TestDockerfile` con

```sh
node1] (local) root@192.168.0.8 ~
$ mkdir TestDockerfile
```

Nos ubicamos en el directorio `TestDockerfile`

```sh
[node1] (local) root@192.168.0.8 ~
$ cd TestDockerfile/
```

Creamos y editamos el archivo `index.js`, le agregamos lo sgte

```js
var os = require("os");
var hostname = os.hostname();
console.log("hello from " + hostname);
```

```sh
[node1] (local) root@192.168.0.8 ~/TestDockerfile
$ touch index.js
[node1] (local) root@192.168.0.8 ~/TestDockerfile
$ nano index.js
```

Creamos y editamos el archivo `Dockerfile`, le agregamos

```docker
FROM alpine
RUN apk update && apk add nodejs
COPY . /app
WORKDIR /app
CMD ["node","index.js"]
```

```sh
[node1] (local) root@192.168.0.8 ~/TestDockerfile
$ touch Dockerfile
[node1] (local) root@192.168.0.8 ~/TestDockerfile
$ nano Dockerfile
[node1] (local) root@192.168.0.8 ~/TestDockerfile
```

Despues de ello, tenemos que contruir la imagen mediante

```sh
[node1] (local) root@192.168.0.8 ~/TestDockerfile
$ docker image build -t hello:v0.1 .
[+] Building 4.3s (9/9) FINISHED                                                       docker:default
 => [internal] load .dockerignore                                                                0.0s
 => => transferring context: 2B                                                                  0.0s
 => [internal] load build definition from Dockerfile                                             0.0s
 => => transferring dockerfile: 134B                                                             0.0s
 => [internal] load metadata for docker.io/library/alpine:latest                                 0.3s
 => [1/4] FROM docker.io/library/alpine@sha256:b89d9c93e9ed3597455c90a0b88a8bbb5cb7188438f70953  0.4s
 => => resolve docker.io/library/alpine@sha256:b89d9c93e9ed3597455c90a0b88a8bbb5cb7188438f70953  0.0s
 => => extracting sha256:ec99f8b99825a742d50fb3ce173d291378a46ab54b8ef7dd75e5654e2a296e99        0.2s
 => => sha256:b89d9c93e9ed3597455c90a0b88a8bbb5cb7188438f70953fede212a0c4394e0 1.85kB / 1.85kB   0.0s
 => => sha256:dabf91b69c191a1a0a1628fd6bdd029c0c4018041c7f052870bb13c5a222ae76 528B / 528B       0.0s
 => => sha256:a606584aa9aa875552092ec9e1d62cb98d486f51f389609914039aabd9414687 1.47kB / 1.47kB   0.0s
 => => sha256:ec99f8b99825a742d50fb3ce173d291378a46ab54b8ef7dd75e5654e2a296e99 3.62MB / 3.62MB   0.1s
 => [internal] load build context                                                                0.0s
 => => transferring context: 264B                                                                0.0s
 => [2/4] RUN apk update && apk add nodejs                                                       2.9s
 => [3/4] COPY . /app                                                                            0.1s
 => [4/4] WORKDIR /app                                                                           0.0s
 => exporting to image                                                                           0.5s
 => => exporting layers                                                                          0.5s
 => => writing image sha256:c17ac1a4a9484b0889c16af1d21836a1c376e4ce411f35cf3f1c3968043bc030     0.0s
 => => naming to docker.io/library/hello:v0.1                                                    0.0s
```

Verificamos que no haya errores durante la creacion de la imagen, si todo esta bien, podemos correr el contenedor basado en la imagen de `hello:v0.1`

```sh
[node1] (local) root@192.168.0.8 ~/TestDockerfile
$ docker container run hello:v0.1
hello from 7433b6de1261
[node1] (local) root@192.168.0.8 ~/TestDockerfile
```

Como trabaja el `Dockerfile`

```docker
# Busca la imagen base y le hace `pull`
FROM alpine
#Despues corre dos comandos, para actualizar y para instalar nodejs
RUN apk update && apk add nodejs
#Despues se copia los archivos de nuestro directorio(.) al del contenedor
COPY . /app
#Debemos especificar el directorio de trabajo que el contenedor usara cuando se inicie (run)
WORKDIR /app
#Una vez se de run, con cmd especificamos que comandos queremos que se ejecuten
CMD ["node","index.js"]
```

#### Image layers

Revisemos algunos detalles de nuestra imagen anterior

```sh
[node1] (local) root@192.168.0.8 ~/TestDockerfile
$ docker image history c17
IMAGE          CREATED          CREATED BY                                      SIZE      COMMENT
c17ac1a4a948   25 minutes ago   CMD ["node" "index.js"]                         0B        buildkit.dockerfile.v0
<missing>      25 minutes ago   WORKDIR /app                                    0B        buildkit.dockerfile.v0
<missing>      25 minutes ago   COPY . /app # buildkit                          190B      buildkit.dockerfile.v0
<missing>      25 minutes ago   RUN /bin/sh -c apk update && apk add nodejs …   58.2MB    buildkit.dockerfile.v0
<missing>      8 days ago       /bin/sh -c #(nop)  CMD ["/bin/sh"]              0B
<missing>      8 days ago       /bin/sh -c #(nop) ADD file:33ebe56b967747a97…   7.8MB
[node1] (local) root@192.168.0.8 ~/TestDockerfile
```

Para la version 2 de la imagen, modificamos el archivo `index.js`

```sh
echo "console.log(\"this is v0.2\");" >> index.js
```

Y la construimos con

```sh
docker image build -t hello:v0.2 .
```

```sh
Sending build context to Docker daemon  86.15MB
Step 1/5 : FROM alpine
 ---> 7328f6f8b418
Step 2/5 : RUN apk update && apk add nodejs
 ---> Using cache
 ---> 2707762fca63
Step 3/5 : COPY . /app
 ---> 07b2e2127db4
Removing intermediate container 84eb9c31320d
Step 4/5 : WORKDIR /app
 ---> 6630eb76312c
Removing intermediate container ee6c9e7a5337
Step 5/5 : CMD node index.js
 ---> Running in e079fb6000a3
 ---> e536b9dadd2f
Removing intermediate container e079fb6000a3
Successfully built e536b9dadd2f
Successfully tagged hello:v0.2
```

#### Image inspection

Para ver detalles sobre un contenedor que estamos consumiendo podemos usar `inspect`.

Usaremos la imagen de `apline` como ejemplo

```sh
[node1] (local) root@192.168.0.8 ~
$ docker image pull alpine
Using default tag: latest
latest: Pulling from library/alpine
ec99f8b99825: Already exists
Digest: sha256:b89d9c93e9ed3597455c90a0b88a8bbb5cb7188438f70953fede212a0c4394e0
Status: Downloaded newer image for alpine:latest
docker.io/library/alpine:latest
[node1] (local) root@192.168.0.8 ~
$ docker image inspect alpine
[
    {
        "Id": "sha256:a606584aa9aa875552092ec9e1d62cb98d486f51f389609914039aabd9414687",
        "RepoTags": [
            "alpine:latest"
        ],
        "RepoDigests": [
            "alpine@sha256:b89d9c93e9ed3597455c90a0b88a8bbb5cb7188438f70953fede212a0c4394e0"
        ],
        "Parent": "",
        "Comment": "",
        "Created": "2024-06-20T20:16:58.064410339Z",
        "Container": "8c42de2551524ce87f88a98ea6caca568cd8705aaee2503e67303f7e7f1fdee0",
        "ContainerConfig": {
            "Hostname": "8c42de255152",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
            ],
            "Cmd": [
                "/bin/sh",
                "-c",
                "#(nop) ",
                "CMD [\"/bin/sh\"]"
            ],
            "Image": "sha256:18dd80d51c0c4849a695d48973ccde063e1d528c9b5d12204208a0eb486cbe04",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": {}
        },
        "DockerVersion": "23.0.11",
        "Author": "",
        "Config": {
            "Hostname": "",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
            ],
            "Cmd": [
                "/bin/sh"
            ],
            "Image": "sha256:18dd80d51c0c4849a695d48973ccde063e1d528c9b5d12204208a0eb486cbe04",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": null
        },
        "Architecture": "amd64",
        "Os": "linux",
        "Size": 7798812,
        "VirtualSize": 7798812,
        "GraphDriver": {
            "Data": {
                "MergedDir": "/var/lib/docker/overlay2/a0faf2e18e050d20be0801be516f9adf5aa8b72edcdf39eed332ed9e5e433cd8/merged",
                "UpperDir": "/var/lib/docker/overlay2/a0faf2e18e050d20be0801be516f9adf5aa8b72edcdf39eed332ed9e5e433cd8/diff",
                "WorkDir": "/var/lib/docker/overlay2/a0faf2e18e050d20be0801be516f9adf5aa8b72edcdf39eed332ed9e5e433cd8/work"
            },
            "Name": "overlay2"
        },
        "RootFS": {
            "Type": "layers",
            "Layers": [
                "sha256:94e5f06ff8e3d4441dc3cd8b090ff38dc911bfa8ebdb0dc28395bc98f82f983f"
            ]
        },
        "Metadata": {
            "LastTagTime": "0001-01-01T00:00:00Z"
        }
    }
]
[node1] (local) root@192.168.0.8 ~
$ docker image inspect --format "{{ json .RootFS.Layers }}" alpine
["sha256:94e5f06ff8e3d4441dc3cd8b090ff38dc911bfa8ebdb0dc28395bc98f82f983f"]
[node1] (local) root@192.168.0.8 ~
```

Podemos ver detalles de todo tipo. Para el problema listamos las capas.

### Deploy and managing multiple containers

#### Initialize Your Swarm

De momento se trabajo con contenedores de forma local, pero para que sea escalable necesitamos de herramientas mas especializadas como `Docker Compose` o `Docker Swarm`

Entonces, para inicializar `swarm`, escribimos

```sh
[node1] (local) root@192.168.0.23 ~
$ docker swarm init --advertise-addr $(hostname -i)
Swarm initialized: current node (kfef25vu1n25na9xakxgzbhtq) is now a manager.

To add a worker to this swarm, run the following command:

    docker swarm join --token SWMTKN-1-5kp06n7dpdpqvjw1qkul9kxip00znx6pbc95tw0s0vsl55ge3y-cwb0ntqmb8cauivyd5xty11gc 192.168.0.23:2377

To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.

[node1] (local) root@192.168.0.23 ~
```

Para listar los miembros de `swarm` con `ls`, y apareceria solo el host.

```sh
[node1] (local) root@192.168.0.23 ~
$ docker node ls
ID                            HOSTNAME   STATUS    AVAILABILITY   MANAGER STATUS   ENGINE VERSION
kfef25vu1n25na9xakxgzbhtq *   node1      Ready     Active         Leader           24.0.7
[node1] (local) root@192.168.0.23 ~
$
```

Ahora nos uniremos al entorno swarm mediante el mensaje que nos dieron previamente

Entonces en el nodo2 corremos el comando

```sh
docker swarm join --token SWMTKN-1-5kp06n7dpdpqvjw1qkul9kxip00znx6pbc95tw0s0vsl55ge3y-cwb0ntqmb8cauivyd5xty11gc 192.168.0.23:2377
```

#### Show Swarm Members

Resultados:

```sh
[node2] (local) root@192.168.0.22 ~
$ docker swarm join --token SWMTKN-1-5kp06n7dpdpqvjw1qkul9kxip00znx6pbc95tw0s0vsl55ge3y-cwb0ntqmb8cauivyd5xty11gc 192.168.0.23:2377
This node joined a swarm as a worker.
[node2] (local) root@192.168.0.22 
```

Si ponemos `ls` nuevamente,veremos que se unio el nodo2 al swarm.

```sh
[node1] (local) root@192.168.0.23 ~
$ docker node ls
ID                            HOSTNAME   STATUS    AVAILABILITY   MANAGER STATUS   ENGINE VERSION
kfef25vu1n25na9xakxgzbhtq *   node1      Ready     Active         Leader           24.0.7
3gxom2pp74lilbv98vkhgelhi     node2      Ready     Active                          24.0.7
[node1] (local) root@192.168.0.23 ~
```

#### Clone the voting app

Clonamos una app de votacion desde el nodo1

```sh
 root@192.168.0.23 ~
$ git clone https://github.com/docker/example-voting-app
Cloning into 'example-voting-app'...
remote: Enumerating objects: 1140, done.
remote: Total 1140 (delta 0), reused 0 (delta 0), pack-reused 1140
Receiving objects: 100% (1140/1140), 1.19 MiB | 16.09 MiB/s, done.
Resolving deltas: 100% (435/435), done.
[node1] (local) root@192.168.0.23 ~
$ cd example-voting-app
[node1] (local) root@192.168.0.23 ~/example-voting-app
```

#### Deploy a stack

Revisemos el contenido de `docker-stack.yml`

```sh
[node1] (local) root@192.168.0.23 ~/example-voting-app
$ cat docker-stack.yml
# this file is meant for Docker Swarm stacks only
# trying it in compose will fail because of multiple replicas trying to bind to the same port
# Swarm currently does not support Compose Spec, so we'll pin to the older version 3.9

version: "3.9"

services:

  redis:
    image: redis:alpine
    networks:
      - frontend

  db:
    image: postgres:15-alpine
    environment:
      POSTGRES_USER: "postgres"
      POSTGRES_PASSWORD: "postgres"
    volumes:
      - db-data:/var/lib/postgresql/data
    networks:
      - backend

  vote:
    image: dockersamples/examplevotingapp_vote
    ports:
      - 5000:80
    networks:
      - frontend
    deploy:
      replicas: 2

  result:
    image: dockersamples/examplevotingapp_result
    ports:
      - 5001:80
    networks:
      - backend

  worker:
    image: dockersamples/examplevotingapp_worker
    networks:
      - frontend
      - backend
    deploy:
      replicas: 2

networks:
  frontend:
  backend:

volumes:
  db-data:
[node1] (local) root@192.168.0.23 ~/example-voting-app
```

Hacemos un deploy de la app mediante el archivo `.yml` y listamos con `ls`.

```sh
[node1] (local) root@192.168.0.23 ~/example-voting-app
$ docker stack deploy --compose-file=docker-stack.yml voting_stack
Creating network voting_stack_frontend
Creating network voting_stack_backend
Creating service voting_stack_vote
Creating service voting_stack_result
Creating service voting_stack_worker
Creating service voting_stack_redis
Creating service voting_stack_db
[node1] (local) root@192.168.0.23 ~/example-voting-app
$ docker stack ls
NAME           SERVICES
voting_stack   5
```

Para ver los detalles de cada servicio usamos el comando

```sh
[node1] (local) root@192.168.0.23 ~/example-voting-app
$ docker stack services voting_stack
ID             NAME                  MODE         REPLICAS   IMAGE                                          PORTS
4vkygmiyly6e   voting_stack_db       replicated   1/1        postgres:15-alpine
xdgsso0rqsms   voting_stack_redis    replicated   1/1        redis:alpine
vuzj2kvmiljs   voting_stack_result   replicated   1/1        dockersamples/examplevotingapp_result:latest   *:5001->80/tcp
pbsodxe1ym0k   voting_stack_vote     replicated   2/2        dockersamples/examplevotingapp_vote:latest     *:5000->80/tcp
j9tvnpbk3adm   voting_stack_worker   replicated   2/2        dockersamples/examplevotingapp_worker:latest
```

Para listar las tareas del vote service usamos

```sh
[node1] (local) root@192.168.0.23 ~/example-voting-app
$ docker service ps voting_stack_vote
ID             NAME                  IMAGE                                        NODE      DESIRED STATE   CURRENT STATE                ERROR     PORTS
3j3c4rdpbav7   voting_stack_vote.1   dockersamples/examplevotingapp_vote:latest   node2     Running         Running about a minute ago
sfk7358h5j98   voting_stack_vote.2   dockersamples/examplevotingapp_vote:latest   node1     Running         Running about a minute ago
```

#### Scaling An Application

Para agregar o modificar el nro de replicas de nuestro servivio de votacion ejecutamos

```sh
[node1] (local) root@192.168.0.23 ~/example-voting-app
$ docker service scale voting_stack_vote=5
voting_stack_vote scaled to 5
overall progress: 5 out of 5 tasks
1/5: running   [==================================================>]
2/5: running   [==================================================>]
3/5: running   [==================================================>]
4/5: running   [==================================================>]
5/5: running   [==================================================>]
verify: Service converged
[node1] (local) root@192.168.0.23 ~/example-voting-app
```

Veremos el cambio de nro de replicas a 5, para verificarlo ponemos el comando

```sh
[node1] (local) root@192.168.0.23 ~/example-voting-app
$ docker stack services voting_stack
ID             NAME                  MODE         REPLICAS   IMAGE                                          PORTS
4vkygmiyly6e   voting_stack_db       replicated   1/1        postgres:15-alpine
xdgsso0rqsms   voting_stack_redis    replicated   1/1        redis:alpine
vuzj2kvmiljs   voting_stack_result   replicated   1/1        dockersamples/examplevotingapp_result:latest   *:5001->80/tcp
pbsodxe1ym0k   voting_stack_vote     replicated   5/5        dockersamples/examplevotingapp_vote:latest     *:5000->80/tcp
j9tvnpbk3adm   voting_stack_worker   replicated   2/2        dockersamples/examplevotingapp_worker:latest
[node1] (local) root@192.168.0.23 ~/example-voting-app
```

En conclusion, Docker Swarm permite escalar los servicios de una aplicacion rapidamente.

## Stage 2(Digging Deeper)

Se vera como integrar Docker en una infraestructura de aplicacion ya existente.

### Security

#### Seccomp profiles

#### Linux Kernel Capabilities and Docker

### Networking

#### Docker Networking Hands-on Lab

### Orchestration

#### Docker Orchestration Hands-on Lab

And for a bonus, you can work through a comprehensive Orchestration Workshop

#### Part 1

#### Part 2
