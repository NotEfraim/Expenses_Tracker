package com.itechcom.expensestracker.presenter.fragment

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.data.room.PlanEntity
import com.itechcom.expensestracker.databinding.FragmentHomeBinding
import com.itechcom.expensestracker.presenter.SingleViewModel
import com.itechcom.expensestracker.presenter.adapter.BudgetPlanAdapter
import com.itechcom.expensestracker.utils.extensions.navigateTo

class HomeFragment : BaseFragment<FragmentHomeBinding, SingleViewModel>(
    FragmentHomeBinding::inflate,
    SingleViewModel::class
) {

    private val budgetPlanAdapter = BudgetPlanAdapter()

    override fun FragmentHomeBinding.initialize() {
        planRecycler.adapter = budgetPlanAdapter
        moreBtn.setOnClickListener { it.navigateTo(R.id.action_toAllPlans) }
        budgetPlanAdapter.setOnItemClickListener { _, view, _ ->
            view.navigateTo(R.id.actionToViewPlanFragment)
        }
    }

}