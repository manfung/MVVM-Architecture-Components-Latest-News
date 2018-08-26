package com.tephra.mc.latestnews.di.modules

import android.app.Application
import com.tephra.mc.latestnews.data.db.ArticleDao
import com.tephra.mc.latestnews.data.repository.NewsApiService
import com.tephra.mc.latestnews.data.repository.NewsRepo
import com.tephra.mc.latestnews.data.repository.NewsRepoImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, ServiceModule::class, ActivityModule::class, DatabaseModule::class])
class AppModule(val app: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    internal fun provideNewsRepo(newsApiService: NewsApiService, articlesDao: ArticleDao): NewsRepo {
        return NewsRepoImp(newsApiService, articlesDao)
    }


}