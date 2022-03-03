package com.example.harrypotterspells.di

import com.example.harrypotterspells.model.DataModel
import com.example.harrypotterspells.model.DataModelImpl
import com.example.harrypotterspells.model.SpellService
import com.example.harrypotterspells.ui.MainAdapter
import com.example.harrypotterspells.viewmodel.MainViewModel
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/* 코인 모듈 생성 */
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

//생성자에 의해 의존성 주입받는 형태
var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

//viewModel 키워드로 선언
var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
}

var adapterPart = module {
    factory {
        MainAdapter()
    }
}
/* 모듈 하나로 묶기 -> 쉽고 편하게 모듈 호출 가능(startKoin) */
var myDiModule = listOf(viewModelPart, modelPart, retrofitPart, adapterPart)
