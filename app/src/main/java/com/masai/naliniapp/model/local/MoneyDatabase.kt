package com.masai.naliniapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MoneyEntity::class],version = 1)
abstract class MoneyDatabase : RoomDatabase() {
    abstract fun getMoneyDao():MoneyDao
    companion object{
        private var instance:MoneyDatabase?=null
        fun getMoneyDatabase(context: Context):MoneyDatabase{
            if (instance!=null){
                return instance!!
            }else{
                val builder= Room.databaseBuilder(
                    context.applicationContext,
                    MoneyDatabase::class.java,
                    "moneydb"
                )
                builder.fallbackToDestructiveMigration()
                instance=builder.build()
            }
            return instance!!
        }
    }
}