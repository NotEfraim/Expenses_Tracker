package com.itechcom.expensestracker.presenter.fragment

import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAllPlansBinding
import com.itechcom.expensestracker.presenter.viewmodel.SingleViewModel
import com.itechcom.expensestracker.presenter.adapter.BudgetPlanAdapter
import com.itechcom.expensestracker.utils.extensions.navigateTo

class AllPlansFragment : BaseFragment<FragmentAllPlansBinding, SingleViewModel>(
    FragmentAllPlansBinding::inflate,
    SingleViewModel::class
) {

    override fun FragmentAllPlansBinding.initialize() {
        val budgetPlanAdapter = BudgetPlanAdapter()
        allPlansRecycler.adapter = budgetPlanAdapter

        budgetPlanAdapter.setOnItemClickListener { _, v, _ ->
            v.navigateTo(R.id.action_fragmentAllPlans_to_viewPlanFragment)
        }
    }
}