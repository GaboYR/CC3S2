# Pregunta 1:
Haremos lo siguiente:
- Creamos un array que guarde la frecuencia de cada letra
- De la 'a' a la 'z' hay un conteo de 0 a 25, al recorrer la cadena, en O(n), si encontramos la letra 'c', enconces el contador de 'c' aumenta.
- Pero para guardar la frecuencia lo haremos de la forma cont[letra.ord - 'a'.ord], así tenemos un espacio para guardar los valores de la 'a' a 'z'
- Una vez recorrida la cadena, verificamos cada valor del contador, si tenemos cont[2] = 0 -> eso significa que el caracter 'c' no se encuentra en la cadena.
- De esa forma, por cada contador de valor 0, significa que no aparece en la cadena.
![Salida de terminal](https://github.com/GaboYR/CC3S2/blob/main/PC02/pc02_p1.png)
- Debe mostrar :"Falta f"
  
