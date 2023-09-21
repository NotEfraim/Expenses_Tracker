package com.itechcom.expensestracker.presenter.fragment

import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentEditExpensesBinding
import com.itechcom.expensestracker.presenter.SingleViewModel

class EditExpensesFragment : BaseFragment<FragmentEditExpensesBinding, SingleViewModel>(
    FragmentEditExpensesBinding::inflate,
    SingleViewModel::class) {
}