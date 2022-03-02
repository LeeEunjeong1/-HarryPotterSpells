package com.example.harrypotterspells.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypotterspells.model.DataModel
import com.example.harrypotterspells.model.SpellResponse
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private  val model: DataModel) : ViewModel() {

    private val _getSpellResponseLiveData = MutableLiveData<List<SpellResponse>>()

    val getSpellResponseLiveData: LiveData<List<SpellResponse>>
        get() = _getSpellResponseLiveData

    fun getSpells() {
        model.getData()
            .subscribeOn(Schedulers.io())
            .subscribe({
                it.run {
                    Log.d( "MainViewModel","spells : $it")
                    _getSpellResponseLiveData.postValue(it)
                }
            }, {
                Log.d("MainViewModel", "response error, message : ${it.message}")
            })
    }
}