package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAllPlansBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel
import com.itechcom.expensestracker.presenter.adapter.BudgetPlanAdapter
import com.itechcom.expensestracker.utils.extensions.navigateTo

class AllPlansFragment : BaseFragment<FragmentAllPlansBinding, MainViewModel>(
    FragmentAllPlansBinding::inflate,
    MainViewModel::class
) {

    override fun FragmentAllPlansBinding.initialize() {
        Log.d("fragmentState", "initialize: ")
        val budgetPlanAdapter = BudgetPlanAdapter()
        allPlansRecycler.adapter = budgetPlanAdapter

        budgetPlanAdapter.setOnItemClickListener { _, v, _ ->
            v.navigateTo(R.id.action_fragmentAllPlans_to_viewPlanFragment)
        }
    }
}