package com.example.pokeappdb.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoritosManager {
    companion object{
        var instance : FavoritosManager = FavoritosManager()
            private set
    }

    private val dbFirebase = Firebase.firestore

    fun guardarFavoritos(nombre: String,
                       favoritos: ArrayList<String>,
                       callbackOK: (Long) -> Unit,
                       callbackError: (String) -> Unit){

        val data = hashMapOf<String, Any>(
            "nombre" to nombre,
            "favoritos" to favoritos
        )

        val userId = System.currentTimeMillis()
        dbFirebase.collection("usuarioop")
            .document(userId.toString())
            .set(data)
            .addOnSuccessListener {
                callbackOK(userId)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }
}