package cleancode.usecase

import cleancode.entity.CategoryEntity
import cleancode.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryUsecase {

    suspend fun getCategoriesListUsecase(): Flow<Result<List<CategoryModel>>>

    suspend fun getCategoryUsecase(categoryId: Int): Flow<Result<CategoryModel>>
}