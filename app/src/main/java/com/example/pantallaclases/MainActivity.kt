package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pantallaclases.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var nom_clase = ""
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista = arrayListOf(binding.buttonBerserker, binding.buttonThief, binding.buttonMago,
            binding.buttonGuerrero, binding.buttonAccept)

        llamadaBotones(lista)

    }

    fun llamadaBotones(lista: ArrayList<Button>){

        //He creado un array con todos los botones para no ir uno por uno y que se vea mejor
        for (button in lista){
            button.setOnClickListener {
                when (button) {

                    binding.buttonBerserker -> {
                        //Cambiamos la imagen
                        binding.ImagenClase.setBackgroundResource(R.drawable.berserker)

                        nom_clase = "berserker"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                    }

                    binding.buttonThief -> {
                        //Cambiamos la imagen
                        binding.ImagenClase.setBackgroundResource(R.drawable.ladron)

                        nom_clase = "ladron"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                    }
                    binding.buttonMago -> {
                        //Cambiamos la imagen
                        binding.ImagenClase.setBackgroundResource(R.drawable.mago)

                        nom_clase = "mago"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE
                    }
                    binding.buttonGuerrero -> {

                        //Cambiamos la imagen
                        binding.ImagenClase.setBackgroundResource(R.drawable.guerrero)

                        nom_clase = "guerrero"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                    }
                    binding.buttonAccept -> {
                        //Hacemos el intent a la siguiente actividad (Eleccion de raza)
                        crearActividad(Raza::class.java, nom_clase)
                    }
                }

            }
        }
    }

    fun crearActividad(clase : Class<*>, nom_clase : String){
        val intent = Intent(this, clase)
        intent.putExtra("clase", nom_clase)

        startActivity(intent)
    }

}