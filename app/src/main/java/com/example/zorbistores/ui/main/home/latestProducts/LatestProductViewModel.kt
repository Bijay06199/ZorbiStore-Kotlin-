package com.example.zorbistores.ui.main.home.latestProducts

import androidx.lifecycle.viewModelScope
import com.example.zorbistores.base.BaseViewModel
import com.example.zorbistores.data.repo.LatestProductRepository
import com.example.zorbistores.ui.main.cart.model.CartItemModel
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import com.example.zorbistores.utils.ApiException
import com.example.zorbistores.utils.NoInternetException
import com.example.zorbistores.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LatestProductViewModel(var latestProductRepository: LatestProductRepository) :BaseViewModel(){

    var latestProduct:List<NewProductResponseItem>?=null
    var latestProductEvent= SingleLiveEvent<Unit>()


    fun getLatestProduct() {
        viewModelScope.launch {
            try {

                val latest = latestProductRepository.getProduct()
                latestProduct = latest.body()
                latestProductEvent.call()

            } catch (e: NoInternetException) {
            }catch (e: ApiException){

            }
        }

    }
}