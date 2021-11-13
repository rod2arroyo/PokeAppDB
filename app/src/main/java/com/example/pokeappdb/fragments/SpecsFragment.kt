package com.example.pokeappdb.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pokeappdb.*


class SpecsFragment : Fragment(){
    lateinit var ACTIVITY : MainActivity
    interface OnMenuClicked{
        fun OnClick(menuName: String)
    }
    private var listener: OnMenuClicked? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ACTIVITY = context as MainActivity
        return inflater.inflate(R.layout.fragment_specs,container,false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnMenuClicked
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //favoritos
        val btnAgregarfavorito = view.findViewById<Button>(R.id.buttonfavorito)
        btnAgregarfavorito.setOnClickListener{ _ : View ->

            var x: Int = 0
            for(i in 0..(listaFav.size-1)){
                if(listaFav[i].nombre == pokemonactual.nombre)
                {
                    x++
                }
            }


            if(x==0){
                listaFav.add(pokemonactual)
            }

            println("contador xxxxxxxxxxxxxxxxxx..---------------------->"+ x)
            println("ingreso de pokemon en lista fav..---------------------->"+ pokemonactual.nombre)
            println("x en  lista fav..---------------------->"+ listaFav.size)
        }

        //datos pokemon actual


        var nombre = view.findViewById<TextView>(R.id.txtnombrefvorito)
        nombre?.text = pokemonactual.nombre

        var hp = view.findViewById<TextView>(R.id.texthpfavorito)
        hp?.text = pokemonactual.hp.toString()

        var attack = view.findViewById<TextView>(R.id.textattackfavorito)
        attack?.text = pokemonactual.attack.toString()

        var defensa = view.findViewById<TextView>(R.id.textdefensefavorito)
        defensa?.text = pokemonactual.defense.toString()

        var espeattack = view.findViewById<TextView>(R.id.textspecialattackfavorito)
        espeattack?.text = pokemonactual.specialAttack.toString()

        var espedefense = view.findViewById<TextView>(R.id.textspecialefencefavorito)
        espedefense?.text = pokemonactual.specialDefense.toString()

        var imagen = view.findViewById<ImageView>(R.id.imageViews)

        Glide.with(this)
            .load(pokemonactual.url)
            .override(137,119)
            .fitCenter()
            .into(imagen)
    }



}