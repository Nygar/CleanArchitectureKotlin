package cleancode.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cleancode.base.RxAsync
import cleancode.model.UserLoggedModel
import cleancode.model.mappers.MapperUserLogged
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class UserLoggedViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel(){

    private var userLogged: MutableLiveData<UserLoggedModel>? =null

    fun getUserLogged(): MutableLiveData<UserLoggedModel> {
        if (userLogged == null) {
            userLogged = MutableLiveData()
            getUserLoggedTask()
        }
        return userLogged as MutableLiveData<UserLoggedModel>
    }

    @SuppressLint("CheckResult")
    private fun getUserLoggedTask() {
        RxAsync.getAsync(repository.userLogged()).map { MapperUserLogged.transform(it) }.subscribeBy(
            onNext = { userLogged?.postValue(it)},
            onError =  { it.printStackTrace() },
            onComplete = { })
    }
}