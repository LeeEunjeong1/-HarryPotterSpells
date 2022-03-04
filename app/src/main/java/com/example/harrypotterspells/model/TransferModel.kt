package com.example.harrypotterspells.model

import io.reactivex.rxjava3.core.Single

interface TransferModel {
    fun transfer(id: String, secret: String,source: String, target: String, text:String): Single<ResultTransferPapago>
}