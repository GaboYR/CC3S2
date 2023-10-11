# Pregunta 1:
# 1.1
Haremos lo siguiente:
- Creamos un array que guarde la frecuencia de cada letra
- De la 'a' a la 'z' hay un conteo de 0 a 25, al recorrer la cadena, en O(n), si encontramos la letra 'c', enconces el contador de 'c' aumenta.
- Pero para guardar la frecuencia lo haremos de la forma cont[letra.ord - 'a'.ord], así tenemos un espacio para guardar los valores de la 'a' a 'z'
- Una vez recorrida la cadena, verificamos cada valor del contador, si tenemos cont[2] = 0 -> eso significa que el caracter 'c' no se encuentra en la cadena.
- De esa forma, por cada contador de valor 0, significa que no aparece en la cadena.
![Salida de terminal](https://github.com/GaboYR/CC3S2/blob/main/PC_2/images/pc02_p1.png)
- Debe mostrar :"Falta f"
- En mi caso, creo un espacio de 65, por si acaso, para evitar errores de memoria.
# Pregunta 3
Ejecutamos el comando "**rails new rottenpotatoes --skip-test-unit --skip-turbolinks --skip-spring**"
![Salida del comando rails new rottenpotatoes --skip-test-unit --skip-turbolinks --skip-spring](https://github.com/GaboYR/CC3S2/blob/main/PC_2/images/bundle.png)
Eso genera un directorio rottenpotatoes:

![a](https://github.com/GaboYR/CC3S2/blob/main/PC_2/images/rotten.png)

Luego, generamos la migracion con el comando **rails generate migration CreateMovies**

![](https://github.com/GaboYR/CC3S2/blob/main/PC_2/images/db%3Amigrate.png)

Seguido, ponemos el comando **rails db:migrate**, y posteriormente **rails db:seed** para migrar y sembrar los datos a la aplicación.

![](https://github.com/GaboYR/CC3S2/blob/main/PC_2/images/migrate.png)

Finalmente, preparamos el heroku para subirlo a remoto.

![](https://github.com/GaboYR/CC3S2/blob/main/PC_2/images/heroku.png)
