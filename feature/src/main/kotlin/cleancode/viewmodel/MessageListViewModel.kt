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
class MessageListViewModel
    @Inject
    constructor(
        private val usecase: MessageUsecase,
    ) : ViewModel() {
        var messageList by mutableStateOf<List<MessageModel>>(arrayListOf())

        private val _messageListResult =
            MutableSharedFlow<MessageListResult>(
                replay = 1,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )
        val messageListResult: SharedFlow<MessageListResult> = _messageListResult

        fun getMessageCategory(categoryId: Int) {
            viewModelScope.launch {
                _messageListResult.emit(MessageListResult.Loading)
                usecase.getMessageListUsecase(categoryId).collect { result ->
                    result.onSuccess {
                        messageList = it
                        _messageListResult.emit(MessageListResult.Success)
                    }
                    result.onFailure {
                        _messageListResult.emit(MessageListResult.Error)
                    }
                }
            }
        }
    }

sealed interface MessageListResult {
    data object Loading : MessageListResult

    data object Success : MessageListResult

    data object Error : MessageListResult
}
