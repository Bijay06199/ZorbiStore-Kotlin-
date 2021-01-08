package com.example.zorbistores.data.repo

import com.example.zorbistores.data.api.zorbiApiServices
import com.example.zorbistores.ui.auth.login.response.CustomerOrderResponse
import retrofit2.Response

class CustomerOrderRepository (var apiServices: zorbiApiServices){
    suspend fun getCustomerOrder(id: Int?): Response<List<CustomerOrderResponse>> {
        return apiServices.getCustomersOrder(id)
    }
}