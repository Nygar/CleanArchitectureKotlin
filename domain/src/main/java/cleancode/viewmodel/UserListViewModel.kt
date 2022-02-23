package cleancode.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cleancode.base.RxAsync
import cleancode.model.UserModel
import cleancode.model.mappers.MapperUser
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel(){

    private var userList: MutableLiveData<List<UserModel>>? =null

    fun getUserList(): MutableLiveData<List<UserModel>> {
        if (userList == null) {
            userList = MutableLiveData()
            getUserListTask()
        }
        return userList as MutableLiveData<List<UserModel>>
    }

    @SuppressLint("CheckResult")
    private fun getUserListTask() {
        RxAsync.getAsync(repository.users()).map { MapperUser.transform(it) }.subscribeBy(
            onNext = { userList?.postValue(it)},
            onError =  { it.printStackTrace() },
            onComplete = {  })
    }
}