package com.adil.iplknockout.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class GridItemDecorator (
    private var spacing: Int,
    ) : RecyclerView.ItemDecoration()
{

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    )
    {

        outRect.bottom = (spacing * 0.65).toInt()

        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.left = spacing
            outRect.right = spacing / 3
        } else {
            outRect.left = spacing / 3
            outRect.right = spacing
        }

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
            outRect.top = spacing
        } else {
            outRect.top = 0;
        }

    }
}