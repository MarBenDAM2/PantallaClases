package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pantallaclases.databinding.ActivityObjetoBinding


data class Objeto(
    var nombre: String,
    var peso: Int,
    var valor: Int,
    var vida: Int
)

class ObjetoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityObjetoBinding

    private val arrayObj : ArrayList<String> = arrayListOf("Espada", "Escudo", "Arco", "Maza", "Vara")

    private val objeto = Objeto(arrayObj.random(), 5, 10, 20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)
        binding = ActivityObjetoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Que escoja uno aleatorio y cambie a la foto correspondiente.
        when (objeto.nombre){
            "Espada" -> binding.imageView2.setImageResource(R.drawable.espada)
            "Escudo" -> binding.imageView2.setImageResource(R.drawable.escudo)
            "Arco" -> binding.imageView2.setImageResource(R.drawable.arco)
            "Maza" -> binding.imageView2.setImageResource(R.drawable.maza)
            "Vara" -> binding.imageView2.setImageResource(R.drawable.vara)
        }




        //hazme un arraylist de los dos botones que tengo
        binding.button.setOnClickListener{
            val intent = Intent(this, RandomEventActivity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener{
            if (ObjetoActivity().objeto.peso <= Resumen().p1.mochila.tam){

                //Añadimos el objeto a la mochila
                Resumen().p1.mochila.objetos.add(ObjetoActivity().objeto)
                //Le quitamos el peso a la mochila
                Resumen().p1.mochila.tam -= ObjetoActivity().objeto.peso
                //Mostramos el texto "Recogido"
                binding.textView2.visibility = View.VISIBLE
                //Volvemos a la aleatoria
                val intent = Intent(this, RandomEventActivity::class.java)
                startActivity(intent)
            }
        }
    }
}