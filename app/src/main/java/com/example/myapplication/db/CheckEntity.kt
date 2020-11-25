package com.example.myapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "check_entity")
class CheckEntity {
    @PrimaryKey
    @ColumnInfo(name = "request")
    var request: String = ""

    @ColumnInfo(name = "created_at")
    @TypeConverters(DateConverter::class)
    var createdAt: Date = Date()

    @ColumnInfo(name = "ipv4")
    var ipv4: String? = null

    @ColumnInfo(name = "continent_name")
    var continentName: String? = null

    @ColumnInfo(name = "country_name")
    var countryName: String? = null

    @ColumnInfo(name = "subdivision_1_name")
    var subdivision1Name: String? = null

    @ColumnInfo(name = "subdivision_2_name")
    var subdivision2Name: String? = null

    @ColumnInfo(name = "city_name")
    var cityName: String? = null

    @ColumnInfo(name = "latitude")
    var latitude: String? = null

    @ColumnInfo(name = "longitude")
    var longitude: String? = null

    override fun toString(): String {
        return "CheckEntity(request='$request', createdAt=$createdAt, ipv4=$ipv4, continentName=$continentName, countryName=$countryName, subdivision1Name=$subdivision1Name, subdivision2Name=$subdivision2Name, cityName=$cityName, latitude=$latitude, longitude=$longitude)"
    }

}

