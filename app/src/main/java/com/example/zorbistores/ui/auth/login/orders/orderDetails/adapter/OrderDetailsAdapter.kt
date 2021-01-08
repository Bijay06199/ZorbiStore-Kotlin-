package com.example.zorbistores.ui.auth.login.orders.orderDetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zorbistores.R
import com.example.zorbistores.ui.auth.login.orders.orderDetails.model.OrderDetailsModel
import kotlinx.android.synthetic.main.recyclerview_order_details.view.*

class OrderDetailsAdapter (var itemList:List<OrderDetailsModel>):RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder>(){



    inner class ViewHolder(val containerView: View):RecyclerView.ViewHolder(containerView){

        var product=containerView.product
        var quantity=containerView.quantity
        var rate=containerView.rate
        var total=containerView.quantity_rate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_order_details,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.product.setText(itemList[position].product)
        holder.quantity.setText(itemList[position].quantity.toString())
        holder.rate.setText(itemList[position].rate)
        holder.total.setText(itemList[position].total!!.toString())

    }
    
    fun addAll(itemList: ArrayList<OrderDetailsModel>){
        this.itemList=itemList
        notifyDataSetChanged()
        
    }


}