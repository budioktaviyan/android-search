package id.kotlin.android.views

import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import id.kotlin.android.R
import id.kotlin.android.data.Product
import id.kotlin.android.ext.inflate
import id.kotlin.android.ext.load
import id.kotlin.android.views.MainSearch.MainSearchView
import kotlinx.android.synthetic.main.item_search.view.*

class MainSearch(private val models: List<Product>) : Adapter<MainSearchView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainSearchView = MainSearchView(parent.inflate(parent.context, R.layout.item_search, false))

    override fun onBindViewHolder(holder: MainSearchView, position: Int) {
        holder.bindView(models[position])
    }

    override fun getItemCount(): Int = models.size

    inner class MainSearchView(itemView: View) : ViewHolder(itemView) {

        fun bindView(model: Product) {
            with(model) {
                val price = "Rp ${model.price}"
                itemView.iv_search.load(model.img)
                itemView.tv_search_name.text = model.name
                itemView.tv_search_price.text = price
            }
        }
    }
}