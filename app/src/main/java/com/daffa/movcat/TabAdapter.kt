package com.daffa.movcat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabAdapter(fm : FragmentManager,private var totalTab : Int):FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        return when(position){
            0 -> MovieFragment()
            1 -> SerialFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return totalTab
    }
}