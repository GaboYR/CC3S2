# Verificación del funcionamiento de Docker, Docker, Desktop, Minikube, Kind

## Prueba de Docker Engine

1. Debemos instalar docker en nuestro dispositivo.

    En mi caso trabajare con la distribucion de Debian:

    > OS: Debian GNU/Linux 12 (bookworm) x86_64

2. Al correr el comando `docker version` veremos en consola lo sgte :

    ```txt
    Client: Docker Engine - Community
    Version:           26.1.4
    API version:       1.45
    Go version:        go1.21.11
    Git commit:        5650f9b
    Built:             Wed Jun  5 11:29:22 2024
    OS/Arch:           linux/amd64
    Context:           default

    Server: Docker Engine - Community
    Engine:
    Version:          26.1.4
    API version:      1.45 (minimum version 1.24)
    Go version:       go1.21.11
    Git commit:       de5c9cf
    Built:            Wed Jun  5 11:29:22 2024
    OS/Arch:          linux/amd64
    Experimental:     false
    containerd:
    Version:          1.6.33
    GitCommit:        d2d58213f83a351ca8f528a95fbd145f5654e957
    runc:
    Version:          1.1.12
    GitCommit:        v1.1.12-0-g51d5e94
    docker-init:
    Version:          0.19.0
    GitCommit:        de40ad0
    ```

    Si ponemos `docker --version`` veremos :

    ```txt
    Docker version 26.1.4, build 5650f9b
    ```

3. Verificamos si podemos correr contenedores:
   Corremos el comando `docker container run hello-world`
![hello](/images/HelloDocker.png)

4. Ahora para verificar la instalacion de docker corremos el comando `docker container run rancher/cowsay Hello`.
Salida en consola:
![cow](/images/hellocow.png)

## Probando minikube y kubectl

Por temas de almacenamiento omitire la instalacion de `Docker Desktop`, sin embargo, usare la consola para su administracion.

Procedimiento para instalar `cubectl`

```sh
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x kubectl
sudo mv kubectl /usr/local/bin/
```

Procedimiento para instalar `minikube`

```sh
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```

Captura de procedimiento :

![kubectl](/images/kube.png)

Para verificar que `kubectl` este configurado para su uso con `minikube`, corremos `kubectl get nodes`

![nodes](/images/nodes.png)
