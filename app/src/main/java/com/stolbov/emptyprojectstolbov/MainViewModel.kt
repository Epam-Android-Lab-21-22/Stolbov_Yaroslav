package com.stolbov.emptyprojectstolbov

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.stolbov.emptyprojectstolbov.database.AppDatabase
import com.stolbov.emptyprojectstolbov.database.JustString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.io.IOException

class MainViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    val fileNameInternal = "internal.txt"
    val fileNameExternal = "external.txt"
    val dirName = "mydir"
    var lastString = ""

    fun savePref(text: String) {
        sharedPreferences.edit {
            putString(MainActivity.SHARED_KEY, text)
        }
    }

    fun loadPref(): String {
        lastString = sharedPreferences.getString(
            MainActivity.SHARED_KEY,
            Context.MODE_PRIVATE.toString()
        ).toString()
        return lastString
    }

    fun saveInternal(context: Context, sBody: String?) {
        val dir = File(context.filesDir, dirName)
        if (!dir.exists()) {
            dir.mkdir()
        }
        try {
            val file = File(dir, fileNameInternal)
            val writer = FileWriter(file)
            writer.append(sBody)
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun loadInternal(context: Context): String {
        val dir = File(context.filesDir, dirName)
        if (!dir.exists()) {
            return ""
        }
        val file = File(dir, fileNameInternal)
        var inputString = ""
        try {
            FileInputStream(file).bufferedReader().use {
                inputString = it.readText()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        lastString = inputString
        return inputString
    }

    fun saveExternal(context: Context, sBody: String?) {
        val root = context.getExternalFilesDir(null)
        val dir = File(root, dirName)
        if (!dir.exists()) {
            dir.mkdir()
        }
        try {
            val file = File(dir, fileNameExternal)
            val writer = FileWriter(file)
            writer.append(sBody)
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun loadExternal(context: Context): String {
        val root = context.getExternalFilesDir(null)
        val dir = File(root, dirName)
        if (!dir.exists()) {
            return ""
        }
        val file = File(dir, fileNameExternal)
        var inputString = ""
        try {
            FileInputStream(file).bufferedReader().use {
                inputString = it.readText()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        lastString = inputString
        return inputString
    }

    fun saveDB(context: Context, str: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val justString = JustString()
            justString.string = str
            val db: AppDatabase = AppDatabase.getInstance(context)
            val dao = db.justStringDAO()
            if (dao != null) {
                if (dao.getStringsList().isEmpty()) dao.insertString(justString)
                else dao.updateString(justString)
            }
        }
    }

    suspend fun loadDB(context: Context): String {
        val value = CoroutineScope(Dispatchers.IO).async {
            val db: AppDatabase = AppDatabase.getInstance(context)
            val dao = db.justStringDAO()
            dao?.getStringsList()?.get(0)
        }
        value.await()
        lastString = value.getCompleted()?.string.toString()
        return lastString
    }
}


