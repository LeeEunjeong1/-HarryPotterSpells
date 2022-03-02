package com.example.harrypotterspells.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface SpellService {
    @GET("/spells")
    fun getSpells(): Single<List<SpellResponse>>

}