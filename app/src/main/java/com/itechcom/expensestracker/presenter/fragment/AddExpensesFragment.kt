package com.itechcom.expensestracker.presenter.fragment

import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddExpensesBinding
import com.itechcom.expensestracker.presenter.viewmodel.SingleViewModel

class AddExpensesFragment : BaseFragment<FragmentAddExpensesBinding, SingleViewModel>(
    FragmentAddExpensesBinding::inflate,
    SingleViewModel::class) {
}