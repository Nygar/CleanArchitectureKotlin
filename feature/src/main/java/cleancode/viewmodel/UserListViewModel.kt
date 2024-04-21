package cleancode.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleancode.model.UserModel
import cleancode.usecase.UserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val usecase: UserUsecase
): ViewModel(){

    var userList by mutableStateOf<List<UserModel>>(arrayListOf())

    private val _userListResult = MutableSharedFlow<UserListResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val userListResult: SharedFlow<UserListResult> = _userListResult

    private fun getUserList() {
        viewModelScope.launch {
            _userListResult.emit(UserListResult.Loading)
            usecase.getUserListUsecase().collect{ result ->
                result.onSuccess {
                    _userListResult.emit(UserListResult.Success)
                }
                result.onFailure {
                    _userListResult.emit(UserListResult.Error)
                }
            }
        }
    }
}

sealed interface UserListResult {
    data object Loading : UserListResult
    data object Success : UserListResult
    data object Error : UserListResult
}