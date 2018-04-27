package it.giangraziano.moovelcodingchallenge.extensions

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

fun RecyclerView.setColumnsLayout(ctx: Context, isStaggered: Boolean) {
    layoutManager = if (isStaggered) {
        val displayMetrics = ctx.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val nColumns = (dpWidth / 180).toInt()
        StaggeredGridLayoutManager(nColumns, StaggeredGridLayoutManager.VERTICAL)
    } else {
        LinearLayoutManager(ctx)
    }
}

fun RecyclerView.onScrollToEnd(isStaggered: Boolean, whenScrollCloseToEnd: (Unit) -> Unit) =
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                val childValues = if (isStaggered) {
                    val manager = layoutManager as StaggeredGridLayoutManager
                    val firstVisibleItemsPos = manager.findFirstVisibleItemPositions(null)
                            .max() ?: 0
                    manager.childCount + firstVisibleItemsPos
                } else {
                    val manager = layoutManager as LinearLayoutManager
                    val firstVisibleItemPos = manager.findFirstCompletelyVisibleItemPosition()
                    manager.childCount + firstVisibleItemPos
                }

                if (childValues >= layoutManager.itemCount) {
                    whenScrollCloseToEnd(kotlin.Unit)
                }
            }
        })
