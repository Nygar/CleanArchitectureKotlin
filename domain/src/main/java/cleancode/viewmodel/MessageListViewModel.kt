package cleancode.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cleancode.base.RxAsync
import cleancode.model.MessageModel
import cleancode.model.mappers.MapperMessage
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
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
        RxAsync.getAsync(repository.messages()).map { MapperMessage.transform(it) }.subscribeBy(
            onNext = { messageList?.postValue(it)},
            onError =  { it.printStackTrace() },
            onComplete = { })
    }
}