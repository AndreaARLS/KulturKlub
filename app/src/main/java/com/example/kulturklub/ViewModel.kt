package com.example.kulturklub
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.parse.ParseObject
import com.parse.ParseQuery

class VM: ViewModel() {

    var usuarios : MutableList<Usuario> = mutableListOf()

    /*var event1 = Evento("The Hives", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://m.media-amazon.com/images/I/71RTSRlqvnL._SY355_.jpg",2)
    var event2 = Evento("Clutch", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.rutarock.com/wp-content/uploads/2020/01/unnamed.jpg",2)
    var event3 = Evento("Hitchcock", "CICLO DE CINE", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event4 = Evento("Senectud", "EXPOSICIÓN", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "28/10/2022", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event5 = Evento("Patty Smith", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event6 = Evento("Indian Vibe", "EXPOSICIÓN", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "28/12/2022", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event7 = Evento("Paperplane", "EXPOSICIÓN", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "28/12/2022", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event8 = Evento("El lenguaje del cine soviético", "CICLO DE CINE", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event9 = Evento("La M.O.D.A.", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event10 = Evento("Quartet Tarantino", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)

    var user1 = Usuario("Admin", "admin@example.org", "1234", "https://i0.wp.com/codigoespagueti.com/wp-content/uploads/2020/11/Sin-titulo362.jpg?fit=1280%2C720&quality=80&ssl=1")
    var user2 = Usuario("Tester", "tester@example.org", "1234", "https://i.pinimg.com/474x/15/e1/90/15e190a1ca7d6e5e3c6583821ca7455e--adventure-time-memes.jpg")

    init{
        eventos.add(event1)
        eventos.add(event2)
        eventos.add(event3)
        eventos.add(event4)
        eventos.add(event5)
        eventos.add(event6)
        eventos.add(event7)
        eventos.add(event8)
        eventos.add(event9)
        eventos.add(event10)

        usuarios.add(user1)
        usuarios.add(user2)
    }*/


    ///  FUNCIONES EVENTO
    fun consultarEventos(): MutableList<Evento> {
        var eventos: MutableList<Evento> = mutableListOf()
        val query = ParseQuery.getQuery<ParseObject>("Evento")
        query.orderByAscending("fechainicio")
        val lista = query.find()
        for (i in lista) {
            val event: Evento = Evento(
                i.objectId,
                i.getString("titulo").toString(),
                i.getString("tipo").toString(),
                i.getString("descripcion").toString(),
                i.getString("lugar").toString(),
                i.getString("ciudad").toString(),
                i.getString("fechaInicio").toString(),
                i.getString("fechaFin").toString(),
                i.getString("foto").toString(),
                i.getString("creador").toString()
            )
            eventos.add(event)
        }
        return eventos
    }



    fun nuevoEvent(titulo: String, tipo: String, descripcion: String, lugar: String, ciudad: String, fechainicio: String, fechafin: String, url: String, creador : String, actividad : MainActivity){
        val firstObject = ParseObject("Evento")
        firstObject.put("titulo", titulo)
        firstObject.put("tipo", tipo)
        firstObject.put("descripcion", descripcion)
        firstObject.put("lugar", lugar)
        firstObject.put("ciudad", ciudad)
        firstObject.put("fechaInicio", fechainicio)
        firstObject.put("fechaFin", fechafin)
        firstObject.put("foto", url)
        firstObject.put("creador", creador)
        firstObject.saveInBackground{
            if (it != null){
                it.localizedMessage?.let { message ->
                    Toast.makeText(actividad, "Algo ha salido mal. No hemos podido guardar el evento.", Toast.LENGTH_LONG).show()}
                } else {
                    Toast.makeText(actividad, "Evento guardado", Toast.LENGTH_LONG ).show()
                }
            }
        }



    fun editEvent(id: String, titulo: String, tipo: String, descripcion: String, lugar: String, ciudad: String, fechainicio: String, fechafin: String, url: String, actividad: MainActivity){
        val query = ParseQuery.getQuery<ParseObject>("Evento")
        query.whereEqualTo("objectId", id)
        query.getFirstInBackground{ parseObject, parseException ->
            if (parseException == null){
                parseObject.put("titulo", titulo)
                parseObject.put("tipo", tipo)
                parseObject.put("descripcion", descripcion)
                parseObject.put("lugar", lugar)
                parseObject.put("ciudad", ciudad)
                parseObject.put("fechaInicio", fechainicio)
                parseObject.put("fechaFin", fechafin)
                parseObject.put("foto", url)
                parseObject.saveInBackground{
                    if (it != null){
                        it.localizedMessage?.let{ message ->
                            Toast.makeText(actividad, "Algo no ha ido bien. No se ha podido guardar el cambio", Toast.LENGTH_LONG).show()
                        }
                    }else {
                        Toast.makeText(actividad, "Cambio guardardo.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }


    fun deleteEvent(id: String, actividad: MainActivity){
        val query = ParseQuery.getQuery<ParseObject>("Evento")
        query.whereEqualTo("objectId", id)
        query.getFirstInBackground{ parseObject, parseException ->
            if (parseException != null){
                parseObject.deleteInBackground {
                    if (it != null){
                        it.localizedMessage?.let { message ->
                            Toast.makeText(actividad, "Algo no ha ido bien. No se ha podido eliminar el evento", Toast.LENGTH_LONG).show()}
                    } else {
                        Toast.makeText(actividad, "Evento eliminado.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }




    /// FUCNIONES USUARIOS

    fun nuevoUser(name: String, email: String, password: String, avatar: String, actividad : MainActivity){
        val firstObject = ParseObject("Usuario")
        firstObject.put("username", name)
        firstObject.put("email", email)
        firstObject.put("password", password)
        firstObject.put("avatar", avatar)
        firstObject.saveInBackground{
            if (it != null){
                it.localizedMessage?.let { message ->
                    Toast.makeText(actividad, "Algo ha salido mal. No hemos podido registrarte.", Toast.LENGTH_LONG).show()}
            } else {
                Toast.makeText(actividad, "Usuario creado", Toast.LENGTH_LONG ).show()
            }
        }
    }

    fun editUser(posicion: Int, username: String, email: String, password: String, avatar: String){
        usuarios[posicion].username = username
        usuarios[posicion].email = email
        usuarios[posicion].password = password
        usuarios[posicion].avatar = avatar
    }


}
