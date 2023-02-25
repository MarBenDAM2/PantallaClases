package com.example.pantallaclases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.pantallaclases.databinding.ActivityMercaderBinding
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

class MercaderActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMercaderBinding
    lateinit var personaje: Personaje
    lateinit var tts : TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)
        binding = ActivityMercaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Variable personaje que contendrá todo lo de personaje
        personaje = Gson().fromJson(intent.getStringExtra("Personaje"), Personaje::class.java)

        llamada_tts("Bienvenido, ¿Qué deseas hacer?")

        //Array con los botones de la "etapa 2" del tradeo
        val arrayBotonesE2Comercio : ArrayList<View> = arrayListOf(binding.ComprarButton, binding.VenderButton, binding.CancelButton)


        /*
           Etapas del tradeo:
            1. El jugador elige si "comerciar" o "continuar"

            2. El jugador elige si "comprar" o "vender" [o "cancelar" y volver atrás]
                2.1. El jugador Cancela -> Vuelve al inicio del tradeo

            3. El jugador Compra -> Se "activa" la tercera etapa del tradeo
            4. El jugador Vende -> Se "activa" la cuarta etapa del tradeo

         */

        //Boton "Continuar" -> Vuelve a la pantalla de RandomEvent
        binding.ContinuarButton.setOnClickListener {
            llamada_tts("Vale, hasta luego!")
            if (intent.getIntExtra("Veces", 0) == 1){
                clase(EnterActivity::class.java)
            } else {
                clase(RandomEventActivity::class.java)
            }
        }

        //Boton "Comerciar" -> Etapa 2 del tradeo
        binding.ComerciarButton.setOnClickListener {
            llamada_tts("¿Qué deseas hacer?")
            etapa2Comercio(arrayBotonesE2Comercio)
        }

        //Boton "Comprar" -> Etapa 3 del tradeo
        binding.ComprarButton.setOnClickListener {
            etapa3Comercio(arrayBotonesE2Comercio, personaje)
        }
        //Boton "Vender" -> Etapa 4 del tradeo
        binding.VenderButton.setOnClickListener {
            etapa4Comercio(arrayBotonesE2Comercio, personaje)
        }
    }

    fun etapa2Comercio(arrayBotonesE2Comercio: ArrayList<View>){
        //Array con los botones de la "etapa 1" del tradeo
        val arrayBotonesE1Comercio : ArrayList<View> = arrayListOf(binding.ComerciarButton, binding.ContinuarButton)

        //Ocultamos los botones de la "etapa 1" del tradeo
        goneOrNotUI(arrayBotonesE1Comercio, true)

        //Mostramos los botones de la "etapa 2" del tradeo
        goneOrNotUI(arrayBotonesE2Comercio, false)

        //Si hacemos click en cancelar, volvemos a la "etapa 1" del tradeo (quita todos los botones de la "etapa 2" del tradeo y pone los anteriores)
        binding.CancelButton.setOnClickListener {
            goneOrNotUI(arrayBotonesE2Comercio, true)
            goneOrNotUI(arrayBotonesE1Comercio, false)
        }
    }

    fun etapa3Comercio(arrayBotonesE2Comercio: ArrayList<View>, personaje: Personaje){

        //Ocultamos todo lo de la segunda etapa
        goneOrNotUI(arrayBotonesE2Comercio, true)

        //Cambiamos la imagen del mercader a un objeto
        binding.imageView4.setImageResource(R.drawable.pico)

        //Objeto de precio 125
        val objeto = Objeto("Pico", 5, 125, 20)

        //Array con las vistas de esta etapa
        val arrayVistasE3Comercio : ArrayList<View> = arrayListOf(binding.EditTextEleccionCantidad, binding.TotalAPagarTxt, binding.PrecioTxt, binding.RealizarCompraButton, binding.DineroActualTxt)

        //Mostramos el dinero que tienes
        binding.DineroActualTxt.text = buildString {
            append("Tienes ")
            append(personaje.monedero.dinero)
            append(" monedas")
        }

        llamada_tts("¿Cuántos quieres comprar?")
        //Mostramos las vistas de esta etapa
        goneOrNotUI(arrayVistasE3Comercio, false)

        //Operar con las cantidades
        var precioTotal = 0
        binding.EditTextEleccionCantidad.addTextChangedListener {
            try {
                //Si no está vacio, si es mayor que 0 y si no es nulo o está en blanco
                if (it.toString().isNotEmpty() || it.toString().toInt() > 0 || !it.isNullOrBlank()){

                    //Calculamos el precio total
                    precioTotal = objeto.precio * it.toString().toInt()

                    //Mostramos el precio total
                    binding.PrecioTxt.text = "$precioTotal"
                    
                    //El boton de realizar compra se activará si:
                    // 1. El dinero del personaje es mayor o igual al precio total 
                    // Y A SU VEZ
                    // 2. Si el peso del objeto es menor o igual al peso maximo de la mochila
                    binding.RealizarCompraButton.isEnabled = personaje.monedero.dinero >= precioTotal && personaje.mochila.tam >= objeto.peso
                }
            } catch (e: NumberFormatException) { //catch para que cuando borremos el texto no de error

                //limipiamos el editText
                binding.EditTextEleccionCantidad.text.clear()

                //Precio total a 0.
                binding.PrecioTxt.text = "0"

                //Boton realizar compra desactivado
                binding.RealizarCompraButton.isEnabled = false
            }
        }



        //Realizar compra
        binding.RealizarCompraButton.setOnClickListener {
            llamada_tts("¡Gracias por comprar!")
            //Restamos el dinero
            personaje.monedero.dinero -= precioTotal

            //Añadimos a la mochila
            addToMochila(objeto, personaje)

            //Mostramos el dinero que tienes
            binding.DineroActualTxt.text = buildString {
                append("Tienes ")
                append(personaje.monedero.dinero)
                append(" monedas")
            }

            //Ocultamos las vistas de esta eta-pa (la 3)
            goneOrNotUI(arrayVistasE3Comercio, true)

            //Limpiamos el editText para que no quede fallo si volvemos a comprar el objeto de nuevo.
            binding.EditTextEleccionCantidad.text.clear()

            //Volvemos a la "etapa 2"
            binding.imageView4.setImageResource(R.drawable.mercader)
            etapa2Comercio(arrayBotonesE2Comercio)
        }

    }

    fun etapa4Comercio(arrayBotonesE2Comercio: ArrayList<View>, personaje: Personaje){
        llamada_tts("¿Que objeto quieres venderme?")
        //Cambiar imagen mercader por una mochila
        binding.imageView4.setImageResource(R.drawable.mochila)

        //Cambiamos el texto del boton "Realizar compra" por "Realizar venta"
        binding.RealizarCompraButton.text = buildString {
            append("Realizar venta")
        }

        //Cambio el hint del editText
        binding.EditTextEleccionCantidad.hint = "Nº Objeto a vender"

        //Cambio el texto de Total a pagar por Total a recibir
        binding.TotalAPagarTxt.text = buildString {
            append("Total a recibir:")
        }

        //Prueba de texto
        binding.DineroActualTxt.text = buildString {
            append("Tienes ${personaje.mochila.objetos.size} objeto(s) y ")
            append("${personaje.monedero.dinero} monedas")
        }

        //Ocultamos los botones de la etapa 2
        goneOrNotUI(arrayBotonesE2Comercio, true)

        //Array con las vistas de esta etapa
        val arrayVistasE4Comercio : ArrayList<View> = arrayListOf(binding.EditTextEleccionCantidad, binding.TotalAPagarTxt, binding.PrecioTxt, binding.RealizarCompraButton, binding.DineroActualTxt, binding.NomObj)

        //Mostramos las vistas de esta etapa
        goneOrNotUI(arrayVistasE4Comercio, false)

        //Operar con las cantidades
        binding.EditTextEleccionCantidad.addTextChangedListener {
            try {
                //Si no está vacio, si es mayor que 0 y si no es nulo o está en blanco
                if (it.toString().isNotEmpty() || !it.isNullOrBlank()){

                    //Calculamos el total a recibir
                    val precioTotal = personaje.mochila.objetos[it.toString().toInt()].precio / 2

                    //Mostramos el precio total
                    binding.PrecioTxt.text = "$precioTotal"

                    //El boton de realizar compra se activará si:
                    // 1. El dinero del personaje es mayor o igual al precio total
                    // Y A SU VEZ
                    // 2. Si el peso del objeto es menor o igual al peso maximo de la mochila
                    binding.RealizarCompraButton.isEnabled = personaje.mochila.objetos.size > it.toString().toInt() || personaje.mochila.objetos.size > 0

                    //Diremos que objeto es
                    binding.NomObj.text = personaje.mochila.objetos[it.toString().toInt()].nombre
                }
            } catch (e: NumberFormatException) { //catch para que cuando borremos el texto no de error
                //limipiamos el editText
                binding.EditTextEleccionCantidad.text.clear()
                //Precio total a 0.
                binding.PrecioTxt.text = "0"
                //Boton realizar compra desactivado
                binding.RealizarCompraButton.isEnabled = false
            } catch (e: IndexOutOfBoundsException){
                //limipiamos el editText
                binding.EditTextEleccionCantidad.text.clear()
                //Precio total a 0.
                binding.PrecioTxt.text = "0"
                //Boton realizar compra desactivado
                binding.RealizarCompraButton.isEnabled = false
            }
        }

        //Realizar venta
        binding.RealizarCompraButton.setOnClickListener {
            llamada_tts("Me interesa el objeto, ¡Gracias por venderlo!")
            //Sumamos el dinero
            personaje.monedero.dinero += personaje.mochila.objetos[binding.EditTextEleccionCantidad.text.toString().toInt()].precio / 2

            //Quitamos de la mochila
            personaje.mochila.objetos.removeAt(binding.EditTextEleccionCantidad.text.toString().toInt())

            //Mostramos el dinero que tienes
            binding.DineroActualTxt.text = buildString {
                append("Tienes ")
                append(personaje.monedero.dinero)
                append(" monedas")
            }

            //Ocultamos las vistas de esta etapa (la 4)
            goneOrNotUI(arrayVistasE4Comercio, true)

            //Limpiamos el editText para que no quede fallo si volvemos a comprar el objeto de nuevo.
            binding.EditTextEleccionCantidad.text.clear()

            //Volvemos a la "etapa 2"
            binding.imageView4.setImageResource(R.drawable.mercader)
            etapa2Comercio(arrayBotonesE2Comercio)
        }

    }

    fun addToMochila(objeto: Objeto, personaje: Personaje){

        //Si el peso maximo de la mochila es mayor o igual al peso del objeto
        if (personaje.mochila.tam >= objeto.peso){
            //Añadimos el objeto a la mochila
            personaje.mochila.objetos.add(objeto)
            //Quitamos el peso máximo de la mochila
            personaje.mochila.tam -= objeto.peso
        }

    }

    fun llamada_tts(texto: String){
        tts = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                tts.language = Locale("es", "ES")
                tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null)
            }
        })
    }

    fun goneOrNotUI(arrayViews: ArrayList<View>, gone: Boolean){
        if (gone) {
            for (view in arrayViews) {
                view.visibility = View.GONE
            }
        } else {
            for (view in arrayViews) {
                view.visibility = View.VISIBLE
            }
        }
    }

    private fun clase(Class: Class<*>){
        val intent = Intent(this, Class)
        intent.putExtra("Personaje", Gson().toJson(personaje))
        startActivity(intent)
    }

}