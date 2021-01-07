package com.project.githubuser.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.project.githubuser.R

class SectionsPagerAdapter(private val mContext: Context, fm : FragmentManager, private val login: String?) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.followers, R.string.following)

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position) {
            0 -> fragment = FollowerFragment(login)
            1 -> fragment = FollowingFragment(login)
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}