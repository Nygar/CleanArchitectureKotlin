package cleancode.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cleancode.base.RxAsync
import cleancode.model.MessageModel
import cleancode.model.mappers.MapperMessage
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.subscribeBy
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
        RxAsync.getAsync(repository.message(messageId)).map { MapperMessage.transform(it) }.subscribeBy(
            onNext = { messageSingle?.postValue(it)},
            onError =  { it.printStackTrace() },
            onComplete = { })
    }
} 