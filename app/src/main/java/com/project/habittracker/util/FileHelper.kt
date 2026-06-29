    package com.project.habittracker.util

    import android.content.Context
    import com.google.gson.Gson
    import com.google.gson.reflect.TypeToken
    import com.project.habittracker.model.Habit
    import java.io.File
    import java.io.FileOutputStream
    import java.io.IOException

    class FileHelper(val context: Context) {

        val folderName = "habitfolder"
        val fileName = "habitdata.json"

        private fun getFile(): File {
            val dir = File(context.getExternalFilesDir(null), folderName)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            return File(dir, fileName)
        }

        fun writeToFile(list: List<Habit>) {
            try {
                val file = getFile()
                val json = Gson().toJson(list)
                FileOutputStream(file, false).use { output ->
                    output.write(json.toByteArray())
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun readFromFile(): MutableList<Habit> {
            return try {
                val file = getFile()
                val json = file.bufferedReader().use { it.readText() }

                val type = object : TypeToken<MutableList<Habit>>() {}.type
                Gson().fromJson(json, type) ?: mutableListOf()
            } catch (e: Exception) {
                mutableListOf()
            }
        }

        fun deleteFile(): Boolean {
            return getFile().delete()
        }

        fun getFilePath(): String {
            return getFile().absolutePath
        }

        fun clearData() {
            writeToFile(mutableListOf())
        }
    }