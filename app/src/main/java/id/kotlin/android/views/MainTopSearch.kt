package id.kotlin.android.views

import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import id.kotlin.android.R
import id.kotlin.android.ext.inflate
import id.kotlin.android.views.MainTopSearch.MainTopSearchView
import kotlinx.android.synthetic.main.item_top_search.view.*

class MainTopSearch(private val models: List<String>) : Adapter<MainTopSearchView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTopSearchView = MainTopSearchView(parent.inflate(parent.context, R.layout.item_top_search, false))

    override fun onBindViewHolder(holder: MainTopSearchView, position: Int) {
        holder.bindView(models[position])
    }

    override fun getItemCount(): Int = models.size

    inner class MainTopSearchView(itemView: View) : ViewHolder(itemView) {

        fun bindView(model: String) {
            with(model) {
                itemView.tv_top_search.text = model
            }
        }
    }
}