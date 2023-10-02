package com.itechcom.expensestracker.presenter.fragment

import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.database.PlanEntityList
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAllPlansBinding
import com.itechcom.expensestracker.presenter.adapter.BudgetPlanAdapter
import com.itechcom.expensestracker.presenter.viewmodel.AllPlansViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.navigateTo
import com.itechcom.expensestracker.utils.extensions.useEmptyView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllPlansFragment : BaseFragment<FragmentAllPlansBinding, AllPlansViewModel>(
    FragmentAllPlansBinding::inflate,
    AllPlansViewModel::class
) {

    private val budgetPlanAdapter = BudgetPlanAdapter()


    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.getAllPlans(100)
        }
    }

    override fun AllPlansViewModel.initObserver() {
        collect(plansResponse, ::initPlansData)
    }

    override fun FragmentAllPlansBinding.initialize() {
        showLoadingDialog()
        allPlansRecycler.adapter = budgetPlanAdapter
        budgetPlanAdapter.setOnItemClickListener { _, v, _ ->
            v.navigateTo(R.id.action_fragmentAllPlans_to_viewPlanFragment)
        }

        addPlanBtn.setOnClickListener {
            it.navigateTo(R.id.action_fragmentAllPlans_to_addPlanFragment)
        }
    }

    private fun initPlansData(data: PlanEntityList) = binding.apply {
        if(isFirstCall){
            isFirstCall = false
            return@apply
        }
        hideLoadingDialog()
        budgetPlanAdapter.submitList(data.data)
        budgetPlanAdapter.useEmptyView()
        hideLoadingDialog()
    }
}