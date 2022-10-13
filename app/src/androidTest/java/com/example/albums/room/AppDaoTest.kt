package com.example.albums.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.albums.data.room.dao.AppDao
import com.example.albums.data.room.db.AppDatabase
import com.example.albums.data.room.model.DataModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDaoTest {

    private lateinit var dataBase: AppDatabase
    private lateinit var appDao: AppDao
    private lateinit var list: List<DataModel>

    @Before
    fun setUpDatabase() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        appDao = dataBase.appDao()

        list = listOf(DataModel(
            id = 1,
            albumId = 1,
            title = "test_1",
            url = "www.url.com",
            thumbnailUrl = "www.url.com"
        ),
            DataModel(
                id = 2,
                albumId = 2,
                title = "test_2",
                url = "www.url.com",
                thumbnailUrl = "www.url.com"
            ),
            DataModel(
                id = 3,
                albumId = 3,
                title = "test_3",
                url = "www.url.com",
                thumbnailUrl = "www.url.com"
            ))
    }

    /**
     * Assert that database save model
     */
    @Test
    fun get_by_id_data_return_true() {

        runBlocking {
            appDao.insertModel(list.first())
            assertTrue(appDao.getModelById(id = 1).title == "test_1")
        }
    }

    /**
     * Assert that database not contain model with empty title
     */
    @Test
    fun get_by_id_data_return_false() {

        runBlocking {
            appDao.insertModel(list.first())
            assertFalse(appDao.getModelById(id = 1).title == "")
        }
    }

    /**
     * Assert that database contain all elements return true
     */
    @Test
    fun get_all_data_return_true() {

        runBlocking {
            appDao.createAll(list)
            assertTrue(appDao.getAll().first().title == "test_1")
            assertTrue(appDao.getAll()[1].title == "test_2")
            assertTrue(appDao.getAll()[2].title == "test_3")
            assertTrue(appDao.getAll().size == 3)
        }
    }

    /**
     * Assert that database not contain any element with empty title
     */
    @Test
    fun get_all_data_return_false() {

        runBlocking {
            appDao.createAll(list)
            assertFalse(appDao.getAll().first().title == "")
            assertFalse(appDao.getAll()[1].title == "")
            assertFalse(appDao.getAll()[2].title == "")
            assertFalse(appDao.getAll().size == 4)
        }
    }

    @After
    fun closeDatabase() {
        dataBase.close()
    }
}