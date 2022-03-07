package com.example.harrypotterspells.model

import com.example.harrypotterspells.model.response.SpellResponse
import com.example.harrypotterspells.model.service.SpellService
import io.reactivex.rxjava3.core.Single

class DataModelImpl(private val service: SpellService) : DataModel{
    override fun getData(): Single<List<SpellResponse>> {
        return service.getSpells()
    }
}