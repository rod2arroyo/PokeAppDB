package com.example.pokeappdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.pokeappdb.R
import com.example.pokeappdb.listaFav
import com.example.pokeappdb.listanueva
import com.example.pokeappdb.model.Pokemon
import com.example.pokeappdb.pokemonactual

class favoritepokeadapter(
    private val recipeList : List<Pokemon>,
    private val listener : (Pokemon) -> Unit) :
    RecyclerView.Adapter<favoritepokeadapter.ViewHolder>() {
    class ViewHolder(view: View, val listener :(Pokemon) -> Unit, val recipeList : List<Pokemon>) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        val nombre: TextView
        val bute: Button

        init {
            nombre = view.findViewById(R.id.textpokemonfavorito)
            bute = view.findViewById(R.id.buteliminarfavorito)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(recipeList[adapterPosition])
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorito, parent,false)

        val viewHolder = ViewHolder(view,listener,recipeList)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombre.text = recipeList[position].nombre
        holder.bute.setOnClickListener{ _ : View ->

            println("----------->>> eliminar favoirto-------->>>>" + holder.nombre.text )

            for(i in 0..(listanueva.size-1)){
                if(listanueva[i].nombre==holder.nombre.text){
                    pokemonactual= listanueva[i]
                }
            }

            var ultimalista : ArrayList<Pokemon> = arrayListOf()
            for(i in 0..(listaFav.size-1)){
                if(listaFav[i].nombre != holder.nombre.text){
                    ultimalista.add(listaFav[i])
                }
            }

            listaFav=ultimalista

        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}