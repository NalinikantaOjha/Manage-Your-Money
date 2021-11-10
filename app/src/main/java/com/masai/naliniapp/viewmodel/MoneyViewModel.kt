package com.masai.naliniapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masai.naliniapp.model.local.MoneyEntity
import com.masai.naliniapp.repository.MoneyRepo

class MoneyViewModel(val moneyRepo: MoneyRepo):ViewModel() {
fun createType(money:MoneyEntity){
    moneyRepo.createMoney(money)

}
    fun getTrans():LiveData<List<MoneyEntity>>{
        return moneyRepo.getTrans()
    }
    fun getExpense():LiveData<List<MoneyEntity>>{
        return moneyRepo.getExpense()
    }
    fun update(money: MoneyEntity){
        moneyRepo.update(money)
    }
    fun delete(money: MoneyEntity){
        moneyRepo.delete(money)
    }
    fun getIncome():LiveData<List<MoneyEntity>>{
       return moneyRepo.getIncome()
    }

}