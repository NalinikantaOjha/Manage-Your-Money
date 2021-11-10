package com.masai.naliniapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masai.naliniapp.repository.MoneyRepo

class ViewModelFactory(val repo: MoneyRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoneyViewModel(repo)as T
    }
}