package com.itechcom.expensestracker.presenter.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.database.IncomeExpensesEntityList
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentViewPlanBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel
import com.itechcom.expensestracker.presenter.adapter.IncomeExpenseAdapter
import com.itechcom.expensestracker.presenter.viewmodel.ViewPlanViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.navigateTo
import com.itechcom.expensestracker.utils.extensions.useEmptyView
import kotlinx.coroutines.launch

class ViewPlanFragment : BaseFragment<FragmentViewPlanBinding, ViewPlanViewModel>(
    FragmentViewPlanBinding::inflate,
    ViewPlanViewModel::class
) , View.OnClickListener {

    private val incomeExpenseAdapter = IncomeExpenseAdapter()
    private var planId : String? = null

    override fun ViewPlanViewModel.initCall() {
        getBundleExtras()
        lifecycleScope.launch {
            val key = planId?:return@launch
            getPlan(key)
            getIncomeExpenses(key)
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

    private fun initIncomeExpenses(data : IncomeExpensesEntityList){
        incomeExpenseAdapter.submitList(data.data)
        incomeExpenseAdapter.useEmptyView()
    }

    @SuppressLint("SetTextI18n")
    private fun initData(model : PlanEntity) = binding.apply {
        val expenses = model.totalExpenses?.toInt()?:0
        val income = model.totalIncome?.toInt()?:0
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

        incomeText.text = "₱$expenses.00 of ₱$budgetIncome.00"
        incomeProgress.max = budget
        incomeProgress.progress = income
    }

    private fun initClicks() = binding.apply {
        editPlanBtn.setOnClickListener(::onClick)
        addExpensesBtn.setOnClickListener(::onClick)
        addIncomeBtn.setOnClickListener(::onClick)
        moreBtn.setOnClickListener(::onClick)

        incomeExpenseAdapter.setOnItemClickListener{ _, v, _ ->
            v.navigateTo(R.id.actionToEditIncome)
        }


    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        bundle.putString("plan_id", planId)
        binding.apply {
            when(v?.id){
                editPlanBtn.id -> {
                    v.navigateTo(R.id.actionToEditPlanFragment)
                }

                addExpensesBtn.id -> {
                    v.navigateTo(R.id.actionToAddExpensesFragment)
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
}