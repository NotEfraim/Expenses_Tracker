package com.itechcom.expensestracker.presenter.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentViewPlanBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel
import com.itechcom.expensestracker.presenter.adapter.IncomeExpenseAdapter
import com.itechcom.expensestracker.utils.extensions.navigateTo

class ViewPlanFragment : BaseFragment<FragmentViewPlanBinding, MainViewModel>(
    FragmentViewPlanBinding::inflate,
    MainViewModel::class
) , View.OnClickListener {

    private val incomeExpenseAdapter = IncomeExpenseAdapter()

    override fun FragmentViewPlanBinding.initialize() {
        initViews()
    }


    private fun initViews() = binding.apply {
        expensesIncomeRecycler.adapter = incomeExpenseAdapter
        getBundleExtras()
        initClicks()
    }

    @SuppressLint("SetTextI18n")
    private fun getBundleExtras() = binding.apply {
        val args = arguments ?: return@apply
        val model = args.getSerializable("plan_model") as PlanEntity
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
        binding.apply {
            when(v?.id){
                editPlanBtn.id -> {
                    v.navigateTo(R.id.actionToEditPlanFragment)
                }

                addExpensesBtn.id -> {
                    v.navigateTo(R.id.actionToAddExpensesFragment)
                }

                addIncomeBtn.id -> {
                    v.navigateTo(R.id.actionToAddIncomeFragment)
                }

                moreBtn.id -> {
                    v.navigateTo(R.id.action_viewPlanFragment_to_allExpensesIncomeFragment2)
                }
            }
        }
    }
}