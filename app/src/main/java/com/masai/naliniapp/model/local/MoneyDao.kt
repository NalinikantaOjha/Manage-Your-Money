package com.masai.naliniapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoneyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTrans(moneyEntity: MoneyEntity)
    @Query("select * from moneyTable")
    fun getTrans():LiveData<List<MoneyEntity>>
    @Update
    fun update(moneyEntity: MoneyEntity)
    @Delete
    fun delete(moneyEntity: MoneyEntity)
    @Query("select * from moneyTable where type = 'Income'")
    fun getAllIncomeData() : LiveData<List<MoneyEntity>>

    @Query("select * from moneyTable where type = 'Expense' ")
    fun getAllExpenseData() : LiveData<List<MoneyEntity>>

}