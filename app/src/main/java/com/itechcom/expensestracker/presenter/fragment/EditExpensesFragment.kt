package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentEditExpensesBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel

class EditExpensesFragment : BaseFragment<FragmentEditExpensesBinding, MainViewModel>(
    FragmentEditExpensesBinding::inflate,
    MainViewModel::class) {

    override fun FragmentEditExpensesBinding.initialize() {
        Log.d("fragmentState", "initialize: ")
    }
}