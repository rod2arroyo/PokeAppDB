package com.example.pokeappdb

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        val btnFavoritos : Button = findViewById(R.id.btnFavoritos)

        btnContinuar.setOnClickListener { _: View ->
            val intent: Intent = Intent()
            ventana = "normal"
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnFavoritos.setOnClickListener{_ : View ->
            val intent: Intent = Intent()
            ventana = "favoritos"
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
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