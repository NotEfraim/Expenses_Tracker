package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentEditIncomeBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel

class EditIncomeFragment : BaseFragment<FragmentEditIncomeBinding, MainViewModel>(
    FragmentEditIncomeBinding::inflate,
    MainViewModel::class) {

    override fun FragmentEditIncomeBinding.initialize() {
        Log.d("fragmentState", "initialize: ")
    }
}