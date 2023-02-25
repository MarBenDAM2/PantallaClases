package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.example.pantallaclases.databinding.ActivityDeadBinding

class DeadActivity : AppCompatActivity() {
    lateinit var binding : ActivityDeadBinding
    lateinit var tts : TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dead)
        binding = ActivityDeadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        llamada_tts("Has muerto.")

        binding.buttonReiniciar.setOnClickListener {
            aInicio(MainActivity::class.java)
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
    fun aInicio(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}