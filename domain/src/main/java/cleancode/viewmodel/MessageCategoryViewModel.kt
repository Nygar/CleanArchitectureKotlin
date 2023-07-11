package cleancode.viewmodel
import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleancode.model.CategoryModel
import cleancode.model.mappers.MapperCategory
import cleancode.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.categories()
            }.fold(
                onSuccess = {
                    categoryList?.postValue(MapperCategory.transform(it))
                },
                onFailure = {
                    Throwable(it.message)
                }
            )
        }
    }

}