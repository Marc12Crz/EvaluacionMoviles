package com.marcelo.cristhian.poketinder

import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {
    @GET("api/v2/pokemon?limit=20")
    suspend fun getPokemons(): Response<PokemonListResponse>
}
