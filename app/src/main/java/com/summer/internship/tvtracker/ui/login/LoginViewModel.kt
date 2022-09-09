package com.summer.internship.tvtracker.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.data.TvDetailsResponse
import com.summer.internship.tvtracker.di.SignInRepositoryFactoryIMPL
import com.summer.internship.tvtracker.domain.login.SignInListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel : ViewModel() {
    private val signInRepository = SignInRepositoryFactoryIMPL.createSignInRepository()
    private val compositeDisposable = CompositeDisposable()
    private val _isLoginSuccessful: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val isLoginSuccessful :LiveData<Boolean> by lazy {
        _isLoginSuccessful
    }

    val isLoginFailed :LiveData<String> by lazy {
        _isLoginFailed
    }
    private val _isLoginFailed: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun isValid(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) {
            return false
        }
        return true
    }

    fun signIn(email: String, password: String) {
        val disposable = signInRepository.signIn(email, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _isLoginSuccessful.postValue(true)
            }, {
                _isLoginFailed.postValue(it.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}