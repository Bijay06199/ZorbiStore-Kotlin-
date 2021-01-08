package com.example.zorbistores.ui.main.home.onSale

import androidx.lifecycle.viewModelScope
import com.example.zorbistores.base.BaseViewModel
import com.example.zorbistores.data.repo.OnSaleRepository
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import com.example.zorbistores.utils.ApiException
import com.example.zorbistores.utils.NoInternetException
import com.example.zorbistores.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class OnSaleViewModel(private var onSaleRepository: OnSaleRepository) :BaseViewModel(){

    var onSale:List<NewProductResponseItem>?=null
    var onSaleProductEvent= SingleLiveEvent<Unit>()


    fun getOnSaleProduct(){
        viewModelScope.launch {
            try {

                val onsaleProduct = onSaleRepository.getProduct()
                onSale = onsaleProduct.body()
                onSaleProductEvent.call()

            } catch (e: NoInternetException) {
            }catch (e: ApiException){

            }
        }


    }
}