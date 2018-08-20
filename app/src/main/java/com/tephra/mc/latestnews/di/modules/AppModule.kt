package com.tephra.mc.latestnews.di.modules

import android.app.Application
import androidx.room.Room
import com.tephra.mc.latestnews.data.db.ArticleDao
import com.tephra.mc.latestnews.data.db.ArticlesDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, ServiceModule::class, ActivityModule::class, DatabaseModule::class])
class AppModule(val app: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = app

}