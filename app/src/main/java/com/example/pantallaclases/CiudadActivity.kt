package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantallaclases.databinding.ActivityCiudadBinding
import com.google.gson.Gson

class CiudadActivity : AppCompatActivity() {
    lateinit var binding : ActivityCiudadBinding
    lateinit var personaje: Personaje
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)
        binding = ActivityCiudadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)


        binding.button3.setOnClickListener(){
            clase(RandomEventActivity::class.java)
        }
        binding.button4.setOnClickListener(){
            clase(EnterActivity::class.java)
        }
    }
    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        startActivity(intent)
    }
}