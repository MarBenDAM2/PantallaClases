package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.example.pantallaclases.databinding.ActivityEnemigoBinding
import com.google.gson.Gson
import java.util.*

class EnemigoActivity : AppCompatActivity() {
    lateinit var binding : ActivityEnemigoBinding
    lateinit var tts : TextToSpeech
    lateinit var personaje: Personaje

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemigo)
        binding = ActivityEnemigoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)

        binding.button7.setOnClickListener{
            llamada_tts("Has elegido luchar! Un chico valiente.")
            clase(FightActivity::class.java)
        }
        binding.button8.setOnClickListener{
            llamada_tts("Has elegido huir! Que cobarde.")
            clase(RandomEventActivity::class.java)
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

    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        startActivity(intent)
    }
}