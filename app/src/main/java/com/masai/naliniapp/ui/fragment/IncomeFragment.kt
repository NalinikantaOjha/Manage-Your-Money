package com.masai.naliniapp.ui.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.naliniapp.R
import com.masai.naliniapp.model.local.MoneyDao
import com.masai.naliniapp.model.local.MoneyDatabase
import com.masai.naliniapp.model.local.MoneyEntity
import com.masai.naliniapp.repository.MoneyRepo
import com.masai.naliniapp.ui.adapter.Adapter
import com.masai.naliniapp.ui.adapter.OnClick
import com.masai.naliniapp.viewmodel.MoneyViewModel
import com.masai.naliniapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_income.*


class IncomeFragment : Fragment(),OnClick {
    lateinit var adapter: Adapter
    private val List = mutableListOf<MoneyEntity>()
    lateinit var viewModel: MoneyViewModel
    lateinit var repository:MoneyRepo
    lateinit var Dao: MoneyDao
    lateinit var roomDb:MoneyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomDb = MoneyDatabase.getMoneyDatabase(requireContext())
        Dao = roomDb.getMoneyDao()
        val repo = MoneyRepo(Dao)
        val viewModelFactory = ViewModelFactory(repo)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MoneyViewModel::class.java)

        viewModel.getIncome().observe(viewLifecycleOwner, Observer {
            List.clear()
            List.addAll(it)
            adapter.notifyDataSetChanged()
        })
        adapter= Adapter(this ,List,context as Activity)
        recycleHistoryPay.adapter = adapter
        recycleHistoryPay.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun update(modelEntity: MoneyEntity) {

        linierBalance.setVisibility(View.VISIBLE)
        etAmount2.setText(modelEntity.ammount.toString())
        etDD2.setText(modelEntity.dd)
        etMM2.setText(modelEntity.mm)
        etYY2.setText(modelEntity.yy)
        etDesc2.setText(modelEntity.desc)



        btnUpdate.setOnClickListener {
            var  ammount2 = Integer.parseInt(etAmount2.text.toString())
            modelEntity.apply {
                ammount = ammount2
                desc = etDesc2.text.toString()
                dd = etDD2.text.toString()
                mm=etMM2.text.toString()
                yy=etYY2.text.toString()
                type = spinner2.selectedItem.toString()
            }

            viewModel.update(modelEntity)
            linierBalance.setVisibility(View.INVISIBLE)
        }







    }

    override fun delete(modelEntity: MoneyEntity) {
        viewModel.delete(modelEntity)

    }
}