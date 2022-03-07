package com.example.harrypotterspells.model


import io.reactivex.rxjava3.core.Single

class TransferModelImpl(private val service: PapagoService) : TransferModel{
    override fun transfer(source: String, target: String, text:String ): Single<ResultTransferPapago> {
        return service.transferPapago(source, target, text)
    }
}