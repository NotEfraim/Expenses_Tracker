package com.itechcom.expensestracker.presenter.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class
), View.OnClickListener {

    private val budgetPlanAdapter = BudgetPlanAdapter()

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.getAllPlans(20)
            viewModel.getLatestPlan()
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

    @SuppressLint("SetTextI18n")
    private fun getLatestPlan(data : PlanEntity?) = binding.apply {

        if(data?.budget == null) {
            initBanner(true)
            return@apply
        }

        initBanner(false)
        planDate.text = data.stringDate
        planAmount.text = "${data.budget?:"0"}.00"
        expensesAmount.text = data.totalExpenses?:"₱0.00"
        incomeAmount.text = data.totalIncome?:"₱0.00"
    }

    private fun initPlansData(data: PlanEntityList?) {
        budgetPlanAdapter.submitList(data?.data)
        budgetPlanAdapter.useEmptyView()
        if(data?.data == null){
            budgetPlanAdapter.useEmptyView()
            lifecycleScope.launch {
                delay(2000)
                hideLoadingDialog()
            }
            return
        }

        hideLoadingDialog()
        budgetPlanAdapter.setOnItemClickListener { _, view, position ->
            val bundle = Bundle()
            val adapterData = data.data?.get(position)
            bundle.putString("plan_id", adapterData?.planId)
            view.navigateTo(R.id.actionToViewPlanFragment, bundle)
        }
    }

    private fun initViews() = binding.apply {
        planRecycler.adapter = budgetPlanAdapter
    }

    private fun initBanner(hide : Boolean) = binding.apply {
        if(hide){
            bannerContainer.visibility = View.GONE
            noPlanLottie.visibility = View.VISIBLE
            noPlanText.visibility = View.VISIBLE
        }else{
            bannerContainer.visibility = View.VISIBLE
            noPlanLottie.visibility = View.GONE
            noPlanText.visibility = View.GONE
        }

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