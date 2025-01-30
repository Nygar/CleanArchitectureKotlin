package cleancode.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import cleancode.database.AppDatabase
import cleancode.database.api.CategoryCache
import cleancode.database.api.MessageCache
import cleancode.database.api.UserCache
import cleancode.database.api.UserLoggedCache
import cleancode.database.impl.CategoryCacheImpl
import cleancode.database.impl.MessageCacheImpl
import cleancode.database.impl.UserCacheImpl
import cleancode.database.impl.UserLoggedCacheImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModuleLocal {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context,
    ): AppDatabase {
        return Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .addCallback(
                object : RoomDatabase.Callback() {
                    // prepopulate(db)
                },
            )
            .build()
    }

    /*fun prepopulate(db: SupportSQLiteDatabase) {
       LocalizadorModel localizadorModel = new LocalizadorModel();
       localizadorModel.setId("f99OlTw2");
       db.insert("localizadores", OnConflictStrategy.IGNORE, localizadorModel.getContentValues());
   }*/

    // Database
    @Provides
    fun provideCategoryCache(
        @ApplicationContext app: Context,
        database: AppDatabase,
    ): CategoryCache = CategoryCacheImpl(app, database)

    @Provides
    fun provideMessageCache(
        @ApplicationContext app: Context,
        database: AppDatabase,
    ): MessageCache = MessageCacheImpl(app, database)

    @Provides
    fun provideUserCache(
        @ApplicationContext app: Context,
        database: AppDatabase,
    ): UserCache = UserCacheImpl(app, database)

    @Provides
    fun provideUserLoggedCache(
        @ApplicationContext app: Context,
        database: AppDatabase,
    ): UserLoggedCache = UserLoggedCacheImpl(app, database)
}
