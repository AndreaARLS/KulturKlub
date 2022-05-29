package com.example.kulturklub
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.parse.ParseObject
import com.parse.ParseQuery

class VM: ViewModel() {


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

    fun delEvent(id: String,actividad: MainActivity) {

        val query = ParseQuery.getQuery<ParseObject>("Evento")
        query.whereEqualTo("objectId", id)
        query.getFirstInBackground { parseObject, parseException ->
            if (parseException == null) {
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
    fun consultarUsuarios(): MutableList<Usuario> {
        var usuarios: MutableList<Usuario> = mutableListOf()
        val query = ParseQuery.getQuery<ParseObject>("Usuario")
        query.orderByAscending("username")
        val lista = query.find()
        for (i in lista) {
            val usu: Usuario = Usuario(
                i.objectId,
                i.getString("username").toString(),
                i.getString("email").toString(),
                i.getString("password").toString(),
                i.getString("avatar").toString()
            )
            usuarios.add(usu)
        }
        return usuarios
    }



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

    fun editUser(id: String, username: String, email: String, password: String, avatar: String, actividad: MainActivity){
        val query = ParseQuery.getQuery<ParseObject>("Usuario")
        query.whereEqualTo("objectId", id)
        query.getFirstInBackground{ parseObject, parseException ->
            if (parseException == null){
                parseObject.put("username", username)
                parseObject.put("email", email)
                parseObject.put("password", password)
                parseObject.put("avatar", avatar)
                parseObject.saveInBackground{
                    if (it != null){
                        it.localizedMessage?.let{ message ->
                            Toast.makeText(actividad, "Algo no ha ido bien. No se ha podido actualizar el usuario", Toast.LENGTH_LONG).show()
                        }
                    }else {
                        Toast.makeText(actividad, "Usuario actualizado.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


}
