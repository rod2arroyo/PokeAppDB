package com.example.pokeappdb.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// Singleton
class LoginManager {
    companion object{
        var instance : LoginManager = LoginManager()
            private set
    }

    private val dbFirebase = Firebase.firestore

    fun guardarUsuario(nombre: String){

        val data = hashMapOf<String, Any>(
            "nombre" to nombre
        )

        val userId = System.currentTimeMillis()
        dbFirebase.collection("usuarios")
            .document(userId.toString())
            .set(data)
            .addOnSuccessListener {
                println("Hola si me cree")
            }
            .addOnFailureListener {
                println("Perdon falle")
            }
    }
}