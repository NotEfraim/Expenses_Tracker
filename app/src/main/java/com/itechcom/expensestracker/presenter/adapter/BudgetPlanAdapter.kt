package com.itechcom.expensestracker.presenter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.model.database.PlanEntityList
import com.itechcom.expensestracker.R

@SuppressLint("SetTextI18n")
class BudgetPlanAdapter : BaseQuickAdapter<PlanEntity, QuickViewHolder>() {

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: PlanEntity?) {
        item?.apply {
            val expensesInt = totalExpenses?.toInt()?:0
            val budgetInt = budget?.toInt()?:0

            holder.getView<TextView>(R.id.planDate).text = stringDate
            holder.getView<TextView>(R.id.costText).text = "₱$expensesInt.00 of ₱$budgetInt.00"
            holder.getView<TextView>(R.id.remainingText).text = "₱${(budgetInt - expensesInt)}.00"

            holder.getView<ProgressBar>(R.id.planProgress).apply {
                max = budgetInt
                progress = expensesInt
            }
        }
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_plan, parent)
    }
}