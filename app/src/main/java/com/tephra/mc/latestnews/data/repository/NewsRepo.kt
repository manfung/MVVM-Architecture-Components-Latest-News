package com.tephra.mc.latestnews.data.repository

import androidx.lifecycle.LiveData
import com.tephra.mc.latestnews.data.model.Article

interface NewsRepo {

    fun getTopHeadlines(): LiveData<List<Article>>
}
