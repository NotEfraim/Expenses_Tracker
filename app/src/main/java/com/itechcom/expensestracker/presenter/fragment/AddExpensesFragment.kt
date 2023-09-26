package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddExpensesBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel

class AddExpensesFragment : BaseFragment<FragmentAddExpensesBinding, MainViewModel>(
    FragmentAddExpensesBinding::inflate,
    MainViewModel::class) {

    override fun FragmentAddExpensesBinding.initialize() {
        Log.d("fragmentState", "initialize: ")
    }
}