package com.example.pokeappdb.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeappdb.*
import com.example.pokeappdb.fragments.PokemonListFavoriteFragment

import com.example.pokeappdb.model.Pokemon
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class favoritepokeadaptersolonombre(
    private val recipeList : ArrayList<String>,
    val eliminar: (String) -> Unit,
    private val listener : (String) -> Unit) :
    RecyclerView.Adapter<favoritepokeadaptersolonombre.ViewHolder>() {
    class ViewHolder(view: View, val listener :(String) -> Unit, val recipeList : ArrayList<String>,val eliminar: (String) -> Unit) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        val nombre: TextView
        val bute: Button

        init {
            nombre = view.findViewById(R.id.textpokemonfavorito)
            bute = view.findViewById(R.id.buteliminarfavorito)
            bute.setOnClickListener {
                val name = recipeList[position]
                eliminar(name)
                bute.setBackgroundColor(Color.RED)
            }

            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(recipeList[adapterPosition])
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorito, parent,false)
        println("----------->>> entreeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee-------->>>>"  )
        val viewHolder = ViewHolder(view,listener,recipeList,eliminar)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombre.text = recipeList[position]
 //       holder.bute.setOnClickListener{ _ : View ->

//            println("----------->>> eliminar favoirto-------->>>>" + holder.nombre.text )
//
//
//
//
//            var listaop: List<String> = listOf()
//            var arrayop :ArrayList<String> = arrayListOf()
//            println("--------------------------------------")
//            println(arrayop)
//            println("--------------------------------------")
//
//            for(i in 0..(recipeList.size-1)){
//                if(recipeList[i]!=holder.nombre.text)
//                {
//                    arrayop.add(recipeList[i])
//                }
//            }
//            println("--------------------------------------")
//            println(arrayop)
//            println("--------------------------------------")
//            listaop = arrayop
//
//            val dbFirebase = Firebase.firestore
//            val data = hashMapOf("favoritos" to listaop)
//            dbFirebase.collection("usuarioop").document(usuarioactual).set(data, SetOptions.merge())
//
//       }

    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}