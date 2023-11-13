package com.example.xmltest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homeView = HomeViewImp()
    private val editView = EditViewImp()
    private val settingsView = SettingsViewImp()
    private var activeFragment: Fragment = homeView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction().add(R.id.content_container, settingsView, "3").hide(settingsView).commit()
        supportFragmentManager.beginTransaction().add(R.id.content_container, editView, "2").hide(editView).commit()
        supportFragmentManager.beginTransaction().add(R.id.content_container, homeView, "1").commit()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_window1 -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(homeView).commit()
                    activeFragment = homeView
                    true
                }
                R.id.action_window2 -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(editView).commit()
                    activeFragment = editView
                    true
                }
                R.id.action_window3 -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(settingsView).commit()
                    activeFragment = settingsView
                    true
                }
                else -> false
            }
        }
    }
}
