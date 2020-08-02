package com.richstern.doggos.core.network

import com.richstern.doggos.model.api.ApiResponse
import retrofit2.http.GET

interface DoggosService {

    @GET("breeds/image/random")
    suspend fun getRandomImage(): ApiResponse<String>
}