package cleancode.di.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context):SharedPreferences{
        return context.getSharedPreferences("APP_SHARED_PREFERENCES", MODE_PRIVATE)
    }
}