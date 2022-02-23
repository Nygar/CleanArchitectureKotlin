package cleancode.base

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


object RxAsync {
    fun <T> getAsync(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> getTest(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}