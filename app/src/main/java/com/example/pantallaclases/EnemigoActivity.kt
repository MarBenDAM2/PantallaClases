package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantallaclases.databinding.ActivityEnemigoBinding
import com.google.gson.Gson

class EnemigoActivity : AppCompatActivity() {
    lateinit var binding : ActivityEnemigoBinding

    lateinit var personaje: Personaje

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemigo)
        binding = ActivityEnemigoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)

        binding.button7.setOnClickListener{
            clase(FightActivity::class.java)
        }
        binding.button8.setOnClickListener{
            clase(RandomEventActivity::class.java)
        }

    }

    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        startActivity(intent)
    }
}