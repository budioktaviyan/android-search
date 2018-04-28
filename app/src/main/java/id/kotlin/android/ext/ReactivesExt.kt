package id.kotlin.android.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.DisposableSubscriber

internal fun CompositeDisposable?.disposed() { this?.clear() }
internal fun <T : Any> disposer(next: (T) -> Unit = {},
                                error: (Throwable) -> Unit = {},
                                complete: () -> Unit = {}): DisposableSubscriber<T> = object : DisposableSubscriber<T>() {
    override fun onNext(response: T) { next(response) }
    override fun onError(exception: Throwable?) { exception?.let(error) }
    override fun onComplete() { complete() }
}