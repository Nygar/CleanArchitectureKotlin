package cleancode.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleancode.model.MessageModel
import cleancode.model.mappers.MapperMessage
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MessageListViewModel @Inject constructor(
    private val repository: DataRepository
):ViewModel(){

    private var messageList: MutableLiveData<List<MessageModel>>? =null
    var categoryId: Int = 0

    fun getMessageList(categoryId: Int): MutableLiveData<List<MessageModel>> {
        if (messageList == null) {
            messageList = MutableLiveData()
            getMessageListTask(categoryId)
        }
        return messageList as MutableLiveData<List<MessageModel>>
    }

    @SuppressLint("CheckResult")
    private fun getMessageListTask(categoryId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.messages()
            }.fold(
                onSuccess = {
                    messageList?.postValue(MapperMessage.transform(it))
                },
                onFailure = {
                    Throwable(it.message)
                }
            )
        }
    }
}