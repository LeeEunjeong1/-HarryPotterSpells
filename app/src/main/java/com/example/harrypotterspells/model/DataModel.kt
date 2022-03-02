package com.example.harrypotterspells.model

import io.reactivex.rxjava3.core.Single

interface DataModel {
    fun getData(): Single<List<SpellResponse>>
}