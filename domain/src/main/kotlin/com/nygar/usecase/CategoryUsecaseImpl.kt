package com.nygar.usecase

import cleancode.repository.DataRepository
import com.nygar.model.CategoryModel
import com.nygar.model.mappers.MapperCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryUsecaseImpl
    @Inject
    constructor(
        private val repository: DataRepository,
    ) : CategoryUsecase {
        override suspend fun getCategoriesListUsecase(): Flow<Result<List<CategoryModel>>> =
            flow {
                emit(repository.categories().map { MapperCategory.transform(it) })
            }

        override suspend fun getCategoryUsecase(categoryId: Int): Flow<Result<CategoryModel>> =
            flow {
                emit(repository.category(categoryId).map { MapperCategory.transform(it) })
            }
    }
