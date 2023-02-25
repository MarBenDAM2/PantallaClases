package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.example.pantallaclases.databinding.ActivityCiudadBinding
import com.google.gson.Gson
import java.util.*

class CiudadActivity : AppCompatActivity() {
    lateinit var binding : ActivityCiudadBinding
    lateinit var personaje: Personaje
    lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)
        binding = ActivityCiudadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)

        llamada_tts("OJO! Una ciudad! ¿Quieres entrar o mejor te vas?")

        binding.button3.setOnClickListener{
            llamada_tts("Pasas de largo")
            clase(RandomEventActivity::class.java)
        }
        binding.button4.setOnClickListener(){
            llamada_tts("Te has metido en la ciudad, ¿Qué quieres hacer?")
            clase(EnterActivity::class.java)
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