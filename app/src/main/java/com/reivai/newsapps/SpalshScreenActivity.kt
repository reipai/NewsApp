package com.reivai.newsapps

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.reivai.newsapps.databinding.ActivitySpalshScreenBinding
import com.reivai.newsapps.home.MainActivity

class SpalshScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpalshScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpalshScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setWindow(this, WindowManager.LayoutParams.FLAG_FULLSCREEN, false)
        window.statusBarColor = Color.TRANSPARENT

        Handler().postDelayed({
            val intent = Intent(this@SpalshScreenActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }, 3000)
    }

    private fun setWindow(activity: Activity, bits: Int, on: Boolean) {
        val win = activity.window
        val winParam = win.attributes
        if (on) {
            winParam.flags = winParam.flags or bits
        } else {
            winParam.flags = winParam.flags and bits.inv()
        }
        win.attributes = winParam
    }
}