package com.itechcom.expensestracker.presenter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.expensestracker.R

class IncomeExpenseAdapter : BaseQuickAdapter<IncomeExpensesEntity, QuickViewHolder>() {

    private var totalIncome = 0
    private var totalExpenses = 0

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: QuickViewHolder,
        position: Int,
        item: IncomeExpensesEntity?
    ) {

        holder.getView<TextView>(R.id.itemName).text = item?.name

        if(item?.type == "income"){
            holder.getView<ImageView>(R.id.startIcon).setImageResource(R.mipmap.income_circle_icon)
            holder.getView<TextView>(R.id.itemAmount).text = "+₱${item.amount}.00"
        }
        else{
            holder.getView<ImageView>(R.id.startIcon).setImageResource(R.mipmap.expenses_circle_icon)
            holder.getView<TextView>(R.id.itemAmount).text = "-₱${item?.amount}.00"
        }

    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_income_expenses, parent)
    }
}