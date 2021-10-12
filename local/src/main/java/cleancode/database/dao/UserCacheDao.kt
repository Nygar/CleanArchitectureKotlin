package cleancode.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cleancode.entity.UserEntity

@Dao
interface UserCacheDao{
    @Insert
    fun insertSingle(entity: UserEntity)

    @Insert
    fun insertList(entityList: List<UserEntity>)

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getById(id: Int): UserEntity

    @Query("Delete from UserEntity where id=:id")
    fun getSingleRecordDelete(id: Int): Int

    @Delete
    fun delete(entity: UserEntity)
}