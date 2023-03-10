package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import com.example.pantallaclases.databinding.ActivityObjetoBinding
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList


data class Objeto(
    var nombre: String,
    var peso: Int,
    var precio: Int,
    var vida: Int
) {
    override fun toString(): String {
        return "\nNombre: $nombre, peso: $peso, precio: $precio, vida: $vida"
    }
}

class ObjetoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityObjetoBinding
    lateinit var personaje: Personaje
    lateinit var tts: TextToSpeech

    val arrayObj : ArrayList<String> = arrayListOf("Espada", "Escudo", "Arco", "Maza", "Vara")

    private val objeto = Objeto(arrayObj.random(), 5, 10, 20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)
        binding = ActivityObjetoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        llamada_tts("Has encontrado un objeto")

        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)

        //Que escoja uno aleatorio y cambie a la foto correspondiente.
        when (objeto.nombre){
            "Espada" -> binding.imageView2.setImageResource(R.drawable.espada)
            "Escudo" -> binding.imageView2.setImageResource(R.drawable.escudo)
            "Arco" -> binding.imageView2.setImageResource(R.drawable.arco)
            "Maza" -> binding.imageView2.setImageResource(R.drawable.maza)
            "Vara" -> binding.imageView2.setImageResource(R.drawable.vara)
        }

        if (intent.getIntExtra("Veces", 0) == 1){
            binding.button.isEnabled = false
        }
        binding.button.setOnClickListener{
            crearIntent(RandomEventActivity::class.java)
        }

        binding.button2.setOnClickListener{
            llamada_tts("Recogiste el objeto")
            //Si el tama??o de la mochila es mayor o igual al peso del objeto
            if (personaje.mochila.tam >= objeto.peso){
                //Reduce el peso de la mochila
                personaje.mochila.tam -= objeto.peso
                //Lo a??ade a la mochila
                personaje.mochila.objetos.add(objeto)
                //Muestra el texto de "RECOGIDO"
                binding.textView2.visibility = View.VISIBLE
            }

            //Esta comprobacion es para ver si viene desde la ciudad o no.
            if (intent.getIntExtra("Veces", 0) == 1){
                crearIntent(EnterActivity::class.java)
            } else {
                crearIntent(RandomEventActivity::class.java)
            }
        }
    }
    private fun crearIntent(Class: Class<*>){
        val intent = Intent(this, Class)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        startActivity(intent)
    }

    fun llamada_tts(texto: String){
        tts = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                tts.language = Locale("es", "ES")
                tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null)
            }
        })
    }



}