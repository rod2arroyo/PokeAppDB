package com.example.pokeappdb.model

import android.content.Context
import androidx.activity.OnBackPressedCallback
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PokemonManager(context: Context) {
    private val dbFirebase = Firebase.firestore

    fun getPokemonFB(callbackOK: (List<Pokemon>) -> Unit, callbackError: (String) -> Unit){
        dbFirebase.collection("pokemones")
            .get()
            .addOnSuccessListener { res ->
                val pokemones = arrayListOf<Pokemon>()
                for(document in res){
                    val poke = Pokemon(
                        nombre = document.data["nombre"]!! as String,
                        hp = (document.data["hp"]!! as Long).toInt(),
                        attack = (document.data["attack"]!! as Long).toInt(),
                        defense = (document.data["defense"]!! as Long).toInt(),
                        specialAttack = (document.data["specialAttack"]!! as Long).toInt(),
                        specialDefense = (document.data["specialDefense"]!! as Long).toInt(),
                        url = document.data["url"]!! as String,
                    )
                    pokemones.add(poke)
                    println(poke)
                }
                callbackOK(pokemones)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }
    var nombre = ""
    var prueba = false
    fun verificarDuplicidad(nombreNuevo: String, listOK: (List<String>) -> Unit, valorBusqueda : (Boolean) -> Unit){
        dbFirebase.collection("usuarios")
            .get()
            .addOnSuccessListener { res ->
                val listNames = arrayListOf<String>()
                for (document in res){
                    nombre = document.data["nombre"]!! as String
                    listNames.add(nombre)
                }
                for(i in 0 until listNames.size){
                    if(nombreNuevo == listNames[i]){
                        prueba = true
                    }
                }
                listOK(listNames)
                valorBusqueda(prueba)
            }
    }



}