import re

# Definir el patrón de la expresión regular
pattern = r'\d+'  # Buscar uno o más dígitos

# Texto de ejemplo
text = 'Hola, mi número de teléfono es 123456789'

# Buscar coincidencias en el texto usando el patrón
matches = re.findall(pattern, text)

# Imprimir las coincidencias encontradas
for match in matches:
    print(match)
