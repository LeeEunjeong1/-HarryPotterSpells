package com.example.harrypotterspells.model

import io.reactivex.rxjava3.core.Single

interface TransferModel {
    fun transfer(source: String, target: String, text:String): Single<ResultTransferPapago>
}