package com.example.zorbistores.data.repo

import com.example.zorbistores.data.api.zorbiApiServices
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response

class CategoriesWiseRepository (var apiServices: zorbiApiServices){
    suspend fun getCategoriesWise(id: Int?): Response<List<NewProductResponseItem>> {
        return apiServices.getProductCategoryWise(id,100)
    }
}