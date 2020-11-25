package com.example.myapplication.rest

import com.google.gson.annotations.SerializedName

data class IpCheckResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("data")
    val data: DataResponse?
)

data class DataResponse(
    @SerializedName("ipv4")
    val ipv4: String?,
    @SerializedName("continent_name")
    val continentName: String?,
    @SerializedName("country_name")
    val countryName: String?,
    @SerializedName("subdivision_1_name")
    val subdivision1Name: String?,
    @SerializedName("subdivision_2_name")
    val subdivision2Name: String?,
    @SerializedName("city_name")
    val cityName: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String
)