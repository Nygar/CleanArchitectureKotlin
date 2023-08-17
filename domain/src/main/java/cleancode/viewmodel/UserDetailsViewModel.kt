package cleancode.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleancode.model.UserModel
import cleancode.model.mappers.MapperUser
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {

    var userLivedata: MutableLiveData<UserModel> = MutableLiveData()

    fun getUserById(userId: Int) {
        if (userLivedata.value == null) {
            getUserTaskById(userId)
        }
    }

    @SuppressLint("CheckResult")
    private fun getUserTaskById(messageId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.user(messageId)
            }.fold(
                onSuccess = {
                    userLivedata.postValue(MapperUser.transform(it))
                },
                onFailure = {
                    Throwable(it.message)
                }
            )
        }
    }
}