package com.itechcom.expensestracker.presenter.fragment

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.model.database.PlanEntityList
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentHomeBinding
import com.itechcom.expensestracker.presenter.adapter.BudgetPlanAdapter
import com.itechcom.expensestracker.presenter.viewmodel.HomeViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.navigateTo
import com.itechcom.expensestracker.utils.extensions.toastUtil
import com.itechcom.expensestracker.utils.extensions.useEmptyView
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class
), View.OnClickListener {

    private val budgetPlanAdapter = BudgetPlanAdapter()

    override fun HomeViewModel.initCall() {
        lifecycleScope.launch {
            getAllPlans(10)
            getLatestPlan()
        }
    }

    override fun HomeViewModel.initObserver() {
        collect(errorToast, ::errorToaster)
        collect(plansResponse, ::initPlansData)
        collect(latestPlanResponse, ::getLatestPlan)
    }

    override fun FragmentHomeBinding.initialize() {
        showLoadingDialog()
        moreBtn.setOnClickListener(::onClick)
        addPlanBtn.setOnClickListener(::onClick)
        initViews()
    }

    private fun getLatestPlan(data : PlanEntity) = binding.apply {
        initBanner()
        planDate.text = data.stringDate
        planAmount.text = data.budget?:"0.00"
        expensesAmount.text = data.totalExpenses?:"0.00"
        incomeAmount.text = data.totalIncome?:"0.00"
    }

    private fun initPlansData(data: PlanEntityList) {
        if(isFirstCall){
            isFirstCall = false
            return
        }
        budgetPlanAdapter.submitList(data.data)
        budgetPlanAdapter.useEmptyView()
        hideLoadingDialog()
    }

    private fun initViews() = binding.apply {
        planRecycler.adapter = budgetPlanAdapter
        budgetPlanAdapter.setOnItemClickListener { _, view, _ ->
            view.navigateTo(R.id.actionToViewPlanFragment)
        }
    }

    private fun initBanner() = binding.apply {
        bannerContainer.visibility = View.VISIBLE
        noPlanLottie.visibility = View.GONE
        noPlanText.visibility = View.GONE
    }

    override fun onClick(v: View?) {
        binding.apply {
            when (v?.id) {
                moreBtn.id -> {
                    v.navigateTo(R.id.action_toAllPlans)
                }

                addPlanBtn.id -> {
                    v.navigateTo(R.id.action_homeFragment_to_addPlanFragment)
                }
            }
        }
    }

    private fun errorToaster(error: String) {
        if(error.isEmpty()) return
        requireActivity().toastUtil(error)
    }


}