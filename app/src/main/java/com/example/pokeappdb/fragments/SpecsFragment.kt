package com.example.pokeappdb.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pokeappdb.*
import com.example.pokeappdb.model.Usuario
import com.example.pokeappdb.model.UsuarioManager
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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

        //favoritos


        val btnAgregarfavorito = view.findViewById<Button>(R.id.buttonfavorito)

        if(ven=="spects"){
            listacompleta.clear()
            listafavsolonombre.clear()
        }
        btnAgregarfavorito.setOnClickListener{ _ : View ->
            println("-------------------------------------------------" )
            println("--------------xxxxxxxxxxxxxxxxxantesxxxx222-----------" + listafavsolonombre)
            UsuarioManager(requireActivity().applicationContext).getUsuariocompletoFB({ usuList : List<Usuario> ->
                for (i in 0..(usuList.size-1)){
                    listacompleta.add(usuList[i])
                }
                for(i in 0..(listacompleta.size-1)){
                    if (listacompleta[i].nombre== usuarioactual){
                        listafavsolonombre= listacompleta[i].favoritos
                        println("esta es la listaaa for ---------------------------------->>>>>>>>>>------->>>" +listacompleta[i].favoritos )
                    }
                }


                var listaop: List<String> = listOf()
                var x: Int = 0
                for (i in 0..(listafavsolonombre.size-1)){
                    if(listafavsolonombre[i]==pokemonactual.nombre){x++}
                }
                if(x==0)
                {
                    listafavsolonombre.add(pokemonactual.nombre)
                    Toast.makeText(activity, "Se agrego el pokemon", Toast.LENGTH_SHORT).show()

                }

                println("--------------xxxxxxxxxxxxxxxxxdespus-----------" + listafavsolonombre)
                listaop = listafavsolonombre

                val dbFirebase = Firebase.firestore
                val data = hashMapOf("favoritos" to listaop)
                dbFirebase.collection("usuarioop").document(usuarioactual).set(data, SetOptions.merge())

            }){ error ->
                Log.e("PokemonFragment--xx--op", error)
                Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
            }

            for (i in 0..(listacompleta.size-1)){
                if(listacompleta[i].nombre== usuarioactual){
                    println("--------------xxxxxxxxxxxxxxxxxcompletaaaaasnombres-----------" + listacompleta[i].favoritos)
                }

            }


            println("--------------xxxxxxxxxxxxxxxxxantes-----------" + listafavsolonombre)

            //logica bd

            ven = "no"
        }




    }



}