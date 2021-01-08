package com.example.zorbistores.data.repo

import com.example.zorbistores.data.api.zorbiApiServices
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response

class ShoppingRepository (var apiServices: zorbiApiServices){
    suspend fun getProduct(page:Int,per_page:Int): Response<List<NewProductResponseItem>> {
        return apiServices.getProductPage(page,per_page)
    }
}