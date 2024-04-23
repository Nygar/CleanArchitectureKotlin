package cleancode.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cleancode.entity.MessageEntity

@Dao
interface MessageCacheDao{
    @Insert
    fun insertSingle(entity: MessageEntity)

    @Insert
    fun insertList(entityList: List<MessageEntity>)

    @Query("SELECT * FROM MessageEntity WHERE id = :id")
    fun getById(id: Int): MessageEntity

    @Query("SELECT * FROM MessageEntity")
    fun getList(): List<MessageEntity>

    @Query("Delete from MessageEntity where id=:id")
    fun getSingleRecordDelete(id: Int): Int

    @Query("Delete from MessageEntity")
    fun deleteAll()

    @Delete
    fun delete(entity: MessageEntity)
}