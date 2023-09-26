package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddIncomeBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel

class AddIncomeFragment : BaseFragment<FragmentAddIncomeBinding, MainViewModel>(
    FragmentAddIncomeBinding::inflate,
    MainViewModel::class) {

    override fun FragmentAddIncomeBinding.initialize() {
        Log.d("fragmentState", "initialize: ")

    }
}