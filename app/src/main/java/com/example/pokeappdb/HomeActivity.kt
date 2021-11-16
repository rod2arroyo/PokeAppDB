package com.example.pokeappdb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeappdb.adapter.PokemonListAdapter
import com.example.pokeappdb.model.FavoritosManager
import com.example.pokeappdb.model.LoginManager
import com.example.pokeappdb.model.Pokemon
import com.example.pokeappdb.model.PokemonManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

var final = false

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        val btnFavoritos : Button = findViewById(R.id.btnFavoritos)
        val txtRegistrar : EditText = findViewById(R.id.userName)

        var listaSacada = arrayListOf<String>()
        var nombre = ""
        var prueba = false
        val dbFirebase = Firebase.firestore
        var cantidad = 0

        fun verificarDuplicidad(nombreNuevo: String){
            dbFirebase.collection("usuarioop")
                .get()
                .addOnSuccessListener { res ->
                    val listNames = arrayListOf<String>()
                    for (document in res){
                        nombre = document.data["nombre"]!! as String
                        listNames.add(nombre)
                        cantidad++
                    }
                    for(i in 0 until listNames.size){
                        if(nombreNuevo == listNames[i]){
                            final = true
                        }
                    }
                    if(txtRegistrar.text.toString() == ""){
                        Toast.makeText(this,"Esta vacio el espacio",Toast.LENGTH_SHORT).show()
                    }else if(final!!){
                        final = false
                        println("SALIO ESTA COSA $final")
                        Toast.makeText(this,"Ya esta registrada",Toast.LENGTH_SHORT).show()
                        val intent: Intent = Intent()
                        usuarioactual = txtRegistrar.text.toString()
                        ventana = "normal"
                        intent.setClass(this, MainActivity::class.java)
                        startActivity(intent)
                    }else if(!final!!){
                        final = true
                        LoginManager.instance.guardarUsuario(
                            txtRegistrar.text.toString())

                        FavoritosManager.instance.guardarFavoritos(
                            txtRegistrar.text.toString(),
                            ArrayList(),
                            {
                                Log.e("FAVORITE REGISTERED", "CORRECT")
                            },
                            {
                                Log.e("Registrar favoritos",it)
                                Toast.makeText(this,"LA CAGASTE",Toast.LENGTH_SHORT).show()
                            }
                        )
                        val intent: Intent = Intent()
                        usuarioactual = txtRegistrar.text.toString()
                        ventana = "normal"
                        intent.setClass(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    println(cantidad)
                    println("me pusieron aca" + listNames.size)
                    println("verifique $final")
                }
            println("ANTES DE VERIFICAR $final")
        }

        fun verificarDuplicidad2(nombreNuevo: String){
            dbFirebase.collection("usuarioop")
                .get()
                .addOnSuccessListener { res ->
                    val listNames = arrayListOf<String>()
                    for (document in res){
                        nombre = document.data["nombre"]!! as String
                        listNames.add(nombre)
                        cantidad++
                    }
                    for(i in 0 until listNames.size){
                        if(nombreNuevo == listNames[i]){
                            final = true
                        }
                    }
                    if(txtRegistrar.text.toString() == ""){
                        Toast.makeText(this,"Esta vacio el espacio",Toast.LENGTH_SHORT).show()
                    }else if(final!!){
                        final = false
                        println("SALIO ESTA COSA $final")
                        Toast.makeText(this,"Ya esta registrada",Toast.LENGTH_SHORT).show()
                        val intent: Intent = Intent()
                        usuarioactual = txtRegistrar.text.toString()
                        ventana = "favoritos"
                        intent.setClass(this, MainActivity::class.java)
                        startActivity(intent)
                    }else if(!final!!){
                        final = true
                        LoginManager.instance.guardarUsuario(
                            txtRegistrar.text.toString())

                        FavoritosManager.instance.guardarFavoritos(
                            txtRegistrar.text.toString(),
                            ArrayList(),
                            {
                                Log.e("FAVORITE REGISTERED", "CORRECT")
                            },
                            {
                                Log.e("Registrar favoritos",it)
                                Toast.makeText(this,"LA CAGASTE",Toast.LENGTH_SHORT).show()
                            }
                        )
                        val intent: Intent = Intent()
                        usuarioactual = txtRegistrar.text.toString()
                        ventana = "favoritos"
                        intent.setClass(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    println(cantidad)
                    println("me pusieron aca" + listNames.size)
                    println("verifique $final")
                }
            println("ANTES DE VERIFICAR $final")
        }

        btnContinuar.setOnClickListener { v : View ->
            verificarDuplicidad(txtRegistrar.text.toString())
        }

        btnFavoritos.setOnClickListener{_ : View ->
            verificarDuplicidad2(txtRegistrar.text.toString())
            println("-------------------------------------xxxxxxxxxxxxxxxxxx----------------------")
            for(i in 0..(listaFav.size-1)){
                println("pokkemones en favorito..---------------------->"+ listaFav[i].nombre)
            }

            for(i in 0..(listanueva.size-1)){
                println("pokkemones generales..------------------------>"+ listanueva[i].nombre)
            }
        }
    }
}