package com.itechcom.expensestracker.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

abstract class BaseActivity<VB: ViewBinding, VM: ViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB,
    model: KClass<out VM>
) : AppCompatActivity(){

    val binding by lazy { bindingInflater(layoutInflater) }
    val viewModel by ViewModelLazy(
        model,
        { this.viewModelStore },
        {this.defaultViewModelProviderFactory },
        {this.defaultViewModelCreationExtras }
    )

    open fun VB.initialize(){}
    open fun VM.initCall(){}
    open fun VM.initObserver(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.initialize()
        viewModel.initObserver()
        viewModel.initCall()
        setContentView(binding.root)
    }

}