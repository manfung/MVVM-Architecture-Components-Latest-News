package com.tephra.mc.latestnews.data.model

/**
 * API response Model for a News object.
 *
 * @param status       If the request was successful or not
 * @param totalResults The total number of results available for your request
 * @param articles     The results of the request
 */
data class News(val status: String,
                val totalResults: Int,
                val articles: List<Article>)