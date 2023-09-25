package com.itechcom.expensestracker.presenter.fragment

import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentViewPlanBinding
import com.itechcom.expensestracker.presenter.viewmodel.SingleViewModel
import com.itechcom.expensestracker.presenter.adapter.IncomeExpenseAdapter
import com.itechcom.expensestracker.utils.extensions.navigateTo

class ViewPlanFragment : BaseFragment<FragmentViewPlanBinding, SingleViewModel>(
    FragmentViewPlanBinding::inflate,
    SingleViewModel::class) {

    override fun FragmentViewPlanBinding.initialize() {

        val incomeExpenseAdapter = IncomeExpenseAdapter()
        expensesIncomeRecycler.adapter = incomeExpenseAdapter

        editPlanBtn.setOnClickListener {
            it.navigateTo(R.id.actionToEditPlanFragment)
        }

        incomeExpenseAdapter.setOnItemClickListener{ _, v, _ ->
            v.navigateTo(R.id.actionToEditIncome)
        }

        addExpensesBtn.setOnClickListener {
            it.navigateTo(R.id.actionToAddExpensesFragment)
        }

        addIncomeBtn.setOnClickListener {
            it.navigateTo(R.id.actionToAddIncomeFragment)
        }

        moreBtn.setOnClickListener {
            it.navigateTo(R.id.action_viewPlanFragment_to_allExpensesIncomeFragment2)
        }


    }
}