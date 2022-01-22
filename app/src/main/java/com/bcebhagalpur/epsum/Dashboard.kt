package com.bcebhagalpur.epsum

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File

class Dashboard : AppCompatActivity() {

    private lateinit var imgTop:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        imgTop=findViewById(R.id.imgTop)
        loadImage()
    }

    private fun loadImage(){
        val handler = Handler()
        val runnable: Runnable = object : Runnable {
            var i = 0
            override fun run() {
//                Picasso.get().load("https://picsum.photos/400/200").memoryPolicy(MemoryPolicy.NO_CACHE)
//                        .networkPolicy(NetworkPolicy.NO_CACHE).error(R.drawable.ic_launcher_background).into(imgTop)
                setBackground()
                deleteCache(this@Dashboard)
                i++
                if (i > 3) {
                    i = 0
                }
                handler.postDelayed(this, 2000)
            }
        }
        handler.postDelayed(runnable, 2000)
    }

    private fun setBackground(){
        Picasso.get().load("https://picsum.photos/400/200").memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                imgTop.background = BitmapDrawable(this@Dashboard.resources, bitmap)
            }

            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
                Toast.makeText(this@Dashboard,"bitmap failed", Toast.LENGTH_SHORT).show()
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
//                Toast.makeText(this@Dashboard,"prepared for loading", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun deleteCache(context: Context) {
        try {
            val dir: File = context.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun deleteDir(dir: File?): Boolean {
        return if (dir != null && dir.isDirectory) {
            val children: Array<String> = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
            dir.delete()
        } else if (dir != null && dir.isFile) {
            dir.delete()
        } else {
            false
        }
    }


}