package com.team3.weatherornot.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * View pager adapter for swiping between fragments
 *
 * @property items the fragments to add to the pager
 * @constructor Create [ViewPagerAdapter]
 *
 * @param activity the activity the fragments are in
 */
class ViewPagerAdapter(
    private val items: ArrayList<Fragment>, activity: AppCompatActivity
): FragmentStateAdapter(activity) {

    /**
     * Get number of fragments
     *
     * @return number of fragments
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Create the fragment at the position
     *
     * @param position the position of the fragment to be created
     * @return [Fragment] the new fragment
     */
    override fun createFragment(position: Int): Fragment {
        return items[position]
    }


}