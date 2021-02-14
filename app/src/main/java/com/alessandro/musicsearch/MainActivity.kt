package com.alessandro.musicsearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alessandro.musicsearch.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private val searchFragment = SearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, searchFragment)
                .commitNow()
        }
    }

}