package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.example.pantallaclases.databinding.ActivityRandomEventBinding
import com.google.gson.Gson
import java.util.*

class RandomEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomEventBinding
    private lateinit var personaje: Personaje
    lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_event)
        binding = ActivityRandomEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        llamada_tts("Pulsa el dado.")

        binding.buttonRandEvent.setOnClickListener {
            selectEvent()
        }

        //Para el personaje
        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)
    }

    private fun selectEvent(){

        when ((1..4).random()) {
            1 -> {
                crearIntent(ObjetoActivity::class.java)
            }
            2 -> {
                crearIntent(CiudadActivity::class.java)
            }
            3 -> {
                crearIntent(MercaderActivity::class.java)
            }
            4 -> {
                crearIntent(EnemigoActivity::class.java)
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

    private fun crearIntent(Class: Class<*>){
        val intent = Intent(this, Class)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        startActivity(intent)
    }
}