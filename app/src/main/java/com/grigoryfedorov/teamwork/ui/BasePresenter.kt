package com.grigoryfedorov.teamwork.ui

import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter : Presenter {

    companion object {
        const val SUBSCRIBE_ON_SCHEDULER = "subscribeOnScheduler"
        const val OBSERVE_ON_SCHEDULER = "observeOnScheduler"
    }

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onStart() {
        // empty by default
    }

    override fun onStop() {
        compositeDisposable.clear()
    }

    open inner class BaseSubscriber<T> : Observer<T> {
        override fun onSubscribe(disposable: Disposable) {
            compositeDisposable.add(disposable)
        }

        override fun onNext(t: T) {
            // empty by default
        }

        override fun onError(error: Throwable) {
            // empty by default
        }

        override fun onComplete() {
            // empty by default
        }

    }
}