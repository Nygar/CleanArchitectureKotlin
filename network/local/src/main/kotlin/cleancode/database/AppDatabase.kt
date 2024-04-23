package cleancode.database


import androidx.room.Database
import androidx.room.RoomDatabase
import cleancode.database.dao.CategoryCacheDao
import cleancode.database.dao.MessageCacheDao
import cleancode.database.dao.UserCacheDao
import cleancode.database.dao.UserLoggedCacheDao
import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.entity.UserEntity
import cleancode.entity.UserLoggedEntity

@Database(
    entities = [UserEntity::class, CategoryEntity::class, UserLoggedEntity::class, MessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryCacheDao(): CategoryCacheDao
    abstract fun messageCacheDao(): MessageCacheDao
    abstract fun userCacheDao(): UserCacheDao
    abstract fun userLoggedCacheDao(): UserLoggedCacheDao

}