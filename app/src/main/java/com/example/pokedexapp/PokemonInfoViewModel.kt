package com.example.pokedexapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonInfoViewModel() : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: APIService = retrofit.create(APIService::class.java)

    val pokemonInfo = MutableLiveData<Pokemon>()
    val pokemonDescription = MutableLiveData<Pokemon>()
    val pokemonListByType = MutableLiveData<List<Pokemon>>()

    fun getPokemonInfo(id: Int){
        val call = service.getPokemonInfo(id)

        call.enqueue(object: Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let {pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun getPokemonDescription(id: Int){
        val callDescription = service.getPokemonSpecies(id)
        callDescription.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonDescription.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun getPokemonListByType(type: String){
        val call = service.getPokemonListByType(type)

        call.enqueue(object: Callback<TypeResult>{
            override fun onResponse(call: Call<TypeResult>, response: Response<TypeResult>){
                response.body()?.let { typeResult ->
                    pokemonListByType.postValue(typeResult.pokemonList)
                }
            }

            override fun onFailure(call: Call<TypeResult>, t: Throwable) {
                call.cancel()
            }
        })
    }
}