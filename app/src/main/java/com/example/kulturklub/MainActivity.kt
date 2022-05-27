package com.example.kulturklub

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.widget.TextView
import androidx.activity.viewModels
import com.example.kulturklub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var titulin: String = "KulturKlub"
    val modelo : VM by viewModels()
    var currentUser : String = "adr5jfWfJz"
    var home = R.id.home
    var usermenu = R.id.miUsuario
    var creatormenu = R.id.organizador
    var logoutmenu = R.id.logout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }




    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }



    fun popupDelete(bundle: Bundle){
        val name: String = bundle.getString("name") ?:""
        val id: String = bundle.getString("id") ?: ""
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.deletepopup, null)

        builder.setView(view)
        val dialog = builder.create()
        dialog.show()

        val titulo = view.findViewById<TextView>(R.id.eventTitulo)
        titulo.text = name
        val delbutton = view.findViewById<TextView>(R.id.deletebutton)
        val cancelbutton = view.findViewById<TextView>(R.id.cancelbutton)

        cancelbutton.setOnClickListener{
            dialog.hide()
        }

        delbutton.setOnClickListener {
            modelo.delEvent(id, this)
            dialog.hide()
        }
    }

}