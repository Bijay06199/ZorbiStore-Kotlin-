package com.example.zorbistores.data.repo

import com.example.zorbistores.data.api.zorbiApiServices
import com.example.zorbistores.data.network.SafeApiRequest
import com.example.zorbistores.data.prefs.PreferenceManager
import com.example.zorbistores.ui.main.cart.ShoppingCart
import com.example.zorbistores.ui.main.cart.checkout.body.Billing
import com.example.zorbistores.ui.main.cart.checkout.body.LineItem
import com.example.zorbistores.ui.main.cart.checkout.body.OrderBody
import com.example.zorbistores.ui.main.cart.checkout.body.Shipping
import com.example.zorbistores.ui.main.cart.checkout.response.OrderResponse
import com.example.zorbistores.ui.main.cart.model.CartItemModel
import com.google.gson.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class OrderRepository(var apiServices: zorbiApiServices, var preferenceManager: PreferenceManager) :
    SafeApiRequest() {





    suspend fun order(
        firstName: String,
        lastName: String,
        contactNumber: String,
        email: String,
        address1: String,
        address2: String
    ): Response<OrderResponse> {
        val requestData = OrderBody(
            preferenceManager.getCustomerId()!!,
            Billing(
                address1,
                address2,
                null,
                "",
                email,
                firstName,
                lastName,
                contactNumber,
                "",
                ""
            ),
            getCartItemsFromDB() ,

            "COD",
            "Cash on Delivery"
            ,
            null,
            (Shipping(address1, address2, "", null, firstName, lastName, null, null)),
            null
        )

        return apiServices.order(requestData)
    }

    private fun getCartItemsFromDB(): ArrayList<LineItem>? {

        val cartDTOList: List<CartItemModel> = ShoppingCart.getCart()
        val jsonArray = ArrayList<LineItem>()
        if (cartDTOList != null && cartDTOList.size > 0) {
            for (i in cartDTOList.indices) {
                try {
                    jsonArray.add( LineItem(cartDTOList[i].product.id!!,cartDTOList[i].quantity,0))

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
        return jsonArray
    }


//    fun fromJson(json: Int): OrderBody {
//        return Gson().fromJson<OrderBody>(json.toString(), OrderBody::class.java)
//
//    }


}