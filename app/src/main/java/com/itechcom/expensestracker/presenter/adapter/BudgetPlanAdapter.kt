package com.itechcom.expensestracker.presenter.adapter

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.data.room.PlanEntity

class BudgetPlanAdapter : BaseQuickAdapter<PlanEntity, QuickViewHolder>() {

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: PlanEntity?) {

    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_plan, parent)
    }

    override fun getItemCount(items: List<PlanEntity>): Int {
        return 6
    }
}