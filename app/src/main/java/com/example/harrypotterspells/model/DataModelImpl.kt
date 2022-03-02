package com.example.harrypotterspells.model

import io.reactivex.rxjava3.core.Single

class DataModelImpl(private val service: SpellService) : DataModel{
    override fun getData(): Single<List<SpellResponse>> {
        return service.getSpells()
    }
}