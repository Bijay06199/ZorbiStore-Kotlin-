package com.example.zorbistores.di

import com.example.zorbistores.ui.auth.login.EditProfileViewModel
import com.example.zorbistores.ui.main.MainViewModel
import com.example.zorbistores.ui.main.categories.CategoriesViewModel
import com.example.zorbistores.ui.main.contact.ContactViewModel
import com.example.zorbistores.ui.main.home.HomeViewModel
import com.example.zorbistores.ui.auth.login.LoginViewModel
import com.example.zorbistores.ui.auth.login.orders.CustomerOrderViewModel
import com.example.zorbistores.ui.auth.login.orders.orderDetails.OrderDetailsViewModel
import com.example.zorbistores.ui.auth.register.RegisterFinalViewModel
import com.example.zorbistores.ui.auth.register.RegisterNameViewModel
import com.example.zorbistores.ui.auth.register.RegisterPasswordViewModel
import com.example.zorbistores.ui.main.cart.CartViewModel
import com.example.zorbistores.ui.main.cart.checkout.CheckOutViewModel
import com.example.zorbistores.ui.main.categories.categoriesDetail.CategoriesDetailViewModel
import com.example.zorbistores.ui.main.categories.categoriesDetail.CategoriesItemViewModel
import com.example.zorbistores.ui.main.home.featuredProduct.FeaturedProductViewModel
import com.example.zorbistores.ui.main.home.latestProducts.LatestProductViewModel
import com.example.zorbistores.ui.main.home.onSale.OnSaleViewModel
import com.example.zorbistores.ui.main.shop.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule:Module= module {


    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get(),get(),get(),get(),get(),get()) }
    viewModel { ContactViewModel() }
    viewModel { LoginViewModel(get(),get(),get()) }
    viewModel { CategoriesViewModel(get()) }
    viewModel { RegisterNameViewModel(get()) }
    viewModel { RegisterPasswordViewModel(get()) }
    viewModel { RegisterFinalViewModel(get()) }
    viewModel { CategoriesDetailViewModel(get()) }
    viewModel { CategoriesItemViewModel(get()) }
    viewModel { EditProfileViewModel() }
    viewModel { ShopViewModel(get(),get()) }
    viewModel { CartViewModel() }
    viewModel { CheckOutViewModel(get()) }
    viewModel { LatestProductViewModel(get()) }
    viewModel { OnSaleViewModel(get()) }
    viewModel { FeaturedProductViewModel(get()) }
    viewModel { CustomerOrderViewModel(get()) }
    viewModel { OrderDetailsViewModel() }
}