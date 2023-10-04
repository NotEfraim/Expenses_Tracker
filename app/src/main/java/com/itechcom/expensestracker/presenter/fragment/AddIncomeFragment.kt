package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddIncomeBinding
import com.itechcom.expensestracker.presenter.viewmodel.AddIncomeExpensesViewModel
import com.itechcom.expensestracker.utils.extensions.createSnackBar
import com.itechcom.expensestracker.utils.extensions.showDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddIncomeFragment : BaseFragment<FragmentAddIncomeBinding, AddIncomeExpensesViewModel>(
    FragmentAddIncomeBinding::inflate,
    AddIncomeExpensesViewModel::class) {

    private var planId : String? = null
    override fun FragmentAddIncomeBinding.initialize() {
        planId = arguments?.getString("plan_id")
        Log.d( "planHunter: ", "$planId")
        addIncome()
    }

    private fun addIncome() = binding.apply {

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
                    type = "income",
                    name = "${source.text}",
                    amount = amount.text.toString().toInt(),
                    stringDate = "${selectDate.text}",
                    description = "${description.text}"
                )
                val result = viewModel.addIncome(entity)
                if(result) {
                    delay(2000)
                    hideLoadingDialog()
                    this@AddIncomeFragment.findNavController().popBackStack()
                }
            }
        }
    }
}