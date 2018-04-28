package id.kotlin.android.data

import id.kotlin.android.ext.Configs
import id.kotlin.android.ext.disposer
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("/v2/top_keywords/index.json")
    fun topkeywords(@Query("user") user: String = Configs.DEFAULT_USER,
                    @Query("key") key: String = Configs.DEFAULT_KEY): Flowable<TopKeywords>

    @GET("/v2/omniscience/v2.json")
    fun omniscience(@Query("user") user: String = Configs.DEFAULT_USER,
                    @Query("key") key: String = Configs.DEFAULT_KEY,
                    @Query("word") word: String): Flowable<OmniScience>
}

class NetworkServices(private val service: NetworkService) {

    fun getTopKeywords(response: (TopKeywords) -> Unit,
                       exception: (Throwable) -> Unit): Disposable {
        return service.topkeywords()
                .compose(NetworkTransformer<TopKeywords>())
                .onErrorResumeNext(Function { Flowable.error(it) })
                .subscribeWith(disposer<TopKeywords>(
                        next = { onSuccess -> response(onSuccess) },
                        error = { onError -> exception(onError) }
                ))
    }

    fun getOmniScience(word: String,
                       response: (OmniScience) -> Unit,
                       exception: (Throwable) -> Unit): Disposable {
        return service.omniscience(word = word)
                .compose(NetworkTransformer<OmniScience>())
                .onErrorResumeNext(Function { Flowable.error(it) })
                .subscribeWith(disposer<OmniScience>(
                        next = { onSuccess -> response(onSuccess) },
                        error = { onError -> exception(onError) }
                ))
    }
}