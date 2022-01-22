package com.bcebhagalpur.epsum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    private lateinit var btnRefresh:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRefresh=findViewById(R.id.btnRefresh)
        btnRefresh.setOnClickListener {
            startActivity(Intent(this,Dashboard::class.java))
        }
    }

}