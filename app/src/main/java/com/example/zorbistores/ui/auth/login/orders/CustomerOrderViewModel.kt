package com.example.zorbistores.ui.auth.login.orders

import androidx.lifecycle.viewModelScope
import com.example.zorbistores.base.BaseViewModel
import com.example.zorbistores.data.repo.CustomerOrderRepository
import com.example.zorbistores.ui.auth.login.response.CustomerOrderResponse
import com.example.zorbistores.utils.ApiException
import com.example.zorbistores.utils.NoInternetException
import com.example.zorbistores.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CustomerOrderViewModel(var customerOrderRepository: CustomerOrderRepository) :BaseViewModel(){

    var orderEvent=SingleLiveEvent<Unit>()
    var orders:List<CustomerOrderResponse>?=null

    fun getOrders(id:Int?){

        viewModelScope.launch {
            try {
                val response = customerOrderRepository.getCustomerOrder(id)
                orders = response.body()!!
                orderEvent.call()
            } catch (e: NoInternetException){

            }catch (e: ApiException){

            }
        }


    }


}