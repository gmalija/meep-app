package com.gmalija.meepapp.di

import com.gmalija.meepapp.BuildConfig
import com.gmalija.meepapp.data.MeepApi
import com.gmalija.meepapp.data.MeepRepository
import com.gmalija.meepapp.data.MeepRepositoryImpl
import com.gmalija.meepapp.presentation.map.ClusterMapItem
import com.gmalija.meepapp.presentation.map.MapViewModel
import com.gmalija.meepapp.usecase.GetPinsUseCase
import com.gmalija.meepapp.util.ClusterRenderer
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val clusterModule = module {
    factory { (map: GoogleMap) -> ClusterManager<ClusterMapItem>(get(), map) }
    factory { (map: GoogleMap, clusterManager: ClusterManager<ClusterMapItem>) -> ClusterRenderer(context = get(), map = map, clusterManager = clusterManager) }
}

val retrofitModule = module {
    single {
        createWebService<MeepApi>(
            okHttpClient = createHttpClient(),
            baseUrl = BuildConfig.MEEP_URL
        )
    }
}

val repositoryModule = module {
    factory<MeepRepository> { MeepRepositoryImpl(meepApi = get()) }
}

val useCaseModule = module {
    single { GetPinsUseCase(meepRepository = get()) }
}

val viewModelModule = module {
    viewModel { MapViewModel(getPinsUseCase = get()) }
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

fun createHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
}