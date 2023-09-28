package com.itechcom.expensestracker.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.viewbinding.ViewBinding
import com.itechcom.expensestracker.utils.extensions.createLoadingDialog
import kotlin.reflect.KClass

abstract class BaseFragment<VB: ViewBinding,VM: ViewModel> (
    private val bindingInflater: (inflater: LayoutInflater) -> VB,
    model: KClass<out VM>
):Fragment(){

    var isFirstCall = true
    private val loadingDialog by lazy { createLoadingDialog() }
    private var _binding: VB? = null
    val binding : VB  get() = _binding.let { binding ->
        when(binding){
            null->bindingInflater(layoutInflater)
            else->binding
        }
    }
    val viewModel by ViewModelLazy(
        model,
        { requireActivity().viewModelStore },
        {requireActivity().defaultViewModelProviderFactory },
        {requireActivity().defaultViewModelCreationExtras }
    )
    open fun VB.initialize(){}
    open fun VM.initObserver(){}
    open fun VM.initCall(){}

    fun showLoadingDialog(){
        loadingDialog.let {
            if(it.isShowing || requireActivity().isFinishing) return@let
            it.show()
        }
    }

    fun hideLoadingDialog(){
        if(requireActivity().isFinishing) return
        loadingDialog.hide()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initCall()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        binding.initialize()
        viewModel.initObserver()
        return binding.root
    }

}