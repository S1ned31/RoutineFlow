package com.busol.habittracker.core.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import com.google.gson.Gson
import com.busol.habittracker.core.data.db.dao.InfoDao
import com.busol.habittracker.core.data.db.entities.InfoEntity
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.Json

class InfoRepositoryImpl(
    private val infoDao: InfoDao,
    private val context: Context,
) : InfoRepository {
    private val gson = Gson()
    override fun getInfoList(): List<InfoEntity> {
        return infoDao.getInfoList()
    }

    override fun getInfoPost(id: Long): InfoEntity {
        return infoDao.getInfoById(id = id)
    }

    override fun deleteAll() {
        val allInfoPosts = infoDao.getInfoList()
        allInfoPosts.forEach { post ->
            deleteImageFile(post.image)
        }
        infoDao.deleteAll()
    }

    override fun insert(infoPost: InfoEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            infoDao.insertInfo(infoPost)
        }
    }

    override suspend fun getInfoFromRemote(): List<InfoEntity> {
        val timeoutMillis = 10000L
        return try {
            withTimeout(timeoutMillis) {
                val jsonString = context.assets.open("articles.json").use { inputStream ->
                    BufferedReader(InputStreamReader(inputStream)).readText()
                }
                parseJsonString(jsonString)?.data ?: emptyList()
            }
        } catch (e: TimeoutCancellationException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun parseJsonString(jsonString: String): InfoList? {
        return try {
            Gson().fromJson(jsonString, InfoList::class.java)
        } catch (e: Exception) {
            null
        }
    }

    private fun deleteImageFile(imagePath: String?) {
        imagePath?.let {
            val imageFile = File(it)
            if (imageFile.exists()) {
                imageFile.delete()
            }
        }
    }
}

class InfoList(val data: List<InfoEntity>)