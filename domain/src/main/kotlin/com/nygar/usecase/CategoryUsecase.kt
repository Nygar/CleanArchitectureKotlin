package com.nygar.usecase

import com.nygar.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryUsecase {
    suspend fun getCategoriesListUsecase(): Flow<Result<List<CategoryModel>>>

    suspend fun getCategoryUsecase(categoryId: Int): Flow<Result<CategoryModel>>
}
