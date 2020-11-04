Procedimientos estándar para el desarrollo de clases.

Convención de nombres:

#Clases: 
Primer letra mayúscula y las demás en minúsculas y en el caso de ser varias palabras deben ir unidas en CamelCase (uper).

Ejemplo:

public class CampañaData {
    
}

#Métodos: 

En tipo de escritura camelCase (lower).

Ejemplo:

public void setNombre(String nombre) {
        this.nombre = nombre;
    }


#Variables: 

Se utilizan todas las palabras en minúsculas y separadas por guion bajo (snake case).

Ejemplos:

private int id_producto;

Procedimiento para trabajo conjunto en GitHub sobre el presente proyecto.

Convenimos en que vamos a tener una sola rama Master en el repositorio cuyo nombre es precisamente master. A su vez cada integrante del grupo va a tener una rama personal donde realizara los trabajos asignados. Luego desde la rama personal hará el push hacia el mater.

Detalle de acciones.
1.	Trabajar cada uno en su rama. (Genaro, Mario, Ezequiel).
2.	Cuando finalizamos la sesión de trabajo, hacer un comit de la rama personal con el detalle de lo realizado.
3.	Cambiar a rama Master, y hacer un pull para bajar lo ultimo que se encuentre en el repositorio.
4.	Estando en la rama Master, hacer un Merge seleccionando el ultimo commit de la rama personal. En este proceso se genera un commit automático llamado merge/commit. Para que se genere éste commit, hay que seleccionar la opción Always créate commit en las opciones del Merge. 
5.	Estando siempre en rama Master, hacer el push.. para enviar al repositorio. Al hacer push master->master también seleccionar la opción miRamaPersonal->miRamaPersonal para que ambas ramas queden actualizadas en el repositorio.
6.	Dar aviso al resto del grupo. Si la acción del push se realizo en videoconferencia por Zoom como acordamos, omitir el aviso. 


