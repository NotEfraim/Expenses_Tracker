package com.itechcom.expensestracker.presenter.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.databinding.WidgetEditFieldBinding

class KEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyle : Int = 0
) : LinearLayout(context, attributeSet, defStyle) {

    private val binding = WidgetEditFieldBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(attributeSet, R.styleable.KEditText)
        val startIcon = a.getResourceId(R.styleable.KEditText_startIcon, R.mipmap.email_icon)
        val fieldHint = a.getString(R.styleable.KEditText_fieldHint)
        binding.let {
            it.startIcon.setImageResource(startIcon)
            it.fieldEditText.hint = fieldHint
        }
        a.recycle()
    }

    fun setFieldText(text : String){
        binding.fieldEditText.setText(text)
    }
    fun getFieldText() = binding.fieldEditText.text.toString()
}