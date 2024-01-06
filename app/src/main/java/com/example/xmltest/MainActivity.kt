package com.example.xmltest

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.xmltest.controller.Communication
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class MainActivity : AppCompatActivity(), Communication {
    private val homeView = HomeViewImp()
    private val editView = EditViewImp()
    private val settingsView = SettingsViewImp()
    private var activeFragment: Fragment = homeView
    private val fragments: List<Fragment> = listOf(homeView, editView)

    override fun onCreate(savedInstanceState: Bundle?) {
        val currentLanguage = Resources.getSystem().configuration.locales[0].language
        Log.d("Language", "Current Language On create (Res): $currentLanguage")
        val currLanguage = Locale.getDefault().language
        Log.d("Language", "Current Language On create (Loc): $currLanguage")
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.content_container, settingsView, "3").hide(settingsView)
            .commit()
        supportFragmentManager.beginTransaction().add(R.id.content_container, editView, "2").hide(editView).commit()
        supportFragmentManager.beginTransaction().add(R.id.content_container, homeView, "1").commit()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        //ToDo: Zneškodnit depricated, jinak chytře (Šimon něco podobného řešil)
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_window -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(homeView).commit()
                    activeFragment = homeView
                    true
                }
                R.id.edit_window -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(editView).commit()
                    activeFragment = editView
                    true
                }
                R.id.settings_window -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(settingsView).commit()
                    activeFragment = settingsView
                    true
                }
                else -> false
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val newLanguage = newConfig.locales[0].language
        Log.d("Language", "New Language in onConfigurationChanged: $newLanguage")
    }

    override fun onOptionSelected(option: Int) {
        fragments.forEach { fragment ->
            if (fragment is Communication) {
                fragment.onOptionSelected(option)
            }
        }
    }
}
