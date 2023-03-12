package com.example.rickmortytestapp.presentation.ui.fragments.view_pager2

import androidx.core.view.isGone
import androidx.paging.ExperimentalPagingApi
import com.example.rickmortytestapp.R
import com.example.rickmortytestapp.databinding.FragmentViewPagerBinding
import com.example.rickmortytestapp.presentation.ui.base.BaseFragment
import com.example.rickmortytestapp.presentation.ui.fragments.characters.CharacterFragment
import com.example.rickmortytestapp.presentation.ui.fragments.episodes.EpisodeFragment
import com.example.rickmortytestapp.presentation.ui.fragments.location.LocationsFragment
import com.example.rickmortytestapp.presentation.utils.network_helper.CheckInternet
import com.google.android.material.tabs.TabLayoutMediator

@ExperimentalPagingApi
class ViewPagerFragment :
    BaseFragment<FragmentViewPagerBinding>(FragmentViewPagerBinding::inflate) {

    override fun initView() {
        super.initView()

        setupVPAdapter()
        setupTabLayout()
    }

    override fun initCheckInternet() {
        super.initCheckInternet()
        val ccs = CheckInternet(activity?.application!!)
        ccs.observe(this) { isInternetOn ->
            vb.tvNoInternet.isGone = isInternetOn
        }
    }

    private fun setupVPAdapter() {
        val vpAdapter = SectionsPagerAdapter(fragment = this)
        val fragmentList = listOf(CharacterFragment(), LocationsFragment(), EpisodeFragment())
        vpAdapter.fragmentList.addAll(fragmentList)
        vb.viewPager.apply {
            adapter = vpAdapter
            isUserInputEnabled = false
        }
    }

    private fun setupTabLayout() {
        TabLayoutMediator(vb.tabs, vb.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.characters)
                }
                1 -> {
                    tab.text = getString(R.string.locations)
                }
                2 -> {
                    tab.text = getString(R.string.episodes)
                }
            }
        }.attach()
    }
}