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
class UserDetailsViewModel @Inject constructor(
    private val usecase: UserUsecase
): ViewModel() {

    var userSingle by mutableStateOf<UserModel?>(null)

    private val _userSingleResult = MutableSharedFlow<UserDetailsResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val userSingleResult: SharedFlow<UserDetailsResult> = _userSingleResult

    fun getUserById(userId: Int) {
        viewModelScope.launch {
            _userSingleResult.emit(UserDetailsResult.Loading)
            usecase.getUserUsecase(userId).collect{ result ->
                result.onSuccess {
                    userSingle = it
                    _userSingleResult.emit(UserDetailsResult.Success)
                }
                result.onFailure {
                    _userSingleResult.emit(UserDetailsResult.Error)
                }
            }
        }
    }
}

sealed interface UserDetailsResult {
    data object Loading : UserDetailsResult
    data object Success : UserDetailsResult
    data object Error : UserDetailsResult
}