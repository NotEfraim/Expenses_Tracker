package com.itechcom.expensestracker.presenter.fragment

import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddIncomeBinding
import com.itechcom.expensestracker.presenter.viewmodel.SingleViewModel

class AddIncomeFragment : BaseFragment<FragmentAddIncomeBinding, SingleViewModel>(
    FragmentAddIncomeBinding::inflate,
    SingleViewModel::class) {
}