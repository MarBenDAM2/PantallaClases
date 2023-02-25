package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.speech.tts.TextToSpeech
import android.widget.Button
import com.example.pantallaclases.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var nom_clase = ""
    private lateinit var binding: ActivityMainBinding
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista = arrayListOf(binding.buttonBerserker, binding.buttonThief, binding.buttonMago,
            binding.buttonGuerrero, binding.buttonAceptar)

        llamadaBotones(lista)

    }

    fun llamadaBotones(lista: ArrayList<Button>){
        llamada_tts("Bienvenido al juego, elige una clase")

        //He creado un array con todos los botones para no ir uno por uno y que se vea mejor
        for (button in lista){
            button.setOnClickListener {
                when (button) {

                    binding.buttonBerserker -> {
                        //Cambiamos la imagen
                        binding.ImagenClase.setImageResource(R.mipmap.berserker)

                        nom_clase = "berserker"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                        llamada_tts("Has elegido la clase berserker")
                    }

                    binding.buttonThief -> {
                        //Cambiamos la imagen
                        binding.ImagenClase.setImageResource(R.mipmap.ladron)

                        nom_clase = "ladron"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                        llamada_tts("Has elegido la clase ladrón")
                    }
                    binding.buttonMago -> {
                        //Cambiamos la imagen
                        binding.ImagenClase.setImageResource(R.mipmap.mago)

                        nom_clase = "mago"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                        llamada_tts("Has elegido la clase mago")
                    }
                    binding.buttonGuerrero -> {

                        //Cambiamos la imagen
                        binding.ImagenClase.setImageResource(R.mipmap.guerrero)

                        nom_clase = "guerrero"

                        //Hacemos visible la imagen
                        binding.ImagenClase.visibility = View.VISIBLE

                        llamada_tts("Has elegido la clase guerrero")

                    }
                    binding.buttonAceptar -> {
                        //Hacemos el intent a la siguiente actividad (Eleccion de raza)
                        crearActividad(Raza::class.java, nom_clase)
                    }
                }

            }
        }
    }

    fun llamada_tts(texto: String){
        tts = TextToSpeech(this) { status ->
            if (status != TextToSpeech.ERROR) {
                tts.language = java.util.Locale("es", "ES")
                tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null)
            }
        }
    }

    fun crearActividad(clase : Class<*>, nom_clase : String){
        val intent = Intent(this, clase)
        intent.putExtra("clase", nom_clase)

        startActivity(intent)
    }

}