package com.example.zorbistores.data.repo

import com.example.zorbistores.data.api.zorbiApiServices
import com.example.zorbistores.ui.auth.login.response.CustomerResponse
import retrofit2.Response

class CustomerRepository (var apiServices: zorbiApiServices){
    suspend fun getCustomer(): Response<List<CustomerResponse>> {
        return apiServices.getCustomer()
    }
}