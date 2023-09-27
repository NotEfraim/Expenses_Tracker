package com.itechcom.expensestracker.presenter.fragment

import android.view.View
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentHomeBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel
import com.itechcom.expensestracker.presenter.adapter.BudgetPlanAdapter
import com.itechcom.expensestracker.utils.extensions.navigateTo
import com.itechcom.expensestracker.utils.extensions.useEmptyView

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>(
    FragmentHomeBinding::inflate,
    MainViewModel::class
) {

    private val budgetPlanAdapter = BudgetPlanAdapter()

    override fun FragmentHomeBinding.initialize() {
        initViews()
        initBanner()
    }

    private fun initViews() = binding.apply {
        planRecycler.adapter = budgetPlanAdapter
        budgetPlanAdapter.useEmptyView()

        moreBtn.setOnClickListener { it.navigateTo(R.id.action_toAllPlans) }
        budgetPlanAdapter.setOnItemClickListener { _, view, _ ->
            view.navigateTo(R.id.actionToViewPlanFragment)
        }
        addPlanBtn.setOnClickListener {
            it.navigateTo(R.id.action_homeFragment_to_addPlanFragment)
        }
    }

    private fun initBanner() = binding.apply {
        if(planDate.text.isNullOrEmpty()){
            bannerContainer.visibility = View.GONE
            noPlanLottie.visibility = View.VISIBLE
            noPlanText.visibility = View.VISIBLE
        }else{
            bannerContainer.visibility = View.VISIBLE
            noPlanLottie.visibility = View.GONE
            noPlanText.visibility = View.GONE
        }
    }

}