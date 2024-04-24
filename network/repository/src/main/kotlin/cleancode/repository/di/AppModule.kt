package cleancode.repository.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import cleancode.database.AppDatabase
import cleancode.net.RestApi
import cleancode.net.RestApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
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

    @Provides
    @Singleton
    fun provideApiRest(
        @ApplicationContext app: Context,
    ): RestApi = RestApiImpl(app)
}
