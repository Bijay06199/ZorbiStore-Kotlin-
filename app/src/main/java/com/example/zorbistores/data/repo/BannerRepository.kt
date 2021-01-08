package com.example.zorbistores.data.repo

import com.example.zorbistores.data.api.zorbiApiServices
import com.example.zorbistores.data.network.SafeApiRequest
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response

class BannerRepository (var apiServices: zorbiApiServices):SafeApiRequest(){
    suspend fun getBanner(): Response<List<NewProductResponseItem>> {
        return apiServices.getBanner()
    }
}