package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantallaclases.databinding.ActivityFightBinding
import com.google.gson.Gson
import kotlin.random.Random

data class Enemigo (val tipo : String, var vida : Int)

class FightActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFightBinding
    lateinit var personaje: Personaje
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fight)
        binding = ActivityFightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var enemigo = enemyCreation()

        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)

        binding.AtacarButton.setOnClickListener{
            if (enemigo.vida >= 0){
                enemigo.vida -= personaje.fuerza
            } else {
                clase(RandomEventActivity::class.java)
            }
        }

        binding.HuirButton.setOnClickListener{
            clase(RandomEventActivity::class.java)
        }

        binding.ObjetoButton.setOnClickListener{
            clase(RandomEventActivity::class.java)
        }
    }
    fun enemyCreation(): Enemigo {
        val listaTipo = listOf("Jefe", "Normal")

        val tipoEnemigo = listaTipo.random()

        var vidaEnemigo = 0

        vidaEnemigo = when (tipoEnemigo) {
            "Jefe" -> {
                200
            }
            "Normal" -> {
                100
            }
            else -> {
                0
            }
        }

        return Enemigo(listaTipo.random(), vidaEnemigo)
    }

    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        startActivity(intent)
    }
}