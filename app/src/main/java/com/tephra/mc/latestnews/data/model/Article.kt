package com.tephra.mc.latestnews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * For simplicity this is both the API and ROOM model class for an Article.
 *
 * @param url           Primary Key - The direct URL to the article
 * @param author        The author of the article
 * @param title         The headline or title of the article
 * @param description   A description or snippet from the article
 * @param urlToImage    The URL to a relevant image for the article
 * @param publishedAt   The date and time that the article was published, in UTC (+000)
 */
@Entity
data class Article( @PrimaryKey val url: String,
                   val author: String,
                   val title: String,
                   val description: String,
                   val urlToImage: String,
                   val publishedAt: Date
                   )