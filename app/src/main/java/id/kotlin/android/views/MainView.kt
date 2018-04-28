package id.kotlin.android.views

import id.kotlin.android.data.Product

interface MainView {

    fun onFetchTopSearch(keywords: List<String>)
    fun onSearchResult(products: List<Product>)
}