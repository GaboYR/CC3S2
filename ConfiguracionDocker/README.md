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
![hello](/ConfiguracionDocker/images/HelloDocker.png)

4. Ahora para verificar la instalacion de docker corremos el comando `docker container run rancher/cowsay Hello`.
Salida en consola:
![cow](/ConfiguracionDocker/images/hellocow.png)

## Probando minikube y kubectl

Por temas de almacenamiento omitire la instalacion de `Docker Desktop`, sin embargo, usare la consola para su administracion.

Procedimiento para instalar `cubectl`

```sh
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x kubectl
sudo mv kubectl /usr/local/bin/
```

### Minikube

Minikube es un software que crea un clúster de Kubernetes en una máquina virtual o en un contenedor Docker con un propósito educativo y permitirnos hacer pruebas del orquestador de contenedores Kubernetes.

Procedimiento para instalar `minikube`

```sh
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```

Captura de procedimiento :

![kubectl](/ConfiguracionDocker/images/kube.png)

1. Intentamos acceder al cluster usando `kubectl`, corramos el comando `kubectl config get-contexts`.

    ![context](/ConfiguracionDocker/images/context.png)

2. Para verificar que `kubectl` este configurado para su uso con `minikube`, corremos `kubectl get nodes` para ver el numero de nodos posee el cluster.

    ![nodes](/ConfiguracionDocker/images/nodes.png)
*Notar que la version del kubernetes es 1.30.0*

3. Ejecucion de **"algo"** en el cluster mediante `nginx.yaml`.

Formato del `.yaml`.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: nginx
  labels:
    app.kubernetes.io/name: proxy
spec:
  containers:
    - name: nginx
      image: nginx:stable
      ports:
      - containerPort: 80
        name: http-web-svc
```

```sh
gabriel@192:ConfiguracionDocker$ kubectl apply -f nginx.yaml & kubectl --help | grep apply
[1] 53379
  apply           Apply a configuration to a resource by file name or stdin
pod/nginx unchanged
[1]+  Done                    kubectl apply -f nginx.yaml
```

En este caso ya no sale `pod/nginx created` porque corri el comando 2 veces.
4. Verificacion de la ejecucion del pod:

```sh
gabriel@192:ConfiguracionDocker$ kubectl get pods
NAME    READY   STATUS    RESTARTS   AGE
nginx   1/1     Running   0          8m13s
```

5. Para acceder al servidor Nginx corremos el comando `kubectl expose pod nginx --type=NodePort --port=80`

```sh
gabriel@192:ConfiguracionDocker$  kubectl expose pod nginx --type=NodePort --port=80
service/nginx exposed
```  

6. Para enumerar los servicios definidos en el cluster corremos el comando `kubectl get services`

```sh
gabriel@192:ConfiguracionDocker$ kubectl get services 
NAME         TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)        AGE
kubernetes   ClusterIP   10.96.0.1      <none>        443/TCP        9h
nginx        NodePort    10.97.171.58   <none>        80:31387/TCP   3m56s
```

7. Uso de minikube para crear un tunel hacia el cluster.
Corremos el comando `minikube service nginx`

```sh
gabriel@192:ConfiguracionDocker$ minikube service nginx
|-----------|-------|-------------|---------------------------|
| NAMESPACE | NAME  | TARGET PORT |            URL            |
|-----------|-------|-------------|---------------------------|
| default   | nginx |          80 | http://192.168.49.2:31387 |
|-----------|-------|-------------|---------------------------|
🎉  Opening service default/nginx in default browser...
Opening in existing browser session.
```

Nos redirige al navegador y nos muestra lo sgte:

![services](/ConfiguracionDocker/images/services.png).

Hora de limpiar:

Comandos `kubectl delete service nginx`, `kubectl delete pod nginx`

```sh
gabriel@192:ConfiguracionDocker$ kubectl delete service nginx
service "nginx" deleted
gabriel@192:ConfiguracionDocker$ kubectl delete pod nginx
pod "nginx" deleted
```

Finalmente el comando `minikube stop`

```sh
gabriel@192:ConfiguracionDocker$ minikube stop
✋  Stopping node "minikube"  ...
🛑  Powering off "minikube" via SSH ...
🛑  1 node stopped.
```

## Ejercicios
