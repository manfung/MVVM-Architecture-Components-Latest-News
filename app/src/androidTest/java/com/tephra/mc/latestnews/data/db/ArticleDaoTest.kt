package com.tephra.mc.latestnews.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.tephra.mc.latestnews.data.model.Article
import com.tephra.mc.latestnews.utils.getValueBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var articleDao: ArticleDao
    lateinit var articlesDatabase: ArticlesDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        articlesDatabase = Room.inMemoryDatabaseBuilder(context, ArticlesDatabase::class.java).build()
        articleDao = articlesDatabase.articleDao()
    }

    @After
    fun tearDown() {
        articlesDatabase.close()
    }

    @Test
    fun testInsertedAndRetrievedArticlesMatch() {

        val id:Long = 1
        val author = "Author 1"
        val title = "title"
        val description = "description 1"
        val url = "url"
        val urlImage = "imageUrl"
        var date = Date()

        articleDao.insert(Article(
                id,
                author,
                title,
                description,
                url,
                urlImage,
                date
                ))

        val articleWrappedInLiveData = articleDao.getAll()
        val articleFromDb = articleWrappedInLiveData.getValueBlocking()
        assertEquals(articleFromDb!!.size, 1)
        assertEquals(articleFromDb!![0].id, id)
        assertEquals(articleFromDb!![0].author, author)
        assertEquals(articleFromDb!![0].title, title)
        assertEquals(articleFromDb!![0].description, description)
        assertEquals(articleFromDb!![0].url, url)
        assertEquals(articleFromDb!![0].urlToImage, urlImage)
        assertEquals(articleFromDb!![0].publishedAt, date)

    }

    @Test
    fun testDeleteAll() {
        articleDao.deleteAll()
        val articleWrappedInLiveData = articleDao.getAll()
        val articleFromDb = articleWrappedInLiveData.getValueBlocking()
        assertEquals(articleFromDb!!.size, 0)
    }

    @Test
    fun testInsertArticles() {

        articleDao.deleteAll()
        val articlesSize = 2
        articleDao.insertArticles(getArticles(articlesSize))
        val articleWrappedInLiveData = articleDao.getAll()
        val articleFromDb = articleWrappedInLiveData.getValueBlocking()
        assertEquals(articleFromDb!!.size, articlesSize)
        articleDao.deleteAll()

    }

    private fun getArticles(articlesSize: Int): List<Article> {

        val author = "Author 1"
        val title = "title"
        val description = "description 1"
        val url = "url"
        val urlImage = "imageUrl"
        var date = Date()

        val articles = mutableListOf<Article>()
        for (i in 1..articlesSize) {
            articles.add (Article(
                    i.toLong(),
                    author,
                    title,
                    description,
                    url,
                    urlImage,
                    date
            ) )
        }

        return articles
    }

}