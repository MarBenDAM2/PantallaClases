package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.pantallaclases.databinding.ActivityResumenBinding
import com.google.gson.Gson
import kotlin.random.Random

class Resumen : AppCompatActivity() {

    var p1 : Personaje = Personaje(
        Raza().raza,
        MainActivity().nom_clase,
        10000,
        Random.nextInt(10, 15),
        Random.nextInt(1,5),
        Mochila(arrayListOf(), 100),
        Monedero(150)
    )
    private lateinit var binding: ActivityResumenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)
        binding = ActivityResumenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Numeros aleatorios para fuerza y defensa
        binding.Dato1.text = p1.fuerza.toString()
        binding.Dato2.text = p1.defensa.toString()

        //Numeros para la vida y mochila respectivamente.
        binding.Dato3.text = p1.vida.toString()
        binding.Dato4.text = p1.mochila.tam.toString()

        //Texto de monedero
        binding.Dato5.text = p1.monedero.dinero.toString()

        //Boton a inicio
        binding.buttonVuelta.setOnClickListener {
            aInicio(MainActivity::class.java)
        }
        //Boton a siguiente
        binding.buttonComenzar.setOnClickListener {
            crearActividad(RandomEventActivity::class.java)
        }

        //Cuando sea una clase u otra entonces pues cambia la imagen y la hace visible
        when (intent.getStringExtra("clase")) {
            "berserker" -> {
                binding.imgClase.setImageResource(R.mipmap.berserker)
                binding.imgClase.visibility = View.VISIBLE
            }
            "ladron" -> {
                binding.imgClase.setImageResource(R.mipmap.ladron)
                binding.imgClase.visibility = View.VISIBLE
            }
            "mago" -> {
                binding.imgClase.setImageResource(R.mipmap.mago)
                binding.imgClase.visibility = View.VISIBLE
            }
            "guerrero" -> {
                binding.imgClase.setImageResource(R.mipmap.berserker)
                binding.imgClase.visibility = View.VISIBLE
            }
        }

        //Cuando la raza sea la que sea pues cambia la imagen y la hace visible
        when (intent.getStringExtra("raza")) {
            "elfo" -> {
                binding.imgRaza.setImageResource(R.drawable.elfo)
                binding.imgRaza.visibility = View.VISIBLE
            }
            "enano" -> {
                binding.imgRaza.setImageResource(R.drawable.enano)
                binding.imgRaza.visibility = View.VISIBLE
            }
            "humano" -> {
                binding.imgRaza.setImageResource(R.drawable.humano)
                binding.imgRaza.visibility = View.VISIBLE
            }
            "goblin" -> {
                binding.imgRaza.setImageResource(R.drawable.goblin)
                binding.imgRaza.visibility = View.VISIBLE
            }
        }

        //Cuando el nombre no este vacio entonces el boton de comenzar se activa y se hace visible
        binding.editTextTextPersonName.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                binding.buttonComenzar.isEnabled = true
                binding.buttonComenzar.visibility = View.VISIBLE
            } else {
                binding.buttonComenzar.isEnabled = false
                binding.buttonComenzar.visibility = View.INVISIBLE
            }
        }

    }

    private fun aInicio(clase: Class<MainActivity>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    private fun crearActividad(clase: Class<RandomEventActivity>) {
        val intent = Intent(this, clase)
        intent.putExtra("Personaje", Gson().toJson(p1))
        startActivity(intent)
    }
}