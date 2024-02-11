package com.example.pokedexapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TypeListViewModel : ViewModel(){

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: APIService = retrofit.create(APIService::class.java)

    val pokemonList = MutableLiveData<List<String>>()

    fun getPokemonListByType(type: String){
        val call = service.getPokemonListByType("$type/")
        call.enqueue(object : Callback<TypeResult> {
            override fun onResponse(call: Call<TypeResult>, response: Response<TypeResult>){
                response.body()?.let { typeResult ->
                    pokemonList.postValue(typeResult.pokemonList.map { it.name })
                }
            }

            override fun onFailure(call: Call<TypeResult>, t: Throwable) {
                call.cancel()
            }
        })
    }
}