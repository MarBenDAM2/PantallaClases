package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pantallaclases.databinding.ActivityRazaBinding

class Raza : AppCompatActivity() {
    private lateinit var binding: ActivityRazaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raza)
        binding = ActivityRazaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista = arrayListOf(binding.buttonElf, binding.buttonEnano, binding.buttonHumano,
            binding.buttonGoblin, binding.buttonAccept)

        llamadaBotones(lista)
    }
    fun llamadaBotones(lista: ArrayList<Button>){
        //He creado un array con todos los botones para no ir uno por uno y que se vea mejor
        for (button in lista){
            button.setOnClickListener {
                when (button) {
                    binding.buttonElf -> {
                        //Cambiamos la imagen
                        binding.ImagenRaza.setBackgroundResource(R.drawable.elfo)

                        //Ponemos el contentdescription para poder cogerlo en la siguiente actividad
                        binding.ImagenRaza.contentDescription = "elfo"

                        //Hacemos visible la imagen
                        binding.ImagenRaza.visibility = View.VISIBLE

                    }
                    binding.buttonEnano -> {
                        //Cambiamos la imagen
                        binding.ImagenRaza.setBackgroundResource(R.drawable.enano)

                        //Ponemos el contentdescription para poder cogerlo en la siguiente actividad
                        binding.ImagenRaza.contentDescription = "enano"

                        //Hacemos visible la imagen
                        binding.ImagenRaza.visibility = View.VISIBLE

                    }
                    binding.buttonHumano -> {
                        //Cambiamos la imagen
                        binding.ImagenRaza.setBackgroundResource(R.drawable.humano)

                        //Ponemos el contentdescription para poder cogerlo en la siguiente actividad
                        binding.ImagenRaza.contentDescription = "humano"

                        //Hacemos visible la imagen
                        binding.ImagenRaza.visibility = View.VISIBLE

                    }
                    binding.buttonGoblin -> {
                        //Cambiamos la imagen
                        binding.ImagenRaza.setBackgroundResource(R.drawable.goblin)

                        //Ponemos el contentdescription para poder cogerlo en la siguiente actividad
                        binding.ImagenRaza.contentDescription = "goblin"

                        //Hacemos visible la imagen
                        binding.ImagenRaza.visibility = View.VISIBLE

                    }
                    binding.buttonAccept -> {
                        //Hacemos el intent a la siguiente actividad (Resumen)
                        crearActividad(Resumen::class.java)
                    }
                }

            }
        }
    }
    fun crearActividad(clase : Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
        intent.putExtra("raza", binding.ImagenRaza.contentDescription)
    }


}