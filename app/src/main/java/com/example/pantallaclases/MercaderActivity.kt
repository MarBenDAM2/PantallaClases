package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.pantallaclases.databinding.ActivityMercaderBinding
import com.google.gson.Gson

class MercaderActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMercaderBinding
    lateinit var personaje: Personaje
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)
        binding = ActivityMercaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)

        val arrayNewButtons = arrayListOf(binding.CancelButton, binding.ComprarButton, binding.VenderButton)

        binding.ContinuarButton.setOnClickListener {
            clase(RandomEventActivity::class.java)
        }

        binding.ComerciarButton.setOnClickListener {
            for (button in arrayNewButtons){
                button.visibility = View.VISIBLE
                button.isEnabled = true
            }
            //Deshabilitamos y ocultamos el resto de botones el boton de comerciar
            binding.ComerciarButton.visibility = View.INVISIBLE
            binding.ComerciarButton.isEnabled = false
            binding.ContinuarButton.visibility = View.INVISIBLE
            binding.ContinuarButton.isEnabled = false
        }

        binding.CancelButton.setOnClickListener{

            //Ocultamos y deshabilitamos los botones de comprar, vender y cancelar
            for (button in arrayNewButtons){
                button.visibility = View.INVISIBLE
                button.isEnabled = false
            }
            //Habilitamos y mostramos el boton de comerciar o continuar
            binding.ComerciarButton.visibility = View.VISIBLE
            binding.ComerciarButton.isEnabled = true
            binding.ContinuarButton.visibility = View.VISIBLE
            binding.ContinuarButton.isEnabled = true

        }

        binding.ComprarButton.setOnClickListener{
            //cambiará la imagen del Mercader por la de un objeto de precio 125
        }

        binding.VenderButton.setOnClickListener{
            //cambiará la imagen del Mercader por la de una mochila y podras seleccionar cuantos quieres vender
            //En caso de no haber se mostrará un mensaje de error
        }
    }
    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        startActivity(intent)
    }
}