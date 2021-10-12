package cleancode.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cleancode.base.RxAsync
import cleancode.model.UserModel
import cleancode.model.mappers.MapperUser
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {

    private var userSingle: MutableLiveData<UserModel>? = null

    fun getUserById(userId: Int): MutableLiveData<UserModel> {
        if (userSingle == null) {
            userSingle = MutableLiveData()
            getUserTaskById(userId)
        }
        return userSingle as MutableLiveData<UserModel>
    }

    @SuppressLint("CheckResult")
    private fun getUserTaskById(messageId: Int) {
        RxAsync.getAsync(repository.user(messageId)).map { MapperUser.transform(it) }.subscribeBy(
            onNext = { userSingle?.postValue(it) },
            onError = { it.printStackTrace() },
            onComplete = {  })
    }
}