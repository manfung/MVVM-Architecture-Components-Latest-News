package com.tephra.mc.latestnews.ui.topheadlines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.tephra.mc.latestnews.data.model.Article
import com.tephra.mc.latestnews.data.repository.NewsRepo
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

class TopHeadlinesViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var topHeadlinesViewModel: TopHeadlinesViewModel
    private val newsRepository = mock<NewsRepo>()
    private val articlesSize = 2

    @Before
    fun setup() {

        var articlesLiveData = MutableLiveData<List<Article>>()
        articlesLiveData.value = getArticles(articlesSize)

        whenever(newsRepository.getTopHeadlines())
                .thenReturn(articlesLiveData)

        topHeadlinesViewModel = TopHeadlinesViewModel(newsRepository)
    }

    @Test
    fun testGetTopHeadlines() {

        val articleWrappedInLiveData = topHeadlinesViewModel.getTopHeadlines()
        val articleFromRemote = articleWrappedInLiveData.value
        Assert.assertEquals(articleFromRemote!!.size, articlesSize)

    }

    private fun getArticles(articlesSize: Int): List<Article> {

        val author = "Author "
        val title = "title"
        val description = "description"
        val url = "url"
        val urlImage = "imageUrl"
        var date = Date()

        val articles = mutableListOf<Article>()
        for (i in 1..articlesSize) {
            articles.add (Article(
                    author = author + i,
                    title = title + i,
                    description = description + i,
                    url = url,
                    urlToImage = urlImage,
                    publishedAt = date
            ) )
        }

        return articles
    }
}
