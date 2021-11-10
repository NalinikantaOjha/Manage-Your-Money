package com.masai.naliniapp.ui.adapter

import com.masai.naliniapp.model.local.MoneyEntity

interface OnClick {
    fun update(modelEntity: MoneyEntity)
    fun delete(modelEntity: MoneyEntity)
}