package com.example.pokeappdb.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeappdb.*

import com.example.pokeappdb.adapter.favoritepokeadapter
import com.example.pokeappdb.adapter.favoritepokeadaptersolonombre
import com.example.pokeappdb.model.Pokemon
import com.example.pokeappdb.model.PokemonManager
import com.example.pokeappdb.model.Usuario
import com.example.pokeappdb.model.UsuarioManager


class PokemonListFavoriteFragment: Fragment() {
    lateinit var ACTIVITY : MainActivity

    interface OnMenuClicked{
        fun OnClick(menuName: String)
    }

    private var listener: OnMenuClicked? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnMenuClicked
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ACTIVITY = context as MainActivity
        return inflater.inflate(R.layout.fragment_favoritepokelist,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rviPokemon = view.findViewById<RecyclerView>(R.id.rviPokemonfavorito)


//        var nombre = view.findViewById<TextView>(R.id.textpokemonfavorito)
//        var bute = view.findViewById<Button>(R.id.buteliminarfavorito)
//        bute?.setOnClickListener{ _ : View ->
//
//            println("----------->>> eliminar favoirto-------->>>>" + nombre?.text )
//            for(i in 0..(listanueva.size-1)){
//                if(listanueva[i].nombre==nombre?.text){
//                    pokemonactual = listanueva[i]
//                }
//            }
//
//            var ultimalista : ArrayList<Pokemon> = arrayListOf()
//            for(i in 0..(listaFav.size-1)){
//                if(listaFav[i].nombre != nombre?.text){
//                    ultimalista.add(listaFav[i])
//                }
//            }
//            listaFav=ultimalista
//        }

        println("---------------------->>>>>> el nombre es : " + usuarioactual + "<<<<<<---------")

        UsuarioManager(requireActivity().applicationContext).getUsuariocompletoFB({ usuList : List<Usuario> ->

            for (i in 0..(usuList.size-1)){
                listacompleta.add(usuList[i])
            }
            for(i in 0..(listacompleta.size-1)){
                if (listacompleta[i].nombre== usuarioactual)
                {
                    listafavsolonombre= listacompleta[i].favoritos
                }
            }

            for (i in 0..(listacompleta.size-1)){
                println("---------------------->>>>>>lista completa es : " + listacompleta[i] + "<<<<<<---------")
            }

            for (i in 0..(listafavsolonombre.size-1)){
                println("---------------------->>>>>> el ususrio es : " + listafavsolonombre[i] + "<<<<<<---------")
            }

        }){ error ->
            Log.e("PokemonFragment--xx--op", error)
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        }



        rviPokemon.adapter = favoritepokeadaptersolonombre(
            listafavsolonombre
        ) { pokemon: String ->
            //  pokemonactual = pokemon
            //Log.i(" fvorito ",pokemon)
            //     listener?.OnClick("verinfo")


            listener?.OnClick("favoritoop")
        }


//        rviPokemon.adapter = favoritepokeadapter(
//            listaFav
//        ) { pokemon: Pokemon ->
//            //  pokemonactual = pokemon
//            Log.i(" fvorito ",pokemon.nombre)
//            //     listener?.OnClick("verinfo")
//
//
//            listener?.OnClick("favoritoop")
//        }


    }
}