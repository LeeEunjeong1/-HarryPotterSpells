package com.example.harrypotterspells.ui.info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypotterspells.model.TransferModel
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class TranslateViewModel(private  val model: TransferModel) : ViewModel() {

    private val _translateData = MutableLiveData<String>()

    val translateData: LiveData<String>
        get() = _translateData

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String> get() = _isError

    fun translate(source:String, target: String, text: String) {
        try{
            //source -> target(결과)
            model.transfer(source,target,text)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.run {
                        Log.d( "TranslateViewModel","it : $it")
                        _translateData.postValue(it.message.result.translatedText)
                    }
                }, {
                    _isError.postValue(it.message)
                    Log.d("TranslateViewModel", "response error, message : ${it.message}")
                })
        }catch (e:Exception){
            _isError.postValue(e.message)
        }

    }
}