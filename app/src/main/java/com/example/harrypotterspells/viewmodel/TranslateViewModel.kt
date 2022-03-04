package com.example.harrypotterspells.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypotterspells.model.DataModel
import com.example.harrypotterspells.model.ResultTransferPapago
import com.example.harrypotterspells.model.SpellResponse
import com.example.harrypotterspells.model.TransferModel
import io.reactivex.rxjava3.schedulers.Schedulers

class TranslateViewModel(private  val model: TransferModel) : ViewModel() {

    private val _translateData = MutableLiveData<String>()

    val translateData: LiveData<String>
        get() = _translateData

    fun translate(source:String, target: String, text: String) {
        //source -> target(결과)
        model.transfer("NkPZFF4nYHnmzDWyBP9e","kIL3gjxskk",source,target,text)
            .subscribeOn(Schedulers.io())
            .subscribe({
                it.run {
                    Log.d( "TranslateViewModel","it : $it")
                    _translateData.postValue(it.message.result.translatedText)
                }
            }, {
                Log.d("TranslateViewModel", "response error, message : ${it.message}")
            })
    }
}