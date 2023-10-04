package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddExpensesBinding
import com.itechcom.expensestracker.presenter.viewmodel.IncomeExpensesViewModel
import com.itechcom.expensestracker.utils.extensions.createSnackBar
import com.itechcom.expensestracker.utils.extensions.showDatePicker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddExpensesFragment : BaseFragment<FragmentAddExpensesBinding, IncomeExpensesViewModel>(
    FragmentAddExpensesBinding::inflate,
    IncomeExpensesViewModel::class) {

    private var planId : String? = null
    override fun FragmentAddExpensesBinding.initialize() {
        planId = arguments?.getString("plan_id")
        Log.d( "planHunter: ", "$planId")
        addExpenses()
    }

    private fun addExpenses() = binding.apply {

        selectDate.setOnClickListener {
            selectDate.showDatePicker()
        }

        saveBtn.setOnClickListener {
            lifecycleScope.launch {
                showLoadingDialog()
                if(source.text.isNullOrEmpty() || amount.text.isNullOrEmpty() || selectDate.text.isNullOrEmpty()){
                    requireActivity().createSnackBar("All Field is required"){}
                    return@launch
                }

                val entity = IncomeExpensesEntity(
                    planId = planId,
                    type = "expenses",
                    name = "${source.text}",
                    amount = amount.text.toString().toInt(),
                    stringDate = "${selectDate.text}",
                    description = "${description.text}"
                )
                val result = viewModel.addIncomeExpenses(entity)
                if(result) {
                    delay(1000)
                    hideLoadingDialog()
                    this@AddExpensesFragment.findNavController().popBackStack()
                }
            }
        }
    }
}