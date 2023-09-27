package com.itechcom.expensestracker.presenter.fragment

import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.auth.FirebaseCallModel
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddPlanBinding
import com.itechcom.expensestracker.presenter.viewmodel.AddPlanViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.navigateTo
import com.itechcom.expensestracker.utils.extensions.showDatePicker
import com.itechcom.expensestracker.utils.extensions.toastUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddPlanFragment : BaseFragment<FragmentAddPlanBinding, AddPlanViewModel>(
    FragmentAddPlanBinding::inflate,
    AddPlanViewModel::class) {


    override fun AddPlanViewModel.initObserver() {
        collect(addPlanResponse, ::onAddPlanObserver)
    }

    override fun FragmentAddPlanBinding.initialize() {
        Log.d("fragmentState", "initialize: ")
        initDatePicker()
        planSaveFunctionality()
    }

    private fun initDatePicker() = binding.apply {
        startDate.setOnClickListener {
            (it as AppCompatTextView).showDatePicker()
        }

        endDate.setOnClickListener {
            (it as AppCompatTextView).showDatePicker()
        }
    }

    private fun planSaveFunctionality() = binding.apply {
        saveBtn.setOnClickListener {
            val planDate = "${startDate.text} - ${endDate.text}"
            val budget = "${budget.text}"
            val description = "${description.text}"

            if(planDate.isEmpty() || budget.isEmpty() || description.isEmpty()){
                requireActivity().toastUtil("All fields is required!")
                return@setOnClickListener
            }

            val planEntity = PlanEntity(
                stringDate = planDate,
                budget = budget,
                description = description
            )

            lifecycleScope.launch {
                showLoadingDialog()
                delay(1000)
                viewModel.addPlan(planEntity)
            }
        }

    }

    private fun onAddPlanObserver(firebaseCallModel: FirebaseCallModel){
        if(firebaseCallModel.isSuccess){
            requireActivity().toastUtil("Success")
            hideLoadingDialog()
            binding.saveBtn.navigateTo(R.id.action_addPlanFragment_to_homeFragment)
        }else{
            if(!firebaseCallModel.errorMessage.isNullOrEmpty())
                requireActivity().toastUtil("${firebaseCallModel.errorMessage}")
        }
    }


}