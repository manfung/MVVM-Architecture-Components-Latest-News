package com.tephra.mc.latestnews.data.repository


import com.tephra.mc.latestnews.BuildConfig
import com.tephra.mc.latestnews.data.model.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * REST API endpoints
 */
interface NewsApiService {
    /**
     * This endpoint provides live top and breaking headlines for a country, specific category in a country,
     * single source, or multiple sources. You can also search with keywords.
     * Articles are sorted by the earliest date published first.
     */
    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Header("Authorization") api: String = BuildConfig.NEWS_API_KEY,
                        @Query("sources") query: String= "bbc-news",
                        @Query("language") language: String = "en",
                        @Query("sortby") sortBy: String = "publishedAt"): Observable<News>

}