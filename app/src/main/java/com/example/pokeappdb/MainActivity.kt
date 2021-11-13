package com.example.pokeappdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pokeappdb.fragments.PokemonListFragment
import com.example.pokeappdb.fragments.SpecsFragment
import com.example.pokeappdb.model.Pokemon

class MainActivity : AppCompatActivity(), PokemonListFragment.OnPokemonSelectedListener {
    private val fragments = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragments.add(PokemonListFragment())
        fragments.add(SpecsFragment())

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContent,fragments[0])
        ft.commit()
    }

    private fun changePokemonDetailFragment(pokemon : Pokemon) {
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment)

        ft.commit()
    }

    override fun onSelect(pokemon: Pokemon) {
        changePokemonDetailFragment(pokemon)
    }


}