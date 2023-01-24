package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import com.example.pantallaclases.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
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

                        //Ponemos el contentdescription para poder cogerlo en la siguiente actividad
                        binding.ImagenClase.contentDescription = "berserker"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                    }

                    binding.buttonThief -> {
                        //Cambiamos la imagen
                        binding.ImagenClase.setBackgroundResource(R.drawable.ladron)

                        //Ponemos el contentdescription para poder cogerlo en la siguiente actividad
                        binding.ImagenClase.contentDescription = "ladron"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                    }
                    binding.buttonMago -> {
                        //Cambiamos la imagen
                        binding.ImagenClase.setBackgroundResource(R.drawable.mago)

                        //Ponemos el contentdescription para poder cogerlo en la siguiente actividad
                        binding.ImagenClase.contentDescription = "mago"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE
                    }
                    binding.buttonGuerrero -> {

                        //Cambiamos la imagen
                        binding.ImagenClase.setBackgroundResource(R.drawable.guerrero)

                        //Ponemos el contentdescription para poder cogerlo en la siguiente actividad
                        binding.ImagenClase.contentDescription = "guerrero"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                    }
                    binding.buttonAccept -> {
                        //Hacemos el intent a la siguiente actividad (Eleccion de raza)
                        crearActividad(Raza::class.java)
                    }
                }

            }
        }
    }

    fun crearActividad(clase : Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)

        intent.putExtra("clase", binding.ImagenClase.contentDescription)
    }

}