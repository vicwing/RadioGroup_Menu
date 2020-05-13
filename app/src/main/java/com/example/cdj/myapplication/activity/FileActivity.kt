package com.example.cdj.myapplication.activity

import android.content.ContentUris
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.cdj.myapplication.R
import com.orhanobut.logger.Logger

/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2020/5/12 2:48 PM
 * 最后修改人：vicwing
 * @version
 */
class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_activity)

//        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
//                null, null, "${MediaStore.MediaColumns.DATE_ADDED} desc")
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOf(MediaStore.Images.Media._ID),
                null, null, "${MediaStore.MediaColumns.DATE_ADDED} desc")

        if (cursor != null) {
            val stringBuilder = StringBuilder()
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
//                println("image uri is $uri")
                stringBuilder.append("$uri\n")
                Logger.d("image uri is $uri")
            }
            findViewById<TextView>(R.id.tv_kotlin_1).setText(stringBuilder.toString())
            cursor.close()
        } else {
            findViewById<TextView>(R.id.tv_kotlin_1).setText("image uri is noting")
        }
    }


}