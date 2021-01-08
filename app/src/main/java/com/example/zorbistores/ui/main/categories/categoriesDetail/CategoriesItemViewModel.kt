package com.example.zorbistores.ui.main.categories.categoriesDetail

import androidx.lifecycle.viewModelScope
import com.example.zorbistores.base.BaseViewModel
import com.example.zorbistores.data.repo.CategoriesWiseRepository
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import com.example.zorbistores.utils.ApiException
import com.example.zorbistores.utils.NoInternetException
import com.example.zorbistores.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CategoriesItemViewModel( val categoriesWiseRepository: CategoriesWiseRepository) :BaseViewModel(){

    var categoryWise:List<NewProductResponseItem>?=null
    var categoryWiseEvent=SingleLiveEvent<Unit>()


    fun getCategoryWise( id:Int?) {

        viewModelScope.launch {
            try {
                val response = categoriesWiseRepository.getCategoriesWise(id)
                categoryWise = response.body()!!
                categoryWiseEvent.call()
            } catch (e: NoInternetException){

            }catch (e: ApiException){

            }catch (e:java.lang.NullPointerException){}
        }
    }

}