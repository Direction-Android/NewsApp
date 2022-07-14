package uz.direction.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import uz.direction.news.data.adapter.ViewPagerAdapter

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(this)
        findViewById<ViewPager2>(R.id.pager).adapter = adapter

        TabLayoutMediator(findViewById(R.id.tabLayout), findViewById(R.id.pager))
        { tab, position ->
            tab.text = getTitle(position)
        }.attach()
    }


    private fun getTitle(position: Int): String {
        return when (position) {
            0 -> "GENERAL"
            1 -> "ENTERTAINMENT"
            2 -> "BUSINESS"
            3 -> "HEALTH"
            4 -> "SCIENCE"
            5 -> "SPORTS"
            else -> "TECHNOLOGY"
        }
    }
}