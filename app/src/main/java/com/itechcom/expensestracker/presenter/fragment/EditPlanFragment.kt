package com.itechcom.expensestracker.presenter.fragment

import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentEditPlanBinding
import com.itechcom.expensestracker.presenter.viewmodel.SingleViewModel

class EditPlanFragment : BaseFragment<FragmentEditPlanBinding, SingleViewModel>(
    FragmentEditPlanBinding::inflate,
    SingleViewModel::class) {

    override fun FragmentEditPlanBinding.initialize() {

    }
}