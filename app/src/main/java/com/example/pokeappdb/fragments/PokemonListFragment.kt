package com.example.pokeappdb.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeappdb.R
import com.example.pokeappdb.adapter.PokemonListAdapter
import com.example.pokeappdb.model.Pokemon
import com.example.pokeappdb.model.PokemonManager

class PokemonListFragment : Fragment() {
    interface OnPokemonSelectedListener{
        fun onSelect(pokemon: Pokemon)
    }
    private var listener : OnPokemonSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokelist,container,false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnPokemonSelectedListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var rviPokemon = view.findViewById<RecyclerView>(R.id.rviPokemon)

        PokemonManager(requireActivity().applicationContext).getPokemonFB({pkList : List<Pokemon> ->
            rviPokemon!!.adapter = PokemonListAdapter(
                pkList,
                this
            ){pokemon : Pokemon ->
                listener?.onSelect(pokemon)
            }
        }) { error ->
            Log.e("PokemonFragment", error)
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        }
    }
}