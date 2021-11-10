package com.masai.naliniapp.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moneyTable")
data class MoneyEntity(
@ColumnInfo(name = "type")var type:String,
@ColumnInfo(name = "ammount")var ammount:Int,
@ColumnInfo(name = "dd")var dd:String,
@ColumnInfo(name = "mm")var mm:String,
@ColumnInfo(name = "yy")var yy:String,
@ColumnInfo(name = "desc")var desc:String
) {
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")var id:Int=0

}