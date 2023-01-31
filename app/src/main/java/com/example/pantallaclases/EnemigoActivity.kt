package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantallaclases.databinding.ActivityEnemigoBinding

class EnemigoActivity : AppCompatActivity() {
    lateinit var binding : ActivityEnemigoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemigo)
        binding = ActivityEnemigoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button7.setOnClickListener(){
            clase(FightActivity::class.java)
        }
        binding.button8.setOnClickListener(){
            clase(RandomEventActivity::class.java)
        }
    }

    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        startActivity(intent)
    }
}