package com.team3.weatherornot.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team3.weatherornot.R

/**
 * The activity for the suggest/select activity view
 *
 * @constructor Create empty constructor for suggest/select weather activity
 */
class SuggestSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.suggest_select_layout)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.suggest_select_nav_view)
        bottomNavigationView.selectedItemId = R.id.navigation_suggest_select
        bottomNavigationView.setOnItemSelectedListener {
            Navigation().onNavigationItemSelected(it, this)
        }

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        val fragments: ArrayList<Fragment> = arrayListOf(
            SuggestFragment(), SelectFragment()
        )

        val adapter2 = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter2
    }
}