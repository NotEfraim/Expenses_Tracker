package com.itechcom.expensestracker.presenter.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.database.IncomeExpensesEntityList
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentViewPlanBinding
import com.itechcom.expensestracker.presenter.adapter.IncomeExpenseAdapter
import com.itechcom.expensestracker.presenter.viewmodel.ViewPlanViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.navigateTo
import com.itechcom.expensestracker.utils.extensions.useEmptyView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewPlanFragment : BaseFragment<FragmentViewPlanBinding, ViewPlanViewModel>(
    FragmentViewPlanBinding::inflate,
    ViewPlanViewModel::class
) , View.OnClickListener {

    private val incomeExpenseAdapter = IncomeExpenseAdapter()
    private var planId : String? = null
    private var modelHolder : IncomeExpensesEntityList? = null
    private var planHolder : PlanEntity? = null

    override fun onResume() {
        super.onResume()
        getBundleExtras()
        lifecycleScope.launch {
            val key = planId?:return@launch
            viewModel.getIncomeExpenses(key)
            viewModel.getPlan(key)
        }
    }

    override fun ViewPlanViewModel.initObserver() {
        collect(plan, ::initData)
        collect(incomeExpenses, :: initIncomeExpenses)
    }

    override fun FragmentViewPlanBinding.initialize() {
        initViews()
    }

    private fun initViews() = binding.apply {
        expensesIncomeRecycler.adapter = incomeExpenseAdapter
        initClicks()
    }

    private fun getBundleExtras() = binding.apply {
        val args = arguments ?: return@apply
        planId = args.getString("plan_id")
    }

    private fun initIncomeExpenses(data : IncomeExpensesEntityList?){
        if(data?.data == null || data.data == incomeExpenseAdapter.items) {
            lifecycleScope.launch {
                delay(2000)
                hideLoadingDialog()
            }
            return
        }
        modelHolder = data
        incomeExpenseAdapter.submitList(data.data)
        incomeExpenseAdapter.useEmptyView()
        hideLoadingDialog()
    }

    @SuppressLint("SetTextI18n")
    private fun initData(model : PlanEntity) = binding.apply {
        planHolder = model
        var expenses = 0
        var income = 0

        modelHolder?.data?.map {
            if(it.type == "income"){
                income += it.amount?:0
            }else{
                expenses += it.amount?:0
            }
        }

        if(model.totalExpenses?.toInt() != expenses || model.totalIncome?.toInt() != income) {
            lifecycleScope.launch {
                model.totalExpenses = expenses.toString()
                model.totalIncome = income.toString()
                viewModel.updatePlan(planId ?: return@launch, model)
            }
        }

        val budget = model.budget?.toInt()?:0
        val totalLeft = (budget - expenses) + income
        val budgetIncome = budget + income

        totalProgress.max = budget
        totalProgress.progress = expenses
        totalLeftText.text = "₱$totalLeft.00"
        totalText.text = "₱$expenses.00 of ₱$totalLeft.00"

        expensesProgress.max = budget
        expensesProgress.progress = expenses
        expensesLeftText.text = "₱$budget.00"
        expensesText.text =  "₱$expenses.00 of ₱$budget.00"

        totalIncomeLeftText.text = "Total Income + Budget ₱$budgetIncome.00"
        incomeText.text = "₱$income.00 of ₱$budget.00 budget"
        incomeProgress.max = budget
        incomeProgress.progress = income

        planDate.text = model.stringDate

        incomeExpenseAdapter.setOnItemClickListener{ item, v, position ->
            val bundle = Bundle()
            bundle.putString("plan_id", planId)
            bundle.putSerializable("plan_model", model)
            bundle.putSerializable("incomeExpenses_model", item.getItem(position))
            if(item.getItem(position)?.type == "income"){
                v.navigateTo(R.id.actionToEditIncome, bundle)
            }else{
                v.navigateTo(R.id.actionToEditExpenses, bundle)
            }
        }
    }

    private fun initClicks() = binding.apply {
        editPlanBtn.setOnClickListener(::onClick)
        addExpensesBtn.setOnClickListener(::onClick)
        addIncomeBtn.setOnClickListener(::onClick)
        moreBtn.setOnClickListener(::onClick)
    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        bundle.putString("plan_id", planId)
        binding.apply {
            when(v?.id){
                editPlanBtn.id -> {
                    bundle.putSerializable("plan_model", planHolder)
                    v.navigateTo(R.id.actionToEditPlanFragment, bundle)
                }

                addExpensesBtn.id -> {
                    v.navigateTo(R.id.actionToAddExpensesFragment, bundle)
                }

                addIncomeBtn.id -> {
                    v.navigateTo(R.id.actionToAddIncomeFragment, bundle)
                }

                moreBtn.id -> {
                    v.navigateTo(R.id.action_viewPlanFragment_to_allExpensesIncomeFragment2)
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.clearStates()
    }
}