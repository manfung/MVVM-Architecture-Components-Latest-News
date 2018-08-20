package com.tephra.mc.latestnews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.tephra.mc.latestnews.data.model.Article
import androidx.lifecycle.LiveData

/**
 * Data Access Object for the Articles table
 */
@Dao
interface ArticleDao {

    @Query("SELECT * from Article")
    fun getAll(): LiveData<List<Article>>

    @Insert(onConflict = REPLACE)
    fun insert(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(repositories: List<Article>)

    @Query("DELETE from Article")
    fun deleteAll()

}