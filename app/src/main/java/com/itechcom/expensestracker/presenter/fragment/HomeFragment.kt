package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
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
            getAllPlans(5)
        }
    }

    override fun HomeViewModel.initObserver() {
        collect(errorToast, ::errorToaster)
        collect(plansResponse, ::initPlansData)
    }

    override fun FragmentHomeBinding.initialize() {
        showLoadingDialog()
        moreBtn.setOnClickListener(::onClick)
        addPlanBtn.setOnClickListener(::onClick)
        initViews()
        initBanner()
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
        if (planDate.text.isNullOrEmpty()) {
            bannerContainer.visibility = View.GONE
            noPlanLottie.visibility = View.VISIBLE
            noPlanText.visibility = View.VISIBLE
        } else {
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