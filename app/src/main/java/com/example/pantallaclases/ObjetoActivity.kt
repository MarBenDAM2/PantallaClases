package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantallaclases.databinding.ActivityObjetoBinding

class ObjetoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityObjetoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)
        binding = ActivityObjetoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hazme un arraylist de los dos botones que tengo
        binding.button.setOnClickListener{
            clase(RandomEventActivity::class.java)
        }

        binding.button2.setOnClickListener{
            clase(ObjetoActivity::class.java)
        }
    }
    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        startActivity(intent)
    }
}