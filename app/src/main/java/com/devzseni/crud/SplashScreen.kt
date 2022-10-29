package com.devzseni.crud

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devzseni.crud.ui.fragments.HomeFragment

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        //Execute a task in the Main Thread
       Handler(Looper.getMainLooper()).postDelayed(Runnable {
           startActivity(Intent(this, MainActivity::class.java))
           },2000)

        val ButtonOpen:Button = findViewById(R.id.moveButton)
        ButtonOpen.setOnClickListener {
            val homeFragment = HomeFragment()
            val fragment: Fragment? =

            supportFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)
            if (fragment !is HomeFragment){
                supportFragmentManager.beginTransaction()
                    .add(R.id.splashContainer,homeFragment,HomeFragment::class.java.simpleName)
                    .commit()
            }
                ButtonOpen.visibility = View.GONE
        }


    }
}