package com.masai.naliniapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProviders
import com.masai.naliniapp.R
import com.masai.naliniapp.model.local.MoneyDao
import com.masai.naliniapp.model.local.MoneyDatabase
import com.masai.naliniapp.model.local.MoneyEntity
import com.masai.naliniapp.repository.MoneyRepo
import com.masai.naliniapp.ui.adapter.Adapter
import com.masai.naliniapp.viewmodel.MoneyViewModel
import com.masai.naliniapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.masai.naliniapp.ui.fragment.BalanceFragment
import com.masai.naliniapp.ui.fragment.ExpenceFragment
import com.masai.naliniapp.ui.fragment.IncomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var Adapter: Adapter
    var totalIncome:Int=0
    var totalExpense:Int=0
    private val List = mutableListOf<MoneyEntity>()
    lateinit var viewModel: MoneyViewModel
    lateinit var repository:MoneyRepo
    lateinit var Dao: MoneyDao
    lateinit var roomDb:MoneyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        roomDb = MoneyDatabase.getMoneyDatabase(this)
        Dao = roomDb.getMoneyDao()
        val repo = MoneyRepo(Dao)
        val viewModelFactory = ViewModelFactory(repo)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MoneyViewModel::class.java)




        viewModel.getIncome().observe(this, {
            val datas = it
            totalIncome=0
            datas.forEach {
                if (it.type == "Income") totalIncome += it.ammount
            }

        })
        //
        viewModel.getExpense().observe(this, {
            val datas = it
            totalExpense = 0
            datas.forEach {
                if (it.type == "Expense") totalExpense += it.ammount
            }
            tvExpense.text = "Expense: "+totalExpense
            tvIncome.text = "Income: "+totalIncome
            val balance = totalIncome - totalExpense
           tvTotalBalance.text = balance.toString()
        })



        add.setOnClickListener{
    startActivity(Intent(this,AddActivity::class.java))

}
        fragmentScreens()


    }
    @SuppressLint("NonConstantResourceId")
    fun fragmentScreens () {
        supportFragmentManager.beginTransaction().replace(R.id.mainframe, IncomeFragment())
            .commit()

        bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.menuHome -> temp = IncomeFragment()
                R.id.menuPortfolio -> temp = ExpenceFragment()
                R.id.menuRewards -> temp = BalanceFragment()

            }
            assert(temp != null)
            supportFragmentManager.beginTransaction().replace(R.id.mainframe, temp!!).commit()
            true
        })
    }


}