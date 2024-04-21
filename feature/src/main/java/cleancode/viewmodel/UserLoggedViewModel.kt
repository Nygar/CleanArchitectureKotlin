package cleancode.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleancode.model.UserLoggedModel
import cleancode.usecase.UserLoggedUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLoggedViewModel @Inject constructor(
    private val usecase: UserLoggedUsecase
): ViewModel(){

    var userLoggedSingle by mutableStateOf<UserLoggedModel?>(null)

    private val _userLoggedSingleResult = MutableSharedFlow<UserLoggedResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val userLoggedSingleResult: SharedFlow<UserLoggedResult> = _userLoggedSingleResult

    init {
        getUserLogged()
    }

    private fun getUserLogged() {
        viewModelScope.launch {
            _userLoggedSingleResult.emit(UserLoggedResult.Loading)
            usecase.getUserLoggedUsecase().collect{ result ->
                result.onSuccess {
                    userLoggedSingle = it
                    _userLoggedSingleResult.emit(UserLoggedResult.Success)
                }
                result.onFailure {
                    _userLoggedSingleResult.emit(UserLoggedResult.Error)
                }
            }
        }
    }
}

sealed interface UserLoggedResult {
    data object Loading : UserLoggedResult
    data object Success : UserLoggedResult
    data object Error : UserLoggedResult
}