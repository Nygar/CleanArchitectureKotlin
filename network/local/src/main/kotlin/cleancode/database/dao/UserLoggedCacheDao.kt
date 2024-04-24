package cleancode.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cleancode.entity.UserLoggedEntity

@Dao
interface UserLoggedCacheDao {
    @Insert
    fun insertSingle(entity: UserLoggedEntity)

    @Insert
    fun insertList(entityList: List<UserLoggedEntity>)

    @Query("SELECT * FROM UserLoggedEntity")
    fun getById(): UserLoggedEntity

    @Query("Delete from UserLoggedEntity")
    fun getSingleRecordDelete(): Int

    @Delete
    fun delete(entity: UserLoggedEntity)
}
