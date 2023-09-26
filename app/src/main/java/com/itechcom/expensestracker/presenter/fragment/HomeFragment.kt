package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentHomeBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel
import com.itechcom.expensestracker.presenter.adapter.BudgetPlanAdapter
import com.itechcom.expensestracker.utils.extensions.navigateTo

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>(
    FragmentHomeBinding::inflate,
    MainViewModel::class
) {

    private val budgetPlanAdapter = BudgetPlanAdapter()

    override fun FragmentHomeBinding.initialize() {
        Log.d("fragmentState", "initialize: ")
        planRecycler.adapter = budgetPlanAdapter
        moreBtn.setOnClickListener { it.navigateTo(R.id.action_toAllPlans) }
        budgetPlanAdapter.setOnItemClickListener { _, view, _ ->
            view.navigateTo(R.id.actionToViewPlanFragment)
        }
    }

}