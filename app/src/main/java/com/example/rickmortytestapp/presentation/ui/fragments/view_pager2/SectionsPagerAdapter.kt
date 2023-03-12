package com.example.rickmortytestapp.presentation.ui.fragments.view_pager2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    val fragmentList = mutableListOf<Fragment>()

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
}