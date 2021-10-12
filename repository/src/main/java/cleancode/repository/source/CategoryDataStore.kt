package cleancode.repository.source

import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import io.reactivex.Observable


interface CategoryDataStore {
    /**
     * Get an [Observable] which will emit a List of [MessageEntity].
     */
    fun categoryEntityList(): Observable<List<CategoryEntity>>

    /**
     * Get an [Observable] which will emit a [MessageEntity] by its id.
     *
     * @param categoryId The id to retrieve message data.
     */
    fun categoryEntityDetails(categoryId: Int): Observable<CategoryEntity>
}