package com.example.zorbistores.di

import com.example.zorbistores.data.api.zorbiApiServices
import com.example.zorbistores.data.prefs.PreferenceManager
import com.example.zorbistores.data.repo.*
import com.example.zorbistores.ui.main.cart.adapter.ShoppingCartAdapter
import com.example.zorbistores.ui.main.cart.model.CartItemModel
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule:Module= module {
    single {
        getCategoryRepository(get())
    }

    single {
        getProductRepository(get())
    }

    single {
       registerRepository(get(),get())
    }

    single {
        orderRepository(get(),get())
    }

    single {
        featuredProductRepository(get())
    }

    single {
        latestProductRepository((get()))
    }

    single {
        onSaleProductRepository(get())
    }

    single {
        bannerRepository(get())
    }

    single {
        categoryWiseRepository(get())
    }

    single {
        customerDetailRepository(get())
    }

    single {
        customerOrderRepository(get())
    }

    single {
        shoppingRespository(get())
    }

    single {
        customerRepository(get())
    }

}

fun getCategoryRepository(
    apiServices: zorbiApiServices
):CategoriesRepository{
    return CategoriesRepository(apiServices)
}

fun getProductRepository(
    apiServices: zorbiApiServices
):ProductRepository{
    return ProductRepository(apiServices)
}

fun registerRepository(
    apiServices: zorbiApiServices,
    preferenceManager: PreferenceManager
):RegisterRepository{
    return RegisterRepository(apiServices,preferenceManager)
}

fun orderRepository(
    apiServices: zorbiApiServices,
     preferenceManager: PreferenceManager
):OrderRepository{
    return OrderRepository(apiServices,preferenceManager)
}

fun featuredProductRepository(
    apiServices: zorbiApiServices
):FeaturedProductRepository{
    return FeaturedProductRepository(apiServices)
}


fun latestProductRepository(
    apiServices: zorbiApiServices
):LatestProductRepository{
    return LatestProductRepository(apiServices)
}

fun onSaleProductRepository(
    apiServices: zorbiApiServices
):OnSaleRepository{
    return OnSaleRepository(apiServices)
}

fun bannerRepository(
    apiServices: zorbiApiServices
):BannerRepository{
    return BannerRepository(apiServices)
}

fun categoryWiseRepository(
    apiServices: zorbiApiServices

):CategoriesWiseRepository{
    return CategoriesWiseRepository(apiServices)
}

fun customerDetailRepository(
    apiServices: zorbiApiServices
):CustomerDetailsRepository{
    return CustomerDetailsRepository(apiServices)
}

fun customerOrderRepository(
    apiServices: zorbiApiServices
):CustomerOrderRepository{
    return CustomerOrderRepository(apiServices)
}

fun shoppingRespository(
    apiServices: zorbiApiServices
):ShoppingRepository{
    return ShoppingRepository(apiServices)
}

fun customerRepository(
    apiServices: zorbiApiServices
):CustomerRepository{
    return CustomerRepository(apiServices)
}