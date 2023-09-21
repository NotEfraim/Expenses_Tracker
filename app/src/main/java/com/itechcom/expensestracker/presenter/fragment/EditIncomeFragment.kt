package com.itechcom.expensestracker.presenter.fragment

import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentEditIncomeBinding
import com.itechcom.expensestracker.presenter.SingleViewModel

class EditIncomeFragment : BaseFragment<FragmentEditIncomeBinding, SingleViewModel>(
    FragmentEditIncomeBinding::inflate,
    SingleViewModel::class) {
}