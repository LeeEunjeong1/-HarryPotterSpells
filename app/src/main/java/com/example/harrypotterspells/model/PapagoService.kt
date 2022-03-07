package com.example.harrypotterspells.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface PapagoService {
    @FormUrlEncoded
    @POST("v1/papago/n2mt")
    fun transferPapago(
        @Field("source") source: String,
        @Field("target") target: String,
        @Field("text") text: String
    ): Single<ResultTransferPapago>
}