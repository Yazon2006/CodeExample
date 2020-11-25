package com.example.myapplication.rest

import retrofit2.http.GET
import retrofit2.http.Path

interface IPVigilanteService {
    @GET("json/{ip}/default")
    suspend fun getInfo(@Path("ip") ip: String): IpCheckResponse
}

