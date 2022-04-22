package com.example.myapplication.laoding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter


class IntroViewPagerAdapter(mContext: Context, mListScreen: List<ScreenItem>) :
    PagerAdapter() {
    var mContext: Context
    var mListScreen: List<ScreenItem>
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutScreen: View = inflater.inflate(com.example.myapplication.R.layout.layout_screen, null)
        val imgSlide: ImageView = layoutScreen.findViewById(com.example.myapplication.R.id.intro_img)
        val title: TextView = layoutScreen.findViewById(com.example.myapplication.R.id.intro_title)
        val description: TextView = layoutScreen.findViewById(com.example.myapplication.R.id.intro_description)
        title.setText(mListScreen[position].title)
        description.setText(mListScreen[position].description)
        imgSlide.setImageResource(mListScreen[position].screenImg)
        container.addView(layoutScreen)
        return layoutScreen
    }

    override fun getCount(): Int {
        return mListScreen.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    init {
        this.mContext = mContext
        this.mListScreen = mListScreen
    }
}
