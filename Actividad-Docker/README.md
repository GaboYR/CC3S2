# Actividad Docker

Nos piden ejecutar `docker run hello-world`.

Salida en consola :

```sh

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

## Pregunta 1

Explica el procedimiento realizado en esta ejecucion por linea de comandos.

En este caso, lo que sucede es:

- Docker busca la imagen `hello-world` de forma local, si es la primera vez que ejecutas el comando, es normal que no lo encuentre asi que hara un pull desde su biblioteca.
- Despues ya con la imagen en local, con una nueva ejecucion no tendria problemas al momento.
  
### Aplicaciones Docker

Por ejemplo, para la busqueda de `mongo` ponemos el comando `docker search mongo` y nos sale lo siguiente:

```sh
NAME                                                   DESCRIPTION                                     STARS     OFFICIAL
mongo                                                  MongoDB document databases provide high avai…   10267     [OK]
mongo-express                                          Web-based MongoDB admin interface, written w…   1444      [OK]
mongodb/mongodb-community-server                       The Official MongoDB Community Server           90        
mongodb/mongodb-atlas-kubernetes-operator              The MongoDB Atlas Kubernetes Operator - Kube…   5         
mongodb/mongodb-enterprise-server                      The Official MongoDB Enterprise Advanced Ser…   8         
mongodb/atlas                                          Create, manage, and automate MongoDB Atlas r…   5         
mongodb/mongodb-atlas-kubernetes-operator-prerelease   This is an internal-use-only build of the Mo…   0         
mongodb/mongodb-atlas-search                           Atlas Search gives you a seamless, scalable …   1         
mongodb/signatures                                     Signatures for container images                 0         
mongodb/mongodb-atlas-local                            Create, manage, and automate MongoDB Atlas L…   0         
mongodb/apix_test                                      apix test repo                                  0         
bitnami/mongodb                                        Bitnami container image for MongoDB             252       
mongodb/mongo-cxx-driver                               Container image for the C++ driver              2         
bitnami/mongodb-exporter                               Bitnami container image for MongoDB Exporter    12        
circleci/mongo                                         CircleCI images for MongoDB                     13        
bitnami/mongodb-sharded                                Bitnami container image for MongoDB® Sharded    12        
percona/mongodb_exporter                               A Prometheus exporter for MongoDB including …   9         
rapidfort/mongodb                                      RapidFort optimized, hardened image for Mong…   23        
rapidfort/mongodb-official                             RapidFort optimized, hardened image for Mong…   12        
rapidfort/mongodb-ib                                   RapidFort optimized, hardened image for Mong…   10        
bitnamicharts/mongodb                                                                                  1         
rancher/mongodb-conf                                   This container image is no longer maintained…   2         
bitnamicharts/mongodb-sharded                                                                          0         
rapidfort/mongodb-perfomance-test                                                                      10        
chainguard/mongodb                                     The MongoDB Database image                      0        
```

### Creacion de imagenes Docker

Podemos crear imagenes Docker de dos formas, la primera, usando el comando `commit` de Docker y la segunda, mediante un Dockerfile.

#### Commit

Si ejecutamos el comando `docker run -i -t ubuntu20:04 /bin/bash`, veremos lo sgte

![imageUbuntu](/images/imageUbuntu.png)

Esto se asimila a una imagen del ISO de ubuntu.

Ahora instalamos git en la ISO de Ubuntu.

```sh
apt-get update
apt-get install -y git
```

Verificacion de la instalacion de `git`

```sh
which git
```

Salida en consola:
![git](/images/git.png)

Y salimos del contenedor colocando `exit`

Verificamos si han habido cambios con el comando `docker diff <ID_contenedor>`.

En este caso seria:

```sh
docker diff eac158ab64a3
```

Salida en consola :

![diff](/images/diff.png)

Ahora para poder ver la "llave"

comando

```sh
docker container ls -a | grep ubuntu
``

Asi veremos la infirmacion del contenedor que contiene la palabra ubuntu.
