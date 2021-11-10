package com.masai.naliniapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.naliniapp.R
import com.masai.naliniapp.model.local.MoneyEntity

class Adapter( var onClick: OnClick,var moneyEntity: MutableList<MoneyEntity>,var context:Context): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view1: View = inflater.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view1,onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val type=moneyEntity.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        return moneyEntity.size
    }
}