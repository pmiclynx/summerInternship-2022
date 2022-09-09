package com.summer.internship.tvtracker.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.summer.internship.tvtracker.di.CreateAccRepositoryFactoryIMPL
import com.summer.internship.tvtracker.domain.register.CreateAccountListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RegisterViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _isLoginSuccessful: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val isLoginSuccessful: LiveData<Boolean> by lazy {
        _isLoginSuccessful
    }

    val isLoginFailed: LiveData<String> by lazy {
        _isLoginFailed
    }
    private val _isLoginFailed: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val createAccountRepository = CreateAccRepositoryFactoryIMPL.createAccRepository()

    fun isOk(email: String, password: String, name: String, verifyPass: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || verifyPass.isEmpty()) {
            return false
        }
        if (!password.equals(verifyPass)) {
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) {
            return false
        }
        if (password.length < 6) {
            return false
        }
        return true
    }

    fun createAccount(email: String, password: String) {
        val disposable = createAccountRepository.createAccount(email, password)
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