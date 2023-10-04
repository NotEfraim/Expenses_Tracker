package com.itechcom.expensestracker.presenter.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.databinding.FragmentEditPlanBinding
import com.itechcom.expensestracker.presenter.viewmodel.EditPlanViewModel
import com.itechcom.expensestracker.utils.extensions.showDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditPlanFragment : BaseFragment<FragmentEditPlanBinding, EditPlanViewModel>(
    FragmentEditPlanBinding::inflate,
    EditPlanViewModel::class) {

    private var planId : String? = null
    private var planModel : PlanEntity? = null

    override fun FragmentEditPlanBinding.initialize() {
        getBundles()
        initViews()
    }

    private fun getBundles(){
        planId = arguments?.getString("plan_id")
        planModel = arguments?.getSerializable("plan_model") as PlanEntity
    }

    private fun initViews() = binding.apply {

        planModel?.let {
            val date = it.stringDate?.split("-")
            startDate.text = date?.get(0)
            endDate.text = date?.get(1)
            budget.setText(it.budget)
            description.setText(it.description)
        }

        startDate.setOnClickListener {
            startDate.showDatePicker()
        }

        endDate.setOnClickListener {
            endDate.showDatePicker()
        }

        saveBtn.setOnClickListener {
            lifecycleScope.launch {
                showLoadingDialog()
                val updateData = PlanEntity(
                    userName = planModel?.userName,
                    stringDate = "${startDate.text} - ${endDate.text}",
                    budget = "${budget.text}",
                    totalIncome = planModel?.totalIncome,
                    totalExpenses = planModel?.totalExpenses,
                    description = "${description.text}"
                )
                val response = viewModel.updatePlan(planId?:return@launch, updateData )
                delay(1000)
                hideLoadingDialog()
                if(response) this@EditPlanFragment.findNavController().popBackStack()
            }
        }
    }
}