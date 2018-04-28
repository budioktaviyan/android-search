package id.kotlin.android.views

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.SearchView.OnQueryTextListener
import android.view.Menu
import android.view.MenuItem
import android.view.View
import dagger.android.AndroidInjection
import id.kotlin.android.R
import id.kotlin.android.data.NetworkServices
import id.kotlin.android.data.Product
import id.kotlin.android.ext.getDrawableResource
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject lateinit var services: NetworkServices

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = getDrawableResource(R.drawable.bg_arrow_back)

        presenter = MainPresenter(this, services)
        presenter.getTopSearch()

        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        presenter.disposed()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchMenuItem = menu?.findItem(R.id.action_search)
        val searchManager = this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as? SearchManager
        val searchView = searchMenuItem?.actionView as? SearchView
        searchView?.setSearchableInfo(searchManager?.getSearchableInfo(this@MainActivity.componentName))
        searchView?.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                query?.let { presenter.search(it) }
                return true
            }

            override fun onQueryTextChange(result: String?): Boolean = false
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFetchTopSearch(keywords: List<String>) {
        rv_top_search.adapter = MainTopSearch(keywords)
    }

    override fun onSearchResult(products: List<Product>) {
        rv_search.adapter = MainSearch(products)
        title_search.visibility = View.VISIBLE
        rv_search.visibility = View.VISIBLE
    }
}