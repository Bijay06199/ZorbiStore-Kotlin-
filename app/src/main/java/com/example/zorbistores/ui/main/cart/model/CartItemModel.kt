package com.example.zorbistores.ui.main.cart.model

import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem

data class CartItemModel(var product: NewProductResponseItem, var quantity:Int=0)