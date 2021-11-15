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

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        val btnFavoritos : Button = findViewById(R.id.btnFavoritos)
        val txtRegistrar : EditText = findViewById(R.id.userName)

        var listaSacada = arrayListOf<String>()

        var prueba = false

        PokemonManager(this).verificarDuplicidad(txtRegistrar.text.toString(),{pkList : List<String> ->
            listaSacada = pkList as ArrayList<String>
            println(" ACA VA LA LISTA $listaSacada")
        },{pkotracosa: Boolean ->
            prueba = pkotracosa
            println(" ACA VA EL BOOLEAN $prueba")
        })


        fun comprobarAntes(){
            PokemonManager(this).verificarDuplicidad(txtRegistrar.text.toString(),{pkList : List<String> ->
                listaSacada = pkList as ArrayList<String>
                println(" ACA VA LA LISTA $listaSacada")
            },{pkotracosa: Boolean ->
                prueba = pkotracosa
                println(" ACA VA EL BOOLEAN $prueba")
            })
        }

        btnContinuar.setOnClickListener { v : View ->
            comprobarAntes()
            println("ANTES DE VERIFICAR $prueba")
            if(txtRegistrar.text.toString() == ""){
                Toast.makeText(this,"Esta vacio el espacio",Toast.LENGTH_SHORT).show()
            }else if(prueba!!){
                println("SALIO ESTA COSA $prueba")
                Toast.makeText(this,"Ya esta registrada",Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent()
                usuarioactual = txtRegistrar.text.toString()
                ventana = "normal"
                intent.setClass(this, MainActivity::class.java)
                startActivity(intent)
            }else if(!prueba!!){
                LoginManager.instance.guardarUsuario(
                    txtRegistrar.text.toString(),
                    {
                        Log.e("USER REGISTERED", "CORRECT")
                    },
                    {
                        Log.e("Registrar usuario",it)
                        Toast.makeText(this,"LA CAGASTE",Toast.LENGTH_SHORT).show()
                    }
                )
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

        }

        btnFavoritos.setOnClickListener{_ : View ->
            comprobarAntes()
            println("ANTES DE VERIFICAR $prueba")
            if(txtRegistrar.text.toString() == ""){
                Toast.makeText(this,"Esta vacio el espacio",Toast.LENGTH_SHORT).show()
            }else if(prueba!!){
                println("SALIO ESTA COSA $prueba")
                Toast.makeText(this,"Ya esta registrada",Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent()
                usuarioactual = txtRegistrar.text.toString()
                ventana = "favoritos"
                intent.setClass(this, MainActivity::class.java)
                startActivity(intent)
            }else if(!prueba!!){
                LoginManager.instance.guardarUsuario(
                    txtRegistrar.text.toString(),
                    {
                        Log.e("USER REGISTERED", "CORRECT")
                    },
                    {
                        Log.e("Registrar usuario",it)
                        Toast.makeText(this,"LA CAGASTE",Toast.LENGTH_SHORT).show()
                    }
                )
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