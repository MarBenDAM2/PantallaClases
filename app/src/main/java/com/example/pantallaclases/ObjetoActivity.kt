package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pantallaclases.databinding.ActivityObjetoBinding
import com.google.gson.Gson


data class Objeto(
    var nombre: String,
    var peso: Int,
    var valor: Int,
    var vida: Int
)

class ObjetoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityObjetoBinding
    lateinit var personaje: Personaje

    private val arrayObj : ArrayList<String> = arrayListOf("Espada", "Escudo", "Arco", "Maza", "Vara")

    private val objeto = Objeto(arrayObj.random(), 5, 10, 20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)
        binding = ActivityObjetoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)

        //Que escoja uno aleatorio y cambie a la foto correspondiente.
        when (objeto.nombre){
            "Espada" -> binding.imageView2.setImageResource(R.drawable.espada)
            "Escudo" -> binding.imageView2.setImageResource(R.drawable.escudo)
            "Arco" -> binding.imageView2.setImageResource(R.drawable.arco)
            "Maza" -> binding.imageView2.setImageResource(R.drawable.maza)
            "Vara" -> binding.imageView2.setImageResource(R.drawable.vara)
        }


        binding.button.setOnClickListener{
            crearIntent(RandomEventActivity::class.java)
        }

        binding.button2.setOnClickListener{
            if (personaje.mochila.tam >= objeto.peso){
                personaje.mochila.tam -= objeto.peso
                personaje.mochila.objetos.add(objeto)
                binding.textView2.visibility = View.VISIBLE
            }

            crearIntent(RandomEventActivity::class.java)
        }
    }
    private fun crearIntent(Class: Class<*>){
        val intent = Intent(this, Class)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        startActivity(intent)
    }
}