package com.daffa.movcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private var tablayout : TabLayout? = null
    private var viewPager : ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loading(true)
        Handler().postDelayed({
            loading(false)
            Log.d("Load","TUTUP")
        },2500)
        tablayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tablayout!!.addTab(tablayout!!.newTab().setText("Movie"))
        tablayout!!.addTab(tablayout!!.newTab().setText("Serial"))
        tablayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabAdapter(supportFragmentManager,tablayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))

        tablayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
    private fun loading(load : Boolean){
        if (load){
            main.visibility = View.GONE
            lav_loading.visibility = View.VISIBLE
        }else{
            main.visibility = View.VISIBLE
            lav_loading.visibility = View.GONE
        }

    }
}
