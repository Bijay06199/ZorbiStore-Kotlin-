package com.example.zorbistores.data.repo

import com.example.zorbistores.data.api.zorbiApiServices
import com.example.zorbistores.ui.main.cart.model.CartItemModel
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response

class LatestProductRepository (var apiServices: zorbiApiServices){
    suspend fun getProduct(): Response<List<NewProductResponseItem>> {
        return apiServices.getLatestProducts()
    }
}