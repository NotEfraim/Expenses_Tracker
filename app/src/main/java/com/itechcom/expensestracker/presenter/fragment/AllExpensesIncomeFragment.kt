package com.itechcom.expensestracker.presenter.fragment

import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentViewAllExpenseIncomeBinding
import com.itechcom.expensestracker.presenter.viewmodel.SingleViewModel
import com.itechcom.expensestracker.presenter.adapter.IncomeExpenseAdapter
import com.itechcom.expensestracker.utils.extensions.navigateTo

class AllExpensesIncomeFragment : BaseFragment<FragmentViewAllExpenseIncomeBinding, SingleViewModel>(
    FragmentViewAllExpenseIncomeBinding::inflate,
    SingleViewModel::class) {

    override fun FragmentViewAllExpenseIncomeBinding.initialize() {
        val incomeExpenseAdapter = IncomeExpenseAdapter()
        allExpensesIncomeRecycler.adapter = incomeExpenseAdapter

        incomeExpenseAdapter.setOnItemClickListener { _, v, _ ->
            v.navigateTo(R.id.action_allExpensesIncomeFragment_to_editIncomeFragment22)
        }
    }
}