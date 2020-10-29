# 2C-U2-TP02-GRUPO1
Realidad "Aivon"
INSTRUCTIVO: El proyecto será una aplicación en Java / MySQL. Se debe crear las tablas de la base de datos, y las correspondientes clases. Utilice una
clase para la conexión y confeccione la interfaz gráfica (GUI). El grupo deberá presentar el proyecto en 3 etapas.
1- Armado de la base de datos completa, relaciones. Proyecto en Netbeans, y clase de conexión. ABM de una clase*.
2- Desarrollo de todas las clases principales del dominio (ABM), consultas SQL embebidas. Interfaces gráficas completas.
3- Implementación de los todos los métodos necesarios, listados, ABM de clases relacionadas y aplicación terminada.
Se deben presentar las clases con atributos y métodos necesarios(abstracción). Setters y Getters (encapsulamiento). Constructor.
Se evaluará el aporte de todos los miembros al proyecto, en la construcción de las clases. Se subirán los commit al repositorio (Git).
--------------------------------------------------------------------------------------------------------------------------------------
AIVON- La empresa vendedora de productos cosméticos por cartilla “Aivon” nos ha pedido
sistematizar su forma de trabajo mediante un sistema en Java, y trabaja de la siguiente manera:
La empresa cuenta con una amplia variedad de productos, tales como cremas corporales, perfumes, desodorantes, espuma
de afeitar, esmaltes, labiales, cremas de rostro, etc. De los cuales se sabe su código, nombre, uso, tamaño en cm3, y claro
su precio de venta al público, y precio costo. En un pedido, entran uno a varios productos, registrando los datos
mencionados y cada producto aporta 0 o más “estrellas” al comprarse.
Un conjunto de personas que distribuyen y venden al menoreo los productos cosméticos, conocidas como “Revendedor/a”,
se encargan de recibir las “Cajas de Aivon” que contendrán todos los productos cosméticos que las revendedoras soliciten
durante una campaña.
Cada revendedora que se incorpora se da de alta, y puede modificar sus datos o estado. Se conocen datos de contacto
(teléfono y mail), nombre completo, y dni. Cada una puede estar activa o inactiva, si no vende por 3 campañas. La ganancia
del vendedor es comprar a precio costo, y vender a precio público. Un revendedor pasa de campaña si junta 50 estrellas,
aumentando el monto mínimo, y monto tope (límite).
Al inicio de la campaña una revendedora puede hacer o no un pedido (una o varias cajas de productos). De cual se guarda
fecha de ingreso, fecha entrega, fecha de pago, cant. de cajas, importe, estrellas por el pedido hecho. Cada una de esas
fechas, marca si la revendedora hizo el pedido, lo recibió, y tiene 10 días hábiles después de fecha de entrega para ir a un
RapiFacil a pagar el importe de compra. El estado podría ser pago o impago. No diremos que le pasa al revendedor que no
paga el pedido. Cada pedido perteneces a un revendedor.
Comienzan de la campaña 1, en adelante. Las campañas duran 25 días, desde fecha inicio a fecha fin, monto mínimo,
monto tope y se registran la cantidad de estrellas por campaña. Además, un “monto mínimo”, nos marca que el “importe”
del pedido debe ser igual o mayor al mínimo de esa campaña. Generalmente se hace uno o varios pedidos por campaña. Se
suman las “estrellas pedido” por los productos adquiridos, y luego se obtiene un total de “estrellas campaña-revendedor”
que se guarda en la clase histórico-campaña, con estado ‘activa’ para esa revendedora.
● Relaciones Potenciales
o Una revendedora es capaz de efectuar 1 a varios pedidos, sin embargo, cada uno de ellos está ligado
exclusivamente a una revendedora de la firma Aivon.
o Cada pedido llega embalado en una cantidad de cajas que contienen productos cosméticos, y esas unidades
llegaran asociadas a un pedido en especial.
o Todo Pedido corresponde a una Campaña en particular, durante la cual se tendría la posibilidad de encargar
múltiples pedidos de Aivon. Y dada la cantidad de estrellas sumadas en cada pedido, se podrán acumular estrellas
en la campaña.
o En adición hay que conocer en cada Pedido a que campaña pertenece, y al método que nos indique ¿qué pedidos
corresponden (o se hicieron) en cada campaña? y por tanto las estrellas que ha sumado con cada pedido.
o Se sabe que en una campaña se tendrán ninguno a muchos pedidos, donde cada pedido (vinculado a la
revendedora) ocurre durante una campaña en particular.
o En cambio, debemos saber un histórico revendedor-campaña de los puntajes (en estrellas) que se hizo en cada
campaña, y determinar cuál es la campaña en estado ‘activa’ en la que está dicho/a revendedor/a. Ya que
podríamos querer saber ¿qué cantidad de estrellas sumo un revendedor en cada campaña? Con alguna función del
programa.

-------------------------------------------------------------------------------------------------------------------------------
