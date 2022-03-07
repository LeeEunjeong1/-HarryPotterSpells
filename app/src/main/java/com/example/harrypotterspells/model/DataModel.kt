package com.example.harrypotterspells.model

import com.example.harrypotterspells.model.response.SpellResponse
import io.reactivex.rxjava3.core.Single

interface DataModel {
    fun getData(): Single<List<SpellResponse>>

}