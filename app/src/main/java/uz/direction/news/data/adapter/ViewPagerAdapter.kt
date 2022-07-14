package uz.direction.news.data.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.direction.news.RuNewsFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int) = RuNewsFragment.newInstance(position)

}

