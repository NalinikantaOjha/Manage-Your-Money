package com.masai.naliniapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.masai.naliniapp.model.local.MoneyEntity
import kotlinx.android.synthetic.main.item_layout.view.*

class ViewHolder(itemView: View,var onClick: OnClick):RecyclerView.ViewHolder(itemView) {
fun setData(modelEntity: MoneyEntity){
    itemView.apply {
        tvAmount.setText(modelEntity.ammount.toString())
        tvDD.setText(modelEntity.dd+" /")
        tvMM.setText(modelEntity.mm+" /")
        tvyy.setText(modelEntity.yy)
        tvType.setText(modelEntity.type)
        tvDesc.setText(modelEntity.desc)
        imDelete.setOnClickListener {
            onClick.delete(modelEntity)
        }
        imUpdate.setOnClickListener {
            onClick.update(modelEntity)
        }
    }
}


}