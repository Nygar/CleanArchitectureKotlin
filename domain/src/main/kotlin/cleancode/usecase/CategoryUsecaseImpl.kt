package cleancode.usecase

import cleancode.model.CategoryModel
import cleancode.model.mappers.MapperCategory
import cleancode.repository.DataRepository
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
