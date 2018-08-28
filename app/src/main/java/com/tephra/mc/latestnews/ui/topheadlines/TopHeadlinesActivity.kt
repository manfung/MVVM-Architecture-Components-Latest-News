package com.tephra.mc.latestnews.ui.topheadlines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.news.mc.com.newsapp.ui.adapter.TopHeadlinesAdapter
import com.tephra.mc.latestnews.R
import com.tephra.mc.latestnews.data.model.Article
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.top_headlines_activity.*
import javax.inject.Inject

class TopHeadlinesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var topHeadlinesViewModel: TopHeadlinesViewModel
    private lateinit var listView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_headlines_activity)
        setupViewModel()
        initViews()

    }

    private fun setupViewModel() {

        topHeadlinesViewModel = ViewModelProviders.of(this, viewModelFactory)[TopHeadlinesViewModel::class.java]

        topHeadlinesViewModel.getTopHeadlines().observe(this, Observer<List<Article>> {
            updateList(it!!)
            if (swipeRefreshLayout.isRefreshing) {
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun initViews() {

        listView = recycler_list
        listView.layoutManager = LinearLayoutManager(this)
        swipeRefreshLayout = refreshLayoutTopHeadlines
        swipeRefreshLayout.setOnRefreshListener { loadTopHeadlines() }
    }

    private fun loadTopHeadlines() {
        topHeadlinesViewModel.loadTopHeadlines()
    }

    private fun updateList(articles: List<Article>) {
        listView.adapter = TopHeadlinesAdapter(articles)
    }

}
