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
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.users()
            }.fold(
                onSuccess = {
                    userList?.postValue(MapperUser.transform(it))
                },
                onFailure = {
                    Throwable(it.message)
                }
            )
        }
    }
}