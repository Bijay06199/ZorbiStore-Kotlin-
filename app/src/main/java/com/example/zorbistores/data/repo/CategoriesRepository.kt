package com.example.zorbistores.data.repo

import com.example.zorbistores.data.api.zorbiApiServices
import com.example.zorbistores.ui.main.categories.response.CategoriesResponse
import retrofit2.Response

class CategoriesRepository (var apiServices: zorbiApiServices){
    suspend fun getCategories(): Response<List<CategoriesResponse>> {
        return apiServices.getCategories()
    }
}