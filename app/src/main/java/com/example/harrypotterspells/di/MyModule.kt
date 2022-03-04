package com.example.harrypotterspells.di

import com.example.harrypotterspells.model.*
import com.example.harrypotterspells.ui.MainAdapter
import com.example.harrypotterspells.viewmodel.MainViewModel
import com.example.harrypotterspells.viewmodel.TranslateViewModel
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/* 코인 모듈 생성 */

//로그 남기기
val httpLoggingInterceptor = HttpLoggingInterceptor()

//해리포터api
var retrofitPart = module {
    single<SpellService> {
        Retrofit.Builder()
            .baseUrl("https://fedeperin-harry-potter-api-en.herokuapp.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpellService::class.java)
    }
}

//네이버 api
var retrofitNaverPart = module{
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
    single<PapagoService>{
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PapagoService::class.java)
    }
    single<OkHttpClient>(named("retrofitNaverPart"))  {
        OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val builder = originalRequest.newBuilder()
                    .header("X-Naver-Client-Id","NkPZFF4nYHnmzDWyBP9e")
                    .header("X-Naver-Client-Secret","kIL3gjxskk")
                val newRequest = builder.build()
                chain.proceed(newRequest)
            }
            .build()
    }
}

//생성자에 의해 의존성 주입받는 형태
var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
    factory<TransferModel>{
        TransferModelImpl(get())
    }
}

//viewModel 키워드로 선언
var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        TranslateViewModel(get())
    }
}

var adapterPart = module {
    factory {
        MainAdapter()
    }
}
/* 모듈 하나로 묶기 -> 쉽고 편하게 모듈 호출 가능(startKoin) */
var myDiModule = listOf(viewModelPart, modelPart, retrofitPart, adapterPart, retrofitNaverPart)
