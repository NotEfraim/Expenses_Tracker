package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddPlanBinding
import com.itechcom.expensestracker.databinding.FragmentEditPlanBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel

class AddPlanFragment : BaseFragment<FragmentAddPlanBinding, MainViewModel>(
    FragmentAddPlanBinding::inflate,
    MainViewModel::class) {

    override fun FragmentAddPlanBinding.initialize() {
        Log.d("fragmentState", "initialize: ")
    }
}