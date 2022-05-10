package com.example.kulturklub
import androidx.lifecycle.ViewModel

class VM: ViewModel() {
    var eventos : MutableList<Evento> = mutableListOf()
    var usuarios : MutableList<Usuario> = mutableListOf()

    var event1 = Evento("The Hives", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://m.media-amazon.com/images/I/71RTSRlqvnL._SY355_.jpg",1)
    var event2 = Evento("Clutch", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.rutarock.com/wp-content/uploads/2020/01/unnamed.jpg",1)
    var event3 = Evento("Hitchcock", "CICLO DE CINE", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event4 = Evento("Senectud", "EXPOSICIÓN", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "28/10/2022", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event5 = Evento("Patty Smith", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event6 = Evento("Indian Vibe", "EXPOSICIÓN", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "28/12/2022", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event7 = Evento("Paperplane", "EXPOSICIÓN", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "28/12/2022", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event8 = Evento("El lenguaje del cine soviético", "CICLO DE CINE", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event9 = Evento("La M.O.D.A.", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)
    var event10 = Evento("Quartet Tarantino", "CONCIERTO", "Garage rock, punk rock, postpunk music. Tercera gira de The Hives por España y segundo concierto en Vitoria-Gasteiz. Entradas a la venta en ticketmaster.com", "Sala Kubik", "Vitoria-Gasteiz", "28/08/2022", "", "https://www.alohacriticon.com/wp-content/uploads/2004/07/the-hives-discografia-t-suecia.jpg",1)

    var user1 = Usuario(1,"Admin", "admin@example.org", "1234", "https://i0.wp.com/codigoespagueti.com/wp-content/uploads/2020/11/Sin-titulo362.jpg?fit=1280%2C720&quality=80&ssl=1")
    var user2 = Usuario(2,"Tester", "tester@example.org", "1234", "https://i.pinimg.com/474x/15/e1/90/15e190a1ca7d6e5e3c6583821ca7455e--adventure-time-memes.jpg")


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
    }


    fun nuevoEvent(titulo: String, tipo: String, descripcion: String, lugar: String, ciudad: String, fechainicio: String, fechafin: String, url: String, creador : Int){
        val newevento = Evento(titulo, tipo, descripcion, lugar, ciudad, fechainicio, fechafin, url, creador)
        eventos.add(newevento)
    }

    fun editEvent(posicion: Int, titulo: String, tipo: String, descripcion: String, lugar: String, ciudad: String, fechainicio: String, fechafin: String, url: String){
        eventos[posicion].titulo = titulo
        eventos[posicion].tipo = tipo
        eventos[posicion].descripcion = descripcion
        eventos[posicion].lugar = lugar
        eventos[posicion].ciudad = ciudad
        eventos[posicion].fechaInicio = fechainicio
        eventos[posicion].fechaFin = fechafin
        eventos[posicion].foto = url

    }

    fun deletePeli(posicion: Int){
        eventos.removeAt(posicion)
    }


}
