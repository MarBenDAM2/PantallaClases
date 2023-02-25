package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import com.example.pantallaclases.databinding.ActivityFightBinding
import com.google.gson.Gson
import java.util.*
import kotlin.random.Random

data class Enemigo (val tipo : String, var vida : Int, var ataque : Int)

class FightActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFightBinding
    lateinit var personaje: Personaje
    lateinit var tts : TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fight)
        binding = ActivityFightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Personaje nuestro
        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)
        //Constante para la vida del personaje inicial (que será la por defecto)
        val VIDAINICIAL_PERS = personaje.vida

        //Aqui preparamos al enemigo en si (y lo que ello conlleva, como la vida, el tipo, la foto, etc)
        val enemigo = enemyCreation()

        //Preparamos la vida del usuario
        binding.VidaUsuario.text = personaje.vida.toString()

        binding.AtacarButton.setOnClickListener{
            llamada_tts("Atacas")
            ataque(enemigo)
        }

        binding.HuirButton.setOnClickListener{
            if (numRandom() in 5..6) {
                clase(RandomEventActivity::class.java)
            }
        }

        //Variable mochila para mejor acceso
        val mochilaJug = personaje.mochila.objetos

        //Si la mochila esta vacia, el boton de objeto estara desactivado
        if (mochilaJug.isEmpty()) {

            binding.ObjetoButton.isEnabled = false

        } else { //Si no, se quedará activo y se podra usar el boton de objeto
            binding.ObjetoButton.setOnClickListener{
                mochilaJug.forEach {
                    if (personaje.vida < VIDAINICIAL_PERS){
                        personaje.vida += it.vida
                    }
                }
            }
        }


    }

    fun ataque(enemigo: Enemigo){
        val random = numRandom()


        //Si la variable random es mayor o igual que 4 y menor o igual que 6
        if (random in 4..6 && enemigo.vida > 0) {

            //El ataque es correcto y quita vida
            bajarVidaEnemigo(enemigo)

            llamada_tts("Has acertado, bien chaval!")

            //Hacemos un delay de 1 segundo para usar bajarVidaPersonaje
            binding.AtacarButton.postDelayed({

                //Le bajamos la vida al personaje
                bajarVidaPersonaje(enemigo)

            }, 1000)


        } else if (random in 1..3 && enemigo.vida > 0) {

            //La visibilidad de "FalloText" será visible durante 1 segundo y despues se volverá invisible
            binding.FalloText.visibility = View.VISIBLE

            llamada_tts("Has fallado, ¿Un poco malo no?")

            //Retrasamos 1 segundo ocultar el texto de fallo
            binding.FalloText.postDelayed({

                //Ocultamos el texto de fallo
                binding.FalloText.visibility = View.GONE

            }, 1000)


            //Le bajamos la vida al personaje
            bajarVidaPersonaje(enemigo)
        }

        if (enemigo.vida < 0){
            enemigo.vida = 0
        }
        if (personaje.vida < 0){
            personaje.vida = 0
        }

        //Actualizamos la vida del enemigo
        binding.VidaActual.text = enemigo.vida.toString()
        //Actualizamos la vida del personaje
        binding.VidaUsuario.text = personaje.vida.toString()

        //Comprobacion de vida.
        compVida(personaje, enemigo)

    }

    fun compVida(personaje: Personaje, enemigo: Enemigo){

        if (personaje.vida == 0){

            clase(DeadActivity::class.java)

        }

        if (enemigo.vida == 0){

            llamada_tts("Has matado al enemigo, bien hecho! De recompensa te daremos 100 monedas y 3 curas")

            val objeto_regalo = Objeto("Cura", 5, 20, 50)

            repeat(3){
                addToMochila(personaje, objeto_regalo)
            }

            personaje.monedero.dinero += 100

            Log.v("Numero_intento", intent.getIntExtra("Veces", 0).toString())

            //Si el putExtra de Veces es mayor que 0, hago el finish pero como hay un bucle en el otro lado del codigo, se seguirá
            if (intent.getIntExtra("Veces", 0) > 0){
                finish()
            } else {
                //El else se hará cuando:
                /*
                    * 1. El putExtra de Veces sea 0, es decir que no queda ningun enemigo por matar en la ciudad
                    * 2. Cuando te encuentres un bicho desde fuera de la ciudad, "como si estuviera por la calle"
                 */
                clase(RandomEventActivity::class.java)
            }
        }

    }

    fun addToMochila (personaje: Personaje, objeto: Objeto){
        if (personaje.mochila.tam >= objeto.peso){

            //Reduce el peso de la mochila
            personaje.mochila.tam -= objeto.peso
            //Lo añade a la mochila
            personaje.mochila.objetos.add(objeto)

        }
    }

    fun numRandom(): Int {
        return Random.nextInt(1, 6)
    }

    fun bajarVidaEnemigo(enemigo: Enemigo){
        enemigo.vida -= personaje.fuerza
    }

    fun bajarVidaPersonaje(enemigo: Enemigo){
        personaje.vida -= (enemigo.ataque / personaje.defensa)
    }

    fun enemyCreation(): Enemigo {
        val listaTipo = listOf("Jefe", "Normal")

        val tipoEnemigo = listaTipo.random()

        var vidaEnemigo = 0
        var ataqueEnemigo = 0

        //Si es jefe, vida a 200 y la foto a jefe.
        //Si es normal, vida a 100 y la foto a enemigo.
        if (tipoEnemigo == "Jefe" ){
            vidaEnemigo = 200
            binding.EnemigoFoto.setImageResource(R.mipmap.jefe)
            ataqueEnemigo = 30
        } else if (tipoEnemigo == "Normal"){
            vidaEnemigo = 100
            binding.EnemigoFoto.setImageResource(R.drawable.enemigo)
            ataqueEnemigo = 20
        }

        //Cambiamos los textos de vida
        binding.VidaActual.text = vidaEnemigo.toString()

        return Enemigo(tipoEnemigo, vidaEnemigo, ataqueEnemigo)
    }

    fun llamada_tts(texto: String){
        tts = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR){
                tts.language = Locale("es", "ES")
                tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null)
            }
        })
    }

    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        startActivity(intent)
    }

}