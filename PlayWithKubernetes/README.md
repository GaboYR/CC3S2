# Play with Kubernetes

## Introduction

Kubernetes es una plataforma para orquestar contenedores.
Orquestar es gestionar diversos contenedores en un entorno distribuido.

## Getting started

En nuestro navegador veremos 2 terminales, node1 y node2,similar a la actividad de stage1 en la parte de Docker Swarm.

Si ejecutamos `ls` en node1 veremos

```sh
[node1 ~]$ ls
anaconda-ks.cfg
```

### Start the cluster

Ejecutamos el comando `kubeadm init --apiserver-advertise-address $ hostname -i)`

En el classroom nos dan la sgte instruccion

```sh
kubeadm init --apiserver-advertise-address $(hostname -i) --pod-network-cidr 10.5.0.0/16
```

Ejecutamos la instruccion anterior en la terminal y veremos informacion similar a esta

```sh
Initializing machine ID from random generator.
.
.
.
KERNEL_VERSION: 4.4.0-210-generic
OS: Linux
CGROUPS_CPU: enabled
CGROUPS_CPUACCT: enabled
CGROUPS_CPUSET: enabled
CGROUPS_DEVICES: enabled
CGROUPS_FREEZER: enabled
CGROUPS_MEMORY: enabled
CGROUPS_PIDS: enabled
CGROUPS_HUGETLB: enabled
CGROUPS_BLKIO: enabled
        .
        .
        .
Your Kubernetes control-plane has initialized successfully!

To start using your cluster, you need to run the following as a regular user:

  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

Alternatively, if you are the root user, you can run:

  export KUBECONFIG=/etc/kubernetes/admin.conf

You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  https://kubernetes.io/docs/concepts/cluster-administration/addons/

Then you can join any number of worker nodes by running the following on each as root:

kubeadm join 192.168.0.18:6443 --token biuysw.cnql2ktjc81j2dsz \
        --discovery-token-ca-cert-hash sha256:cb6ce333d0af9d8d758abb87f47c6b1684f11b96441e2b62549b227c1a77fe4a 
    .
    .
    .
```

Entonces ya tenemos nuestro plano de control de Kubernetes incializado.

Ahora en la 2da terminal, para unir un nodo a nuestro cluster

```sh
kubeadm join 192.168.0.18:6443 --token biuysw.cnql2ktjc81j2dsz \
        --discovery-token-ca-cert-hash sha256:cb6ce333d0af9d8d758abb87f47c6b1684f11b96441e2b62549b227c1a77fe4a
```

Salida en consola:

```sh
kubeadm join 192.168.0.18:6443 --token biuysw.cnql2ktjc81j2dsz \
84f11b96441e2b62549b227c1a77fe4aert-hash sha256:cb6ce333d0af9d8d758abb87f47c6b16 
Initializing machine ID from random generator.
W0630 02:48:14.599262    1999 initconfiguration.go:120] Usage of CRI endpoints without URL scheme is deprecated and can cause kubelet errors in the future. Automatically prepending scheme "unix" to the "criSocket" with value "/run/docker/containerd/containerd.sock". Please update your configuration!
[preflight] Running pre-flight checks
        [WARNING Swap]: swap is enabled; production deployments should disable swap unless testing the NodeSwap feature gate of the kubelet
[preflight] The system verification failed. Printing the output from the verification:
KERNEL_VERSION: 4.4.0-210-generic
OS: Linux
CGROUPS_CPU: enabled
CGROUPS_CPUACCT: enabled
CGROUPS_CPUSET: enabled
CGROUPS_DEVICES: enabled
CGROUPS_FREEZER: enabled
CGROUPS_MEMORY: enabled
CGROUPS_PIDS: enabled
CGROUPS_HUGETLB: enabled
CGROUPS_BLKIO: enabled
        [WARNING SystemVerification]: failed to parse kernel config: unable to load kernel module: "configs", output: "", err: exit status 1
        [WARNING FileContent--proc-sys-net-bridge-bridge-nf-call-iptables]: /proc/sys/net/bridge/bridge-nf-call-iptables does not exist
[preflight] Reading configuration from the cluster...
[preflight] FYI: You can look at this config file with 'kubectl -n kube-system get cm kubeadm-config -o yaml'
[kubelet-start] Writing kubelet configuration to file "/var/lib/kubelet/config.yaml"
[kubelet-start] Writing kubelet environment file with flags to file "/var/lib/kubelet/kubeadm-flags.env"
[kubelet-start] Starting the kubelet
[kubelet-start] Waiting for the kubelet to perform the TLS Bootstrap...

This node has joined the cluster:
* Certificate signing request was sent to apiserver and a response was received.
* The Kubelet was informed of the new secure connection details.

Run 'kubectl get nodes' on the control-plane to see this node join the cluster.

[node2 ~]$ 
```

