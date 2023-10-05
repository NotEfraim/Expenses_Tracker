package com.itechcom.expensestracker.presenter.fragment

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.database.IncomeExpensesEntityList
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentViewAllExpenseIncomeBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel
import com.itechcom.expensestracker.presenter.adapter.IncomeExpenseAdapter
import com.itechcom.expensestracker.presenter.viewmodel.IncomeExpensesViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.navigateTo
import com.itechcom.expensestracker.utils.extensions.useEmptyView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllExpensesIncomeFragment : BaseFragment<FragmentViewAllExpenseIncomeBinding, IncomeExpensesViewModel>(
    FragmentViewAllExpenseIncomeBinding::inflate,
    IncomeExpensesViewModel::class) {

    private var planId : String? = null
    private var planModel : PlanEntity? = null
    private val incomeExpenseAdapter = IncomeExpenseAdapter()

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            getBundles()
            viewModel.getIncomeExpenses(planId?:return@launch)
        }
    }

    override fun IncomeExpensesViewModel.initObserver() {
        collect(incomeExpenses, :: initIncomeExpenses)
    }

    override fun FragmentViewAllExpenseIncomeBinding.initialize() {
        allExpensesIncomeRecycler.adapter = incomeExpenseAdapter
        initViews()
    }

    private fun getBundles(){
        planId = arguments?.getString("plan_id")
        planModel = arguments?.getSerializable("plan_model") as PlanEntity
    }

    private fun initViews(){
        incomeExpenseAdapter.setOnItemClickListener{ item, v, position ->
            val bundle = Bundle()
            bundle.putString("plan_id", planId)
            bundle.putSerializable("plan_model", planModel)
            bundle.putSerializable("incomeExpenses_model", item.getItem(position))
            if(item.getItem(position)?.type == "income"){
                v.navigateTo(R.id.action_allExpensesIncomeFragment_to_editIncomeFragment22, bundle)
            }else{
                v.navigateTo(R.id.action_allExpensesIncomeFragment_to_editExpensesFragment22, bundle)
            }
        }
    }

    private fun initIncomeExpenses(data : IncomeExpensesEntityList?){
        if(data?.data == null || data.data == incomeExpenseAdapter.items) {
            lifecycleScope.launch {
                delay(2000)
                hideLoadingDialog()
            }
            return
        }
        incomeExpenseAdapter.submitList(data.data)
        incomeExpenseAdapter.useEmptyView()
        hideLoadingDialog()
    }
}