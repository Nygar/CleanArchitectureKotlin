package cleancode.repository.source

import com.nygar.entity.CategoryEntity
import com.nygar.entity.MessageEntity

interface CategoryDataStore {
    /**
     * Get an [Result] which will emit a List of [MessageEntity].
     */
    suspend fun categoryEntityList(): Result<List<CategoryEntity>>

    /**
     * Get an [Result] which will emit a [MessageEntity] by its id.
     *
     * @param categoryId The id to retrieve message data.
     */
    suspend fun categoryEntityDetails(categoryId: Int): Result<CategoryEntity>
}
