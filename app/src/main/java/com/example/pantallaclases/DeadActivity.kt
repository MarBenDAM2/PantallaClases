package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantallaclases.databinding.ActivityDeadBinding

class DeadActivity : AppCompatActivity() {
    lateinit var binding : ActivityDeadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dead)
        binding = ActivityDeadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonReiniciar.setOnClickListener {
            aInicio(MainActivity::class.java)
        }
    }
    fun aInicio(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}