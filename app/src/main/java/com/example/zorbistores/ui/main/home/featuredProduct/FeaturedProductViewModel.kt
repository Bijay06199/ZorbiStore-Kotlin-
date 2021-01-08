package com.example.zorbistores.ui.main.home.featuredProduct

import androidx.lifecycle.viewModelScope
import com.example.zorbistores.base.BaseViewModel
import com.example.zorbistores.data.repo.FeaturedProductRepository
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import com.example.zorbistores.utils.ApiException
import com.example.zorbistores.utils.NoInternetException
import com.example.zorbistores.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class FeaturedProductViewModel(private var featuredProductRepository: FeaturedProductRepository) :BaseViewModel(){

    var featuredProductEvent = SingleLiveEvent<Unit>()
    var productName: List<NewProductResponseItem>? = null


    fun getFeaturedProduct() {

        viewModelScope.launch {
            try {

                val featuredProduct = featuredProductRepository.getProduct()
                productName = featuredProduct.body()
                featuredProductEvent.call()

            }  catch (e: NoInternetException) {
            }catch (e: ApiException){

            }
        }
    }
}