Para verificar que el nodo se haya unido a nuestro sistema, verificamos con `kubectl get nodes`

```sh
kubectl get nodes
E0630 02:50:06.319863    3451 memcache.go:265] couldn't get current server API group list: Get "http://localhost:8080/api?timeout=32s": dial tcp 127.0.0.1:8080: connect: connection refused
E0630 02:50:06.320456    3451 memcache.go:265] couldn't get current server API group list: Get "http://localhost:8080/api?timeout=32s": dial tcp 127.0.0.1:8080: connect: connection refused
E0630 02:50:06.322935    3451 memcache.go:265] couldn't get current server API group list: Get "http://localhost:8080/api?timeout=32s": dial tcp 127.0.0.1:8080: connect: connection refused
E0630 02:50:06.324948    3451 memcache.go:265] couldn't get current server API group list: Get "http://localhost:8080/api?timeout=32s": dial tcp 127.0.0.1:8080: connect: connection refused
E0630 02:50:06.326969    3451 memcache.go:265] couldn't get current server API group list: Get "http://localhost:8080/api?timeout=32s": dial tcp 127.0.0.1:8080: connect: connection refused
The connection to the server localhost:8080 was refused - did you specify the right host or port?
[node2 ~]$
```

Para poder desplegar Kube-Router, usamos el comando mostrado en el classroom.

```sh
kubectl apply -f https://raw.githubusercontent.com/cloudnativelabs/kube-router/master/daemonset/kubeadm-kuberouter.yaml
```

Salida en consola:

```sh
configmap/kube-router-cfg unchanged
daemonset.apps/kube-router configured
serviceaccount/kube-router unchanged
clusterrole.rbac.authorization.k8s.io/kube-router unchanged
clusterrolebinding.rbac.authorization.k8s.io/kube-router unchanged
[node1 ~]$
```

En mi caso aparece `unchanged` porque probe el comando 2 veces, con la 1ra ejecucion sale `created`.

Para continuar con el trabajo, necesitamos clonar un repositorio, el de dockercoins

```sh
git clone https://github.com/dockersamples/dockercoins
```

Luego nos dirigimos al directorio de dockercoins con

```sh
cd ~/dockercoins
```

Ahora con

```sh
docker compose up
```

construimos y corremos nuestros contenedores.

Salida en consola:

![err](/images/error.png)

Ahi se tira un error que no termina, mientras espero podemos revisar el contenido del directorio `/dockerCoins`

```sh
[node1 dockercoins]$ ls
LICENSE                    docker-compose.logging.yml  ports.yml  webui
README.md                  docker-compose.yml          rng        worker
docker-compose.images.yml  hasher                      stacks
[node1 dockercoins]$
```

Por ejm, veamos la informacion de todos los `docker-compose.*`

