package com.poomon.androidinternassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager

    // Extendable fragment list for navigation
    private val pages = listOf(
        CoinFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            fragmentNavigation(0)
        }
    }

    // Abstraction fragment navigation
    private fun fragmentNavigation(page: Int){
        manager.beginTransaction()
            .replace(R.id.coinFragment, pages[page])
            .commit()
    }
}
