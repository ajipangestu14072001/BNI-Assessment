package aji.dev.bniassessment.data.network

import aji.dev.bniassessment.common.Constant
import aji.dev.bniassessment.domain.model.Promos
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("promos")
    suspend fun getPromos(@Header("Authorization") token: String = Constant.API_KEY): Promos
}