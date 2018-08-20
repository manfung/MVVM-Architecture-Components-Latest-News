package com.tephra.mc.latestnews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tephra.mc.latestnews.data.model.Article

/**
 * The Room Database that contains the Articles table
 */
@Database(entities = [Article::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class ArticlesDataBase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}