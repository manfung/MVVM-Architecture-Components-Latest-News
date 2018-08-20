package com.tephra.mc.latestnews.data.repository

import androidx.lifecycle.LiveData
import com.tephra.mc.latestnews.data.db.ArticleDao
import com.tephra.mc.latestnews.data.model.Article
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * News Repository module, responsible for handling the news data operations
 */
class NewsRepo @Inject constructor(private val newsApiService: NewsApiService, private val articlesDao: ArticleDao) {

    fun getTopHeadlines(): LiveData<List<Article>> {
        getTopHeadlinesFromApi()
        return articlesDao.getAll()
    }

    private fun getTopHeadlinesFromApi() {

        newsApiService.getTopHeadlines()
                .subscribeOn(Schedulers.io())
                .subscribe { news ->
                    // API call successfully completed. For simplicity I am re-using the same
                    // data model for both the database and API
                    articlesDao.insertArticles(news.articles)
                }
    }

}