package com.example.pantallaclases

data class Personaje(var raza: String, var clase: String, var vida: Int, var fuerza: Int, var defensa: Int, var mochila: Mochila, var monedero: Monedero)

data class Mochila(var objetos: ArrayList<Objeto>, var tam: Int)

data class Monedero(var oro: Int, var plata: Int, var cobre: Int)