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
class MessageDetailsViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel(){

    private var messageSingle: MutableLiveData<MessageModel>? =null

    fun getMessageById(messageId: Int): MutableLiveData<MessageModel> {
        if (messageSingle == null) {
            messageSingle = MutableLiveData()
        }
        getMessageTaskById(messageId)
        return messageSingle as MutableLiveData<MessageModel>
    }

    @SuppressLint("CheckResult")
    private fun getMessageTaskById(messageId:Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.message(messageId)
            }.fold(
                onSuccess = {
                    messageSingle?.postValue(MapperMessage.transform(it))
                },
                onFailure = {
                    Throwable(it.message)
                }
            )
        }
    }
} 