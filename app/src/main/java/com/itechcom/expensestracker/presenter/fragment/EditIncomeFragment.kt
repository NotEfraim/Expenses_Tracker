package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddIncomeBinding
import com.itechcom.expensestracker.databinding.FragmentEditIncomeBinding
import com.itechcom.expensestracker.presenter.viewmodel.IncomeExpensesViewModel
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel
import com.itechcom.expensestracker.utils.extensions.createSnackBar
import com.itechcom.expensestracker.utils.extensions.showDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.Serializable

@AndroidEntryPoint
class EditIncomeFragment : BaseFragment<FragmentEditIncomeBinding, IncomeExpensesViewModel>(
    FragmentEditIncomeBinding::inflate,
    IncomeExpensesViewModel::class) {

    private var planId : String? = null
    private var planModel : PlanEntity? = null
    private var incomeExpensesModel : IncomeExpensesEntity? = null

    override fun FragmentEditIncomeBinding.initialize() {
        getBundles()
        Log.d( "planHunter: ", "$planId $planModel")
        initView()
    }

    private fun getBundles(){
        planId = arguments?.getString("plan_id")
        planModel = arguments?.getSerializable("plan_model") as PlanEntity
        incomeExpensesModel = arguments?.getSerializable("incomeExpenses_model") as IncomeExpensesEntity
    }

    private fun initView() = binding.apply {

        source.setText("${incomeExpensesModel?.name}")
        amount.setText("${incomeExpensesModel?.amount}")
        selectDate.text = "${incomeExpensesModel?.stringDate}"
        description.setText("${incomeExpensesModel?.description}")

        selectDate.setOnClickListener {
            selectDate.showDatePicker()
        }

        saveBtn.setOnClickListener {
            showLoadingDialog()
            lifecycleScope.launch {
                val payer = source.text.toString()
                val amount = amount.text.toString()
                val date = selectDate.text.toString()
                val desc = description.text.toString()

                val updateModel = IncomeExpensesEntity(
                    planId = planId,
                    type = "income",
                    name = payer,
                    amount = amount.toInt(),
                    stringDate = date,
                    description = desc
                )
                val response = viewModel.updateIncomeExpenses(planId?:return@launch, updateModel)
                delay(1000)
                hideLoadingDialog()
                if(response) this@EditIncomeFragment.findNavController().popBackStack()
                else requireActivity().createSnackBar("Unknown Error.", {})
            }

        }
    }
}