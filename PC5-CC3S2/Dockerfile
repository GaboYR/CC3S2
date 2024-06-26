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