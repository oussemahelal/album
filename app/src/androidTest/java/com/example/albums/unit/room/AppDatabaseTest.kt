package com.example.albums.unit.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.albums.data.room.dao.AppDao
import com.example.albums.data.room.db.AppDatabase
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest {

    private lateinit var dataBase: AppDatabase
    private var appDao: AppDao? = null

    @Before
    fun setUpDatabase() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        appDao = dataBase.appDao()
    }

    /**
     * Assert that appDao is initialized
     */
    @Test
    fun app_database_return_true() {
        assertTrue(dataBase.appDao() == appDao)
    }

    /**
     * Assert that appDao is null
     */
    @Test
    fun app_database_return_false() {
        appDao = null
        assertFalse(dataBase.appDao() == appDao)
    }

    @After
    fun closeDatabase() {
        dataBase.close()
    }
}