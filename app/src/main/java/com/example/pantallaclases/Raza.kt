package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import com.example.pantallaclases.databinding.ActivityRazaBinding
import java.util.*
import kotlin.collections.ArrayList

class Raza : AppCompatActivity() {
    var raza = ""
    private lateinit var binding: ActivityRazaBinding
    lateinit var tts: TextToSpeech
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
        llamada_tts("Elige una raza")
        //He creado un array con todos los botones para no ir uno por uno y que se vea mejor
        for (button in lista){
            button.setOnClickListener {
                when (button) {
                    binding.buttonElf -> {
                        //Cambiamos la imagen
                        binding.ImagenRaza.setImageResource(R.drawable.elfo)

                        raza = "elfo"

                        //Hacemos visible la imagen
                        binding.ImagenRaza.visibility = View.VISIBLE

                        llamada_tts("Has elegido la raza elfo")

                    }
                    binding.buttonEnano -> {

                        //Cambiamos la imagen
                        binding.ImagenRaza.setImageResource(R.drawable.enano)

                        raza = "enano"

                        //Hacemos visible la imagen
                        binding.ImagenRaza.visibility = View.VISIBLE

                        llamada_tts("Has elegido la raza enano")
                    }
                    binding.buttonHumano -> {

                        //Cambiamos la imagen
                        binding.ImagenRaza.setImageResource(R.drawable.humano)

                        raza = "humano"

                        //Hacemos visible la imagen
                        binding.ImagenRaza.visibility = View.VISIBLE

                        llamada_tts("Has elegido la raza humano")
                    }
                    binding.buttonGoblin -> {
                        //Cambiamos la imagen
                        binding.ImagenRaza.setImageResource(R.drawable.goblin)

                        raza = "goblin"

                        //Hacemos visible la imagen
                        binding.ImagenRaza.visibility = View.VISIBLE

                        llamada_tts("Has elegido la raza goblin")
                    }
                    binding.buttonAccept -> {
                        //Hacemos el intent a la siguiente actividad (Resumen)
                        crearActividad(Resumen::class.java, raza)
                    }
                }

            }
        }
    }

    fun llamada_tts(texto: String){
        tts = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                tts.language = Locale("es", "ES")
                tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null)
            }
        })
    }

    fun crearActividad(clase : Class<Resumen>, raza : String){
        val new_intent = Intent(this, clase)
        new_intent.putExtra("clase", intent.getStringExtra("clase"))
        new_intent.putExtra("raza", raza)

        startActivity(new_intent)
    }


}