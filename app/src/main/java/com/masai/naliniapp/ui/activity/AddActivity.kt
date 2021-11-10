package com.masai.naliniapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.masai.naliniapp.R
import com.masai.naliniapp.model.local.MoneyDao
import com.masai.naliniapp.model.local.MoneyDatabase
import com.masai.naliniapp.model.local.MoneyEntity
import com.masai.naliniapp.repository.MoneyRepo
import com.masai.naliniapp.viewmodel.MoneyViewModel
import com.masai.naliniapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    lateinit var viewModel: MoneyViewModel
    lateinit var repository:MoneyRepo
    lateinit var Dao: MoneyDao
    lateinit var roomDb:MoneyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        roomDb = MoneyDatabase.getMoneyDatabase(this)
        Dao = roomDb.getMoneyDao()
        val repo = MoneyRepo(Dao)
        val viewModelFactory = ViewModelFactory(repo)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MoneyViewModel::class.java)
        btnAdd.setOnClickListener {
            var amount = etAmount.text.toString().toInt()
            var desc = etDesc.text.toString()
            var currentDate = etDD.text.toString()
            var mm=etMM.text.toString()
            var yy=etYY.text.toString()
            var category = spinner.selectedItem.toString()
            val add=MoneyEntity(category,amount,currentDate,mm,yy,desc)
            viewModel.createType(add)
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}