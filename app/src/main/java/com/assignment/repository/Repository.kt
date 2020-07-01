package com.assignment.repository

import com.assignment.model.Facts
import com.assignment.network.Api
import com.assignment.network.Response
import javax.inject.Inject

class Repository @Inject constructor(val api:Api) {

    suspend fun getFacts(): Response<Facts?> {
        return try {
            Response.success(api.getFacts())
        }catch (e:Exception){
            e.printStackTrace()
            Response.handleException(e,null)
        }
    }
}