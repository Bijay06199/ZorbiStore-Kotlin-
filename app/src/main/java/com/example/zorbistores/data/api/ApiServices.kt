package com.example.zorbistores.data.api

import com.example.zorbistores.ui.auth.login.response.CustomerOrderResponse
import com.example.zorbistores.ui.auth.login.response.CustomerResponse
import com.example.zorbistores.ui.auth.register.body.RegisterBody
import com.example.zorbistores.ui.auth.register.response.RegisterResponse
import com.example.zorbistores.ui.main.cart.checkout.body.OrderBody
import com.example.zorbistores.ui.main.cart.checkout.response.OrderResponse
import com.example.zorbistores.ui.main.cart.model.CartItemModel
import com.example.zorbistores.ui.main.categories.response.CategoriesResponse
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response
import retrofit2.http.*

interface zorbiApiServices{

    var categoryId:Int

    @GET("/wp-json/wc/v3/products/categories?per_page=50")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun getCategories(): Response<List<CategoriesResponse>>

    @GET("wp-json/wc/v3/products?per_page=100")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun getProduct():Response<List<NewProductResponseItem>>

    @POST("/wp-json/wc/v3/customers")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun register(
        @Body params:RegisterBody
    ):Response<RegisterResponse>

    @POST("/wp-json/wc/v3/orders")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun order(
        @Body params: OrderBody
    ):Response<OrderResponse>

    @GET("/wp-json/wc/v3/products?category=45")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun getFeaturedProduct():Response<List<NewProductResponseItem>>

    @GET("/wp-json/wc/v3/products?orderby=date")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
     suspend fun getLatestProducts():Response<List<NewProductResponseItem>>

    @GET("/wp-json/wc/v3/products?on_sale=true")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun getOnSaleProducts():Response<List<NewProductResponseItem>>

    @GET("/wp-json/wc/v3/products?category=88")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
     suspend fun getBanner():Response<List<NewProductResponseItem>>

    @GET("/wp-json/wc/v3/products")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
     suspend fun getProductCategoryWise(
        @Query ("category")category:Int?,
        @Query("per_page")per_page: Int?
    ):Response<List<NewProductResponseItem>>

    @GET("wp-json/wc/v3/customers")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun getCustomerDetails(
        @Query("email")email:String?
    ):Response<List<CustomerResponse>>

    @GET("/wp-json/wc/v2/orders")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun getCustomersOrder(
        @Query("customer")customer:Int?
    ):Response<List<CustomerOrderResponse>>


    @GET("wp-json/wc/v3/products")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun getProductPage(
        @Query("page")page:Int?,
        @Query("per_page")per_page:Int?
    ):Response<List<NewProductResponseItem>>


    @GET("wp-json/wc/v3/customers")
    @Headers("Authorization:Basic Y2tfZGIwNjI0MjE2YmE2Y2I0MDJmMjM5NWU2MDgyMWI1ZTk3MjQ3MDUxNjpjc18yZDJlZjU0OTUwNDJjNGQ3ZDc2Y2Q4MDFjNzk4YWM5OGI4MDZlMzNj")
    suspend fun getCustomer():Response<List<CustomerResponse>>


}