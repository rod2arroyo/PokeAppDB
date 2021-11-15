package com.example.pokeappdb.model

import android.content.Context
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UsuarioManager(context: Context) {
    private val dbFirebase = Firebase.firestore

    fun getUsuariocompletoFB(callbackOK: (List<Usuario>) -> Unit, callbackError: (String) -> Unit){
        dbFirebase.collection("usuarioop")
            .get()
            .addOnSuccessListener { res ->
                val  usuarioslistaop= arrayListOf<Usuario>()
                for(document in res){
                    val constanteusuario = Usuario(
                        nombre = document.data["nombre"]!! as String,
                        favoritos = document.data["favoritos"]!! as ArrayList<String>
                    )
                    usuarioslistaop.add(constanteusuario)
                    println(constanteusuario)
                }
                callbackOK(usuarioslistaop)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }




    fun guardarlista(favoritos: ArrayList<String>,
                       callbackOK: (Long) -> Unit,
                       callbackError: (String) -> Unit){

        val data = hashMapOf<String, Any>(
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