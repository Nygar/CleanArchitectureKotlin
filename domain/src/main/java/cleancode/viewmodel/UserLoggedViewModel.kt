package cleancode.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleancode.model.UserLoggedModel
import cleancode.model.mappers.MapperUserLogged
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.userLogged()
            }.fold(
                onSuccess = {
                    userLogged?.postValue(MapperUserLogged.transform(it))
                },
                onFailure = {
                    Throwable(it.message)
                }
            )
        }
    }
}