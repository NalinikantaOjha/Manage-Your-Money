package com.masai.naliniapp.repository

import androidx.lifecycle.LiveData
import com.masai.naliniapp.model.local.MoneyDao
import com.masai.naliniapp.model.local.MoneyEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoneyRepo (val moneyDao: MoneyDao) {
    fun createMoney(moneyEntity: MoneyEntity){
        CoroutineScope(Dispatchers.IO).launch {
            moneyDao.addTrans(moneyEntity)

        }
    }
    fun getTrans():LiveData<List<MoneyEntity>>{
        return moneyDao.getTrans()
    }
    fun getIncome():LiveData<List<MoneyEntity>>{
        return moneyDao.getAllIncomeData()
    }
    fun getExpense():LiveData<List<MoneyEntity>>{
       return moneyDao.getAllExpenseData()
    }
    fun update(moneyEntity: MoneyEntity){
        CoroutineScope(Dispatchers.IO).launch {
            moneyDao.update(moneyEntity)
        }
    }
    fun delete(moneyEntity: MoneyEntity){
        CoroutineScope(Dispatchers.IO).launch {
            moneyDao.delete(moneyEntity)
        }
    }
}