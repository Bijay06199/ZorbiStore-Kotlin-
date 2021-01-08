package com.example.zorbistores.ui.main.categories

import androidx.lifecycle.viewModelScope
import com.example.zorbistores.base.BaseViewModel
import com.example.zorbistores.data.repo.CategoriesRepository
import com.example.zorbistores.ui.main.categories.response.CategoriesResponse
import com.example.zorbistores.utils.ApiException
import com.example.zorbistores.utils.AuthListenerInfo
import com.example.zorbistores.utils.NoInternetException
import com.example.zorbistores.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CategoriesViewModel(private val getCategoriesRepository: CategoriesRepository) :BaseViewModel(){

    var categoriesEvent=SingleLiveEvent<Unit>()
    var categoryName:List<CategoriesResponse>?=null
    var authListenerInfo: AuthListenerInfo?=null


    fun categories(){
        viewModelScope.launch {
            try {
                val response= getCategoriesRepository.getCategories()
                categoryName=response.body()
                categoriesEvent.call()
            }
            catch (e: NoInternetException){

            }catch (e: ApiException){

            }catch (e:java.lang.NullPointerException){}
        }
    }
}