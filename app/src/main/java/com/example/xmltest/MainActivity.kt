package com.example.xmltest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val window1Fragment = Window1Fragment()
    private val window2Fragment = Window2Fragment()
    private val window3Fragment = Window3Fragment()
    private var activeFragment: Fragment = window1Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction().add(R.id.content_container, window3Fragment, "3").hide(window3Fragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.content_container, window2Fragment, "2").hide(window2Fragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.content_container, window1Fragment, "1").commit()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_window1 -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(window1Fragment).commit()
                    activeFragment = window1Fragment
                    true
                }
                R.id.action_window2 -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(window2Fragment).commit()
                    activeFragment = window2Fragment
                    true
                }
                R.id.action_window3 -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(window3Fragment).commit()
                    activeFragment = window3Fragment
                    true
                }
                else -> false
            }
        }
    }
}
