package cleancode.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nygar.entity.CategoryEntity

@Dao
interface CategoryCacheDao {
    @Insert
    fun insertSingle(entity: CategoryEntity)

    @Insert
    fun insertList(entityList: List<CategoryEntity>)

    @Query("SELECT * FROM CategoryEntity WHERE id = :id")
    fun getById(id: Int): CategoryEntity

    @Query("Delete from CategoryEntity where id=:id")
    fun getSingleRecordDelete(id: Int): Int

    @Delete
    fun delete(entity: CategoryEntity)
}
