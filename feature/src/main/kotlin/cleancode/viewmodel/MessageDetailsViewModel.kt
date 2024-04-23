package cleancode.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleancode.model.MessageModel
import cleancode.usecase.MessageUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageDetailsViewModel @Inject constructor(
    private val usecase: MessageUsecase
): ViewModel(){

    var messageSingle by mutableStateOf<MessageModel?>(null)

    private val _messageSingleResult = MutableSharedFlow<MessageResult>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val messageSingleResult: SharedFlow<MessageResult> = _messageSingleResult

    fun getMessageById(messageId:Int) {
        viewModelScope.launch {
            _messageSingleResult.emit(MessageResult.Loading)
            usecase.getMesageUsecase(messageId).collect{ result ->
                result.onSuccess {
                    messageSingle = it
                    _messageSingleResult.emit(MessageResult.Success)
                }
                result.onFailure {
                    _messageSingleResult.emit(MessageResult.Error)
                }
            }
        }
    }
}

sealed interface MessageResult {
    data object Loading : MessageResult
    data object Success : MessageResult
    data object Error : MessageResult
}