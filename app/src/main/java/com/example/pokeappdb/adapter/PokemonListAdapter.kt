package com.example.pokeappdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeappdb.R
import com.example.pokeappdb.model.Pokemon

class PokemonListAdapter (
    private val pokemonList : List<Pokemon>,
    private val fragment: Fragment,
    private val listener : (Pokemon) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    class ViewHolder(view: View, val listener :(Pokemon) -> Unit,
                     val pokemonList : List<Pokemon>) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val url : ImageView
        val nombre: TextView
        val hp: TextView
        val attack: TextView
        val defense: TextView
        val specialAttack: TextView
        val specialDefense: TextView

        init {
            url = view.findViewById(R.id.imgPokemon)
            nombre = view.findViewById(R.id.txtnombrefvorito)
            hp = view.findViewById(R.id.texthp)
            attack = view.findViewById(R.id.textattack)
            defense = view.findViewById(R.id.textdefense)
            specialAttack = view.findViewById(R.id.textespecialattack)
            specialDefense = view.findViewById(R.id.textspecialdefense)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(pokemonList[adapterPosition])
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent,false)

        val viewHolder = ViewHolder(view, listener, pokemonList)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombre.text = pokemonList[position].nombre
        holder.hp.text = pokemonList[position].hp.toString()
        holder.attack.text = pokemonList[position].attack.toString()
        holder.defense.text = pokemonList[position].defense.toString()
        holder.specialAttack.text = pokemonList[position].specialAttack.toString()

        Glide.with(fragment)
            .load(pokemonList[position].url)
            .override(550,650)
            .fitCenter()
            .into(holder.url)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}