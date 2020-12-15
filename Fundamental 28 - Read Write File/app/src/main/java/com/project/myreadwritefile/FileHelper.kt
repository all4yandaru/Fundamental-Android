package com.project.myreadwritefile

import android.content.Context
import android.util.Log
import com.project.myreadwritefile.model.FileModel
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStreamWriter

// TODO 5: buat internal object helper utk menyimpan metode untuk menyimpan dan membaca data
internal object FileHelper {
    private val TAG = FileHelper::class.java.simpleName

    fun writeToFile(fileModel: FileModel, context: Context){
        try {
            val outputStreamerWriter = OutputStreamWriter(context.openFileOutput(fileModel.filename.toString(), Context.MODE_PRIVATE))
            outputStreamerWriter.write(fileModel.data.toString())
            outputStreamerWriter.close()
        } catch (e: IOException){
            Log.e(TAG, "File write failed : ", e)
        }
    }

    fun readFromFile(context: Context, filename: String) : FileModel {
        val fileModel = FileModel()
        try {
            val inputStream = context.openFileInput(filename)

            if (inputStream != null){
                val receiveString = inputStream.bufferedReader().use(BufferedReader::readText)
                inputStream.close()
                fileModel.data = receiveString
                fileModel.filename = filename
            }
        } catch (e: FileNotFoundException) {
            Log.e(TAG, "File not found :", e)
        } catch (e: IOException) {
            Log.e(TAG, "Can not read file :", e)
        }
        return fileModel
    }
}