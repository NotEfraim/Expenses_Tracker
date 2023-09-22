package com.itechcom.expensestracker.presenter.fragment

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseFragment
import com.itechcom.expensestracker.data.firebase.SignInResults
import com.itechcom.expensestracker.data.room.PlanEntity
import com.itechcom.expensestracker.databinding.FragmentHomeBinding
import com.itechcom.expensestracker.presenter.SingleViewModel
import com.itechcom.expensestracker.presenter.adapter.BudgetPlanAdapter
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.navigateTo
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding, SingleViewModel>(
    FragmentHomeBinding::inflate,
    SingleViewModel::class
) {

    private val budgetPlanAdapter = BudgetPlanAdapter()

        override fun FragmentHomeBinding.initialize() {
        planRecycler.adapter = budgetPlanAdapter
        moreBtn.setOnClickListener { it.navigateTo(R.id.action_toAllPlans) }
        budgetPlanAdapter.setOnItemClickListener { _, view, _ ->
            view.navigateTo(R.id.actionToViewPlanFragment)
        }


    }

}