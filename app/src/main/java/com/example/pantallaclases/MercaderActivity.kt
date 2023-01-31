package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantallaclases.databinding.ActivityMercaderBinding

class MercaderActivity : AppCompatActivity() {
    lateinit var binding : ActivityMercaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)
        binding = ActivityMercaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button5.setOnClickListener(){
            clase(RandomEventActivity::class.java)
        }
        binding.button6.setOnClickListener(){
            clase(CommerceActivity::class.java)
        }
    }
    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        startActivity(intent)
    }
}