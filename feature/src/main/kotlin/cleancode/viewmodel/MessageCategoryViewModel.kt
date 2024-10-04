package cleancode.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleancode.model.CategoryModel
import cleancode.usecase.CategoryUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageCategoryViewModel
    @Inject
    constructor(
        private val usecase: CategoryUsecase,
    ) : ViewModel() {
        var categoryList by mutableStateOf<List<CategoryModel>>(arrayListOf())

        private val _categoryListResult =
            MutableSharedFlow<CategoryResult>(
                replay = 1,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )
        val categoryListResult: SharedFlow<CategoryResult> = _categoryListResult

        init {
            getMessageCategory()
        }

        private fun getMessageCategory() {
            viewModelScope.launch {
                _categoryListResult.emit(CategoryResult.Loading)
                usecase.getCategoriesListUsecase().collect { result ->
                    result.onSuccess {
                        categoryList = it
                        _categoryListResult.emit(CategoryResult.Success)
                    }
                    result.onFailure {
                        _categoryListResult.emit(CategoryResult.Error)
                    }
                }
            }
        }
    }

sealed interface CategoryResult {
    data object Loading : CategoryResult

    data object Success : CategoryResult

    data object Error : CategoryResult
}
