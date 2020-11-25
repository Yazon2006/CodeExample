package com.example.myapplication.rest

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val SERVICE_HOST = "ipvigilante.com"

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Provides
    fun providesGithubApi(): IPVigilanteService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .hostnameVerifier { hostname, session ->
                return@hostnameVerifier hostname == SERVICE_HOST
            }
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://$SERVICE_HOST/")
            .build()
        return retrofit.create(IPVigilanteService::class.java)
    }
}