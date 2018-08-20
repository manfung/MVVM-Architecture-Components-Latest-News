package com.tephra.mc.latestnews.ui.topheadlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tephra.mc.latestnews.data.model.Article
import com.tephra.mc.latestnews.data.repository.NewsRepo
import javax.inject.Inject

class TopHeadlinesViewModel @Inject constructor(private val newsRepository: NewsRepo): ViewModel() {

    val news: LiveData<List<Article>> = newsRepository.getTopHeadlines()

    fun getTopHeadlines():LiveData<List<Article>> {
        return newsRepository.getTopHeadlines()
    }

}
