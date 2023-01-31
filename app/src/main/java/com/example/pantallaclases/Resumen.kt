package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.pantallaclases.databinding.ActivityResumenBinding
import kotlin.random.Random

class Resumen : AppCompatActivity() {
    private lateinit var binding: ActivityResumenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)
        binding = ActivityResumenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Numeros aleatorios para fuerza y defensa
        binding.Dato1.text = Random.nextInt(10, 15).toString()
        binding.Dato2.text = Random.nextInt(1, 5).toString()

        //Boton a inicio
        binding.buttonVuelta.setOnClickListener(){
            aInicio(MainActivity::class.java)
        }
        //Boton a siguiente
        binding.buttonComenzar.setOnClickListener(){
            crearActividad(SigActivity::class.java)
        }

        when {
            intent.getStringExtra("clase") == "berserker" -> {
                binding.imgClase.setBackgroundResource(R.drawable.berserker)
                binding.imgClase.visibility = View.VISIBLE
            }
            intent.getStringExtra("clase") == "ladron" -> {
                binding.imgClase.setBackgroundResource(R.drawable.ladron)
                binding.imgClase.visibility = View.VISIBLE
            }
            intent.getStringExtra("clase") == "mago" -> {
                binding.imgClase.setBackgroundResource(R.drawable.mago)
                binding.imgClase.visibility = View.VISIBLE
            }
            intent.getStringExtra("clase") == "guerrero" -> {
                binding.imgClase.setBackgroundResource(R.drawable.berserker)
                binding.imgClase.visibility = View.VISIBLE
            }
        }

        when {
            intent.getStringExtra("raza") == "elfo" -> {
                binding.imgRaza.setBackgroundResource(R.drawable.elfo)
                binding.imgRaza.visibility = View.VISIBLE
            }
            intent.getStringExtra("raza") == "enano" -> {
                binding.imgRaza.setBackgroundResource(R.drawable.enano)
                binding.imgRaza.visibility = View.VISIBLE
            }
            intent.getStringExtra("raza") == "humano" -> {
                binding.imgRaza.setBackgroundResource(R.drawable.humano)
                binding.imgRaza.visibility = View.VISIBLE
            }
            intent.getStringExtra("raza") == "goblin" -> {
                binding.imgRaza.setBackgroundResource(R.drawable.goblin)
                binding.imgRaza.visibility = View.VISIBLE
            }
        }

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

    private fun crearActividad(clase: Class<SigActivity>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}