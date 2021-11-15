package com.example.pokeappdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pokeappdb.fragments.PokemonListFavoriteFragment
import com.example.pokeappdb.fragments.PokemonListFragment
import com.example.pokeappdb.fragments.SpecsFragment
import com.example.pokeappdb.model.Pokemon
import com.example.pokeappdb.model.Usuario

var pokemonactual  = Pokemon("",  0,0,0,0,0,"")
var listaFav : ArrayList<Pokemon> = arrayListOf()
var listanueva : List<Pokemon> = arrayListOf()
var ventana = ""
var listacompleta :ArrayList<Usuario> = arrayListOf()
var usuarioactual : String = ""
var listafavsolonombre :ArrayList<String> = arrayListOf()
var poke  = Pokemon("dweeed",  0,0,0,0,0,"")
class MainActivity : AppCompatActivity(), PokemonListFragment.OnPokemonSelectedListener, SpecsFragment.OnMenuClicked,
    PokemonListFavoriteFragment.OnMenuClicked {
    private val fragments = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragments.add(PokemonListFragment())
        fragments.add(SpecsFragment())
        fragments.add(PokemonListFavoriteFragment())

        listaFav.add(poke)

        if(ventana=="favoritos"){
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.flaContent,fragments[2])
            println("-------------------------------------------------------------------------------------")
            ft.commit()
        }else{
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.flaContent,fragments[0])
            ft.commit()
        }
    }

    private fun changePokemonDetailFragment(pokemon : Pokemon) {
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment)
        pokemonactual=pokemon
        ft.commit()
    }

    override fun onSelect(pokemon: Pokemon) {
        changePokemonDetailFragment(pokemon)
    }
    fun verfavorito(){
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent,fragment)
        ft.commit()
    }
    override fun OnClick(menuName: String) {
        if(menuName == "verinfo"){
        //
        }
        else if(menuName == "favoritoop"){
            verfavorito()
        }
        else if(menuName == "pokes"){

        }
    }
}