```yml
[node1 dockercoins]$ cat docker-compose.*
version: "2"

services:
  rng:
    build: rng
    image: ${REGISTRY_SLASH}rng${COLON_TAG}
    ports:
    - "8001:80"

  hasher:
    build: hasher
    image: ${REGISTRY_SLASH}hasher${COLON_TAG}
    ports:
    - "8002:80"

  webui:
    build: webui
    image: ${REGISTRY_SLASH}webui${COLON_TAG}
    ports:
    - "8000:80"
    volumes:
    - "./webui/files/:/files/"

  redis:
    image: redis

  worker:
    build: worker
    image: ${REGISTRY_SLASH}worker${COLON_TAG}

version: "2"

services:
  
  rng:
    build: rng
    ports:
    - "8001:80"
    logging:
      driver: gelf
      options:
        gelf-address: udp://localhost:12201

  hasher:
    build: hasher
    ports:
    - "8002:80"
    logging:
      driver: gelf
      options:
        gelf-address: udp://localhost:12201

  webui:
    build: webui
    ports:
    - "8000:80"
    volumes:
    - "./webui/files/:/files/"
    logging:
      driver: gelf
      options:
        gelf-address: udp://localhost:12201

  redis:
    image: redis
    logging:
      driver: gelf
      options:
        gelf-address: udp://localhost:12201

  worker:
    build: worker
    logging:
      driver: gelf
      options:
        gelf-address: udp://localhost:12201

version: "2"

services:
  rng:
    build: rng
    ports:
    - "8001:80"

  hasher:
    build: hasher
    ports:
    - "8002:80"

  webui:
    build: webui
    ports:
    - "8000:80"
    volumes:
    - "./webui/files/:/files/"

  redis:
    image: redis

  worker:
    build: worker

[node1 dockercoins]$
```

Dentro de la carpeta `hasher` hay un Dockerfile y un archivo `hasher.rb`

```sh
[node1 dockercoins]$ ls
LICENSE                    docker-compose.logging.yml  ports.yml  webui
README.md                  docker-compose.yml          rng        worker
docker-compose.images.yml  hasher                      stacks
[node1 dockercoins]$ cd w 
webui/  worker/ 
[node1 dockercoins]$ cd webui/
[node1 webui]$ ls
Dockerfile  files  webui.js
[node1 webui]$ cd ..
[node1 dockercoins]$ cd rng/
[node1 rng]$ sl
bash: sl: command not found
[node1 rng]$ ls
Dockerfile  rng.py
[node1 rng]$ cd ..
[node1 dockercoins]$ cd stacks/
[node1 stacks]$ ls
dockercoins                   dockercoins-big.yml    prometheus.yml
dockercoins+gelf.yml          dockercoins.yml        registry.yml
dockercoins+healthcheck.yml   elk.yml
dockercoins+healthchecks.yml  prometheus+config.yml
[node1 stacks]$ 
```

Veamos un Dockerfile, el que esta ubicado en `hassher`

```sh
[node1 dockercoins]$ ls
LICENSE                    docker-compose.logging.yml  ports.yml  webui
README.md                  docker-compose.yml          rng        worker
docker-compose.images.yml  hasher                      stacks
[node1 dockercoins]$ cd hasher/
[node1 hasher]$ ls
Dockerfile  hasher.rb
[node1 hasher]$ cat Dockerfile 
FROM ruby:3-alpine
RUN apk add --update build-base curl
RUN gem install sinatra
RUN gem install thin
ADD hasher.rb /
CMD ["ruby", "hasher.rb"]
EXPOSE 80
HEALTHCHECK \
  --interval=1s --timeout=2s --retries=3 --start-period=1s \
  CMD curl http://localhost/ || exit 1
[node1 hasher]$ 
```

Ahora el de `worker`

```sh
cd worker/
[node1 worker]$ ls
Dockerfile  worker.py
[node1 worker]$ cat Dockerfile 
FROM python:3-alpine
RUN pip install redis
RUN pip install requests
COPY worker.py /
CMD ["python", "worker.py"]
[node1 worker]$
```

Asi podemos listar todos los directorios, y el error todavia persiste. Cancelamos el proceso con `Ctrl + C`.

```sh
^CGracefully stopping... (press Ctrl+C again to force)
Stopping dockercoins_rng_1    ... done
Stopping dockercoins_worker_1 ... done
Stopping dockercoins_webui_1  ... done
Stopping dockercoins_redis_1  ... done
[node1 dockercoins]$ 
```
