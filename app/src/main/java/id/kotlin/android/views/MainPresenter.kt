package id.kotlin.android.views

import android.util.Log
import id.kotlin.android.data.NetworkServices
import id.kotlin.android.ext.clazz
import id.kotlin.android.ext.disposed
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(private val view: MainView,
                    private val services: NetworkServices) {

    private val composites by lazy { CompositeDisposable() }

    fun getTopSearch() {
        val disposer = services.getTopKeywords(
                { view.onFetchTopSearch(it.topKeywords) },
                { Log.e(clazz<MainPresenter>().simpleName, it.message) }
        )
        composites.add(disposer)
    }

    fun search(word: String) {
        val disposer = services.getOmniScience(word,
                { view.onSearchResult(it.products) },
                { Log.e(clazz<MainPresenter>().simpleName, it.message) }
        )
        composites.add(disposer)
    }

    fun disposed() {
        composites.disposed()
    }
}