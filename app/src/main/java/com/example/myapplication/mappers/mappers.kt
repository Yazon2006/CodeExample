package com.example.myapplication.mappers

import com.example.myapplication.ResultUIModel
import com.example.myapplication.db.CheckEntity
import com.example.myapplication.rest.IpCheckResponse

fun IpCheckResponse.toCheckEntity(request: String): CheckEntity {
    val checkEntity = CheckEntity()
    checkEntity.request = request
    checkEntity.ipv4 = this.data?.ipv4
    checkEntity.continentName = this.data?.continentName
    checkEntity.countryName = this.data?.countryName
    checkEntity.subdivision1Name = this.data?.subdivision1Name
    checkEntity.subdivision2Name = this.data?.subdivision2Name
    checkEntity.cityName = this.data?.cityName
    checkEntity.latitude = this.data?.latitude
    checkEntity.longitude = this.data?.longitude
    return checkEntity
}

fun CheckEntity.toResultUIModel(): ResultUIModel {
    return ResultUIModel(
        request = this.request,
        info = this.toString()
    )
}