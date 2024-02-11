package com.example.pokedexapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>
    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call <PokeApiResponse>
    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: Int): Call<Pokemon>
    @GET("type/{type}")
    fun getPokemonListByType(@Path("type") type: String): Call<TypeResult>
}