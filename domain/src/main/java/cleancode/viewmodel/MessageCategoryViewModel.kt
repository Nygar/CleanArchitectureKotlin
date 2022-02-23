package cleancode.viewmodel
import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cleancode.base.RxAsync
import cleancode.model.CategoryModel
import cleancode.model.mappers.MapperCategory
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class MessageCategoryViewModel @Inject constructor(
    val repository: DataRepository
):ViewModel() {

    private var categoryList: MutableLiveData<List<CategoryModel>>? =null

    fun getMessageCategory(): MutableLiveData<List<CategoryModel>> {
        if (categoryList == null) {
            categoryList = MutableLiveData()
            getMessageCategoryTask()
        }
        return categoryList as MutableLiveData<List<CategoryModel>>
    }

    @SuppressLint("CheckResult")
    fun getMessageCategoryTask() {
        RxAsync.getAsync(repository.categories()).map { MapperCategory.transform(it) }.subscribeBy(
            onNext = { categoryList?.postValue(it)},
            onError =  { Throwable(it.message) },
            onComplete = {  })
    }

}