package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentEditPlanBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel

class EditPlanFragment : BaseFragment<FragmentEditPlanBinding, MainViewModel>(
    FragmentEditPlanBinding::inflate,
    MainViewModel::class) {

    override fun FragmentEditPlanBinding.initialize() {
        Log.d("fragmentState", "initialize: ")
    }
}