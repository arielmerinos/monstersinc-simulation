# Responsabilidades en Monsters Inc. 🚀

## 🌟 Has sido unido al equipo de desarrollo y pruebas
Tu deber en Monsters Inc. es ayudarnos a reformar la empresa. Estamos en un proceso de reestructuración total, pues queremos ser la mejor empresa en producir energía limpia y en gran cantidad. Tu deber es proveer a nuestros directivos una simulación de cómo funcionaría la empresa en un día normal en cada uno de nuestros departamentos y zonas de la empresa, desde que empieza el día hasta que acabe.

📌 **Importante:** Se deben contar con situaciones especiales, por ejemplo si hay un 3312, así mismo si varios monstruos toman vacaciones o similar.

## 🏭 Departamentos y Zonas

### Cafetería ☕
- Se cuentan con los mejores chefs de todo monstruopolis, incluso se tiene contratado a un exhereden de Harryhauseen’s, se podría decir que la comida es digna de un rey
- En esta zona se cuenta con lo siguiente:
  - Se cuenta con un chef profesional y algunos que lo ayudan en las comidas
  - Se cuenta con un recepcionista el cual asigna los monstruos a sus mesas.
  - Las mesas son de distintos tamaños, por lo que pueden existir mesas para 2, 4 y 6 monstruos.
  - Hay una cantidad máxima de monstruos que pueden ingresar
  - Tiene asignado algún camarero para llevar su comida a la mesa
  - El chef de Haryhausen’s puede preparar cualquier tipo de platillo, los otros no.
  - En la mesa todos tienen cubiertos.
  - Se cuenta con un inventarios, en caso de que para el platillo no esté un producto, no se podrá hacer.

### Vestidor 👕
- En nuestro vestidor, los monstruos se preparan para un día de trabajo, dejan sus cosas personales, se ponen colorante y finalmente su casco.
- Cada monstruo cuenta con su propio casillero
- Hay varios vestidores, pues es para cada monstruo de la empresa
- Sólo ellos tienen la contraseña de su locker
- Se pueden añadir o retirar elementos de su casillero así como modificarlos

### Baños 🚻
- Tenemos varios tipos de baños, pues tenemos monstruos grandes, pequeños, peludos, escamosos, tenemos de muchos tipos.
- Al Haber varios tipos de baño, se debe modelar para monstruos específicos.
- Existe la posibilidad de que un monstruo cumpla para dos baños, en este caso, puede usar cualquiera de los 2.
- Sólo una cantidad específica de monstruos puede entrar 
- Existen los baños compartidos, pero esto sólo puede ingresar el tipo de monstruo correspondiente, es decir, si tenemos los monstruos a, b, c y entra el monstruo a, entonces el baño sólo lo pueden usar monstruos a.

### Centro de sustos 👻
- Nuestro centro mas popular y el que vamos dejando en desuso, lo conservamos debido a que siempre necesitamos un plus de energía, aparte alguno de nuestros monstruos veteranos ya no logran hacer reír.
- Cada par de monstruos tienen una estación, en esta llegas las puertas para proceder a asustar y generar energía que se almacena en el contenedor.
- Se cuenta con una unidad máxima de contenedores disponibles
- Si hay cero contenedores, se debe surtir
- Los que surten contenedores, pueden pasar esporádicamente, no necesariamente cuando estén en cero.
- A veces se habilita la puesta especial, donde Sully baja y asusta a algunos adultos, estos gritos se guardan en 1 maxitanque, dependiendo del número de adultos, es el número de maxitanques a usar.
- Se pueden usar muchos contenedores o maxitanques si hay pijamadas
- Hay un lugar donde se ponen los tanques llenos y pasan a recogerlos

### Centro de risas 😂
- Nuestro centro más nuevo, como sabrán, el poder de la risa genera más energía que la de los sustos, por lo que principalmente se guardan en maxitanques y en los experimentales ultratanques
- Es similar al centro de sustos, solo que la capacidad de contenedores es más alta
- Hay un valor máximo de maxitanques
- Hay un valor máximo de ultratanques
- Si hay contenedores, de deben surtir
- A veces se habilita la estación especial para Mike Wazawsky para generar una super risa, esta se puede guardar en varios ultratanques o incluso en los ultra hiper secretos Gigatanques
- Hay un lugar donde se ponen los tanques llenos y pasan a recogerlos

### Centro de reparación 🔧
- Un centro donde se arreglan los tanques, ellas, puestas, tarjetas, etc.
- Hay varios individuos que reparan las cosas.
- Si se rompe algo, ellos reparan
- Pueden generar alguna probabilidad de que se rompa algún elemento o se descomponga y ellos lo reparen.
- Puede ser también por número de usos
- Pueden ir a repararlo In situ o se lo pueden llevar, tú decides.
- Se lleva una cantidad de tiempo en reparar cosas
- Tienen una lista de cómo van reparando cosas.

### Fábrica de puertas 🚪
- Aquí tenemos a los mejores diseñadores de puertas, normalmente se requiere a un monstruo por puerta, pero si es una puerta especial se pueden usar más.
- Aquí se fabrican las puertas
- Cada puerta da a la habitación de un niño o un adulto
- Es mayor la frecuencia de niños
- Hay probabilidades de que sea una fiesta o pijamada
- Una vez fabricadas, se mandan al almacén

### Fábrica de tanques 🛢️
- Aquí fabricamos los tanques donde se almacenan la energía, tenemos muchos tipos de tanques
  - Tenemos el tanque estándar, tiene capacidad para N unidades de energía
  - Tenemos el maxitanque, tiene capacidad de M tanques estándar
  - Tenemos el experimental ultratanque, tiene capacidad de L maxitanques
  - Tenemos el ultra híper secreto Gigatanque, tiene capacidad de 2H ultratanques
  - Para fabricarlos, se requiere un monstruo para cada uno.
  - Varía en el tiempo de fabricación
  - Al final los colectan y los llevan a un almacén

### Almacén de puertas 📦
- Donde guardamos todas nuestras puertas construidas
- Se almacenan las puertas y luego se pueden llamar a pedir
- Las puertas tienen estados (en uso, en reparación, disponible)
- Puedes suponer que el tamaño del almacén es de 4 a 10 veces el número de monstruos
- Pueden estar catalogadas en niños y adultos (puedes agregar más categorías si lo deseas)

### Almacén de tanques 🏭
- Nuestro almacén de tanques, aquí es donde se guardan
- Divididos en categorías
- Cuentan con algunos estados (en uso, en reparación, disponible, etc.)
- Pueden ser recolectados para su distribución

### Recolector industrial 🏗️
- Finalmente aquí se vacían los tanques que tenemos
- Puedes suponer que tiene capacidad J
- Se pueden vaciar paralelamente y finalmente mostrar el cuentas unidades de energía tenemos
- Una vez hecho esto, se regresan las latas al almacén
- Cada cierto tiempo se vacía la energía que alimenta monstruopolis
- Hay un contador de cuánta energía se tiene desde que inició el proceso.

---

+ No se prude usar syncrhonized
+ Se deben especificar qué patrones de diseño fueron utilizados
+ Debe hacerse usando Java y los resultados deben imprimirse en la terminal
