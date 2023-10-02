package com.itechcom.expensestracker.presenter.fragment

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.itechcom.domain.model.auth.FirebaseCallModel
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentAddPlanBinding
import com.itechcom.expensestracker.presenter.viewmodel.AddPlanViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.createSnackBar
import com.itechcom.expensestracker.utils.extensions.showDatePicker
import com.itechcom.expensestracker.utils.extensions.toastUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AddPlanFragment : BaseFragment<FragmentAddPlanBinding, AddPlanViewModel>(
    FragmentAddPlanBinding::inflate,
    AddPlanViewModel::class) {

    private var minDate : Long? = null
    override fun FragmentAddPlanBinding.initialize() {
        Log.d("fragmentState", "initialize: ")
        initDatePicker()
        planSaveFunctionality()
    }

    private fun initDatePicker() = binding.apply {
        startDate.setOnClickListener {
            showStartDatePicker()
        }
        endDate.setOnClickListener {
            if(startDate.text.isNullOrEmpty()){
                requireActivity().createSnackBar("Enter Start Date First") {
                   showStartDatePicker()
                }
            }else{
                (it as AppCompatTextView).showDatePicker(minDate)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun showStartDatePicker() {
        binding. startDate.showDatePicker{
                y, m, d ->
            val startDate = SimpleDateFormat("yyyy-MM-dd")
                .parse("$y-${m + 1}-$d")
            val calendar = Calendar.getInstance()
            calendar.time = startDate
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            minDate = calendar.timeInMillis
        }
    }

    private fun planSaveFunctionality() = binding.apply {
        saveBtn.setOnClickListener {
            val planDate = "${startDate.text} - ${endDate.text}"
            val budget = "${budget.text}"
            val description = "${description.text}"

            if(planDate.isEmpty() || budget.isEmpty()){
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
                collect(viewModel.addPlanResponse, ::onAddPlanObserver)
            }
        }

    }

    private fun onAddPlanObserver(firebaseCallModel: FirebaseCallModel){
        if(firebaseCallModel.isSuccess){
            requireActivity().toastUtil("Success")
            hideLoadingDialog()
            findNavController().popBackStack()
        }else{
            if(!firebaseCallModel.errorMessage.isNullOrEmpty())
                requireActivity().toastUtil("${firebaseCallModel.errorMessage}")
        }
    }


}