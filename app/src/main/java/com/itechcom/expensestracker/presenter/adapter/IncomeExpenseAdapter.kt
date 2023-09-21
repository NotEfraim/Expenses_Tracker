package com.itechcom.expensestracker.presenter.adapter

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.data.room.IncomeEntity

class IncomeExpenseAdapter : BaseQuickAdapter<IncomeEntity, QuickViewHolder>() {
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: IncomeEntity?) {
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_income_expenses, parent)
    }

    override fun getItemCount(items: List<IncomeEntity>): Int {
        return 5
    }
}