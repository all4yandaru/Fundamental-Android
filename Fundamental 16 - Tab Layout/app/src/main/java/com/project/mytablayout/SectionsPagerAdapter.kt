package com.project.mytablayout

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

// TODO 5: buat class utk menangani view pager dan tab layout dan isi codingannya jadi kek gini

class SectionsPagerAdapter(private val mContext: Context, fm : FragmentManager) : FragmentPagerAdapter (fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3) // TODO 9: tambahin text 3

    override fun getItem(position: Int): Fragment {
        /*var fragment : Fragment? = null
        when(position){
            0 -> fragment = HomeFragment()
            1 -> fragment = ProfileFragment()
        }*/
        // TODO 10: ubah jadi gini
        val fragment = HomeFragment.newInstance(position + 1)
        return fragment /*as Fragment*/
    }

    override fun getCount(): Int {
        return 3 /*2*/ // TODO 11: ubah jadi 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}