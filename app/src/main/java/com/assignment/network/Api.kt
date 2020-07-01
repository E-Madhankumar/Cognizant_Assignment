package com.assignment.network

import com.assignment.model.Facts
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {

    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("facts.json")
    suspend fun getFacts(): Facts
}