package com.tephra.mc.latestnews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * For simplicity this is both the API and ROOM model class for an Article.
 *
 * @param id            Primary Key
 * @param author        The author of the article
 * @param title         The headline or title of the article
 * @param description   A description or snippet from the article
 * @param url           The direct URL to the article
 * @param urlToImage    The URL to a relevant image for the article
 * @param publishedAt   The date and time that the article was published, in UTC (+000)
 */
@Entity
data class Article(@PrimaryKey(autoGenerate = true) var id: Long?,
                   val author: String,
                   val title: String,
                   val description: String,
                   val url: String,
                   val urlToImage: String,
                   val publishedAt: Date
                   )