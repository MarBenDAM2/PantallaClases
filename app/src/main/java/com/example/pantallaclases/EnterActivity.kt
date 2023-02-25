package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantallaclases.databinding.ActivityEnterBinding
import com.google.gson.Gson

class EnterActivity : AppCompatActivity() {
    lateinit var binding : ActivityEnterBinding
    lateinit var personaje: Personaje
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)
        binding = ActivityEnterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)

        val numRandom = (1..3).random()

        val VECES_ENFRENTAMIENTO = 5

        //Cuando el numero aleatorio sea:
        // 1. Objeto 2. Mercader 3. Enfrentamiento
        when (numRandom) {
            1 -> {
                aInicio(ObjetoActivity::class.java, 1)
            }
            2 -> {
                aInicio(MercaderActivity::class.java, 1)
            }
            3 -> {
                repeat(VECES_ENFRENTAMIENTO){
                    aInicio(FightActivity::class.java, it)
                }
            }
        }
    }

    fun aInicio(clase: Class<*>, num_veces: Int?) {
        val intent = Intent(this, clase)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        //Esto lo utilizo para que "compruebe" si ha pasado por la ciudad o no
        //Podria haber usado un booleano, no lo he hecho.
        intent.putExtra("Veces", num_veces)
        startActivity(intent)
    }
}