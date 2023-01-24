package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pantallaclases.databinding.ActivityMainBinding
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
        binding.button.setOnClickListener(){
            aInicio(MainActivity::class.java)
        }
        //Boton a siguiente
        binding.button2.setOnClickListener(){
            crearActividad(SigActivity::class.java)
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