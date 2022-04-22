package com.example.myapplication.laoding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout


class IntroActivity : AppCompatActivity() {
    var introViewPagerAdapter: IntroViewPagerAdapter? = null
    lateinit var tabIndicator: TabLayout
    lateinit   var btnNext: Button
    var position = 0
  lateinit  var btnGetStarted: Button
  lateinit  var tvSkip: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        // make the activity on full screen
     /*   requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        */

        // when this activity is about to be launch we need to check if its openened before or not
        if (restorePrefData()) {
            val mainActivity = Intent(applicationContext, Laoding::class.java)
            startActivity(mainActivity)
            finish()
        }

        // hide the action bar


        // ini views
        btnNext = findViewById(R.id.btn_next)
        btnGetStarted = findViewById(R.id.btn_get_started)
        tabIndicator = findViewById(R.id.tab_indicator)
        tvSkip = findViewById(R.id.tv_skip)

        // fill list screen
        val mList: MutableList<ScreenItem> = ArrayList()
        mList.add(
            ScreenItem(
                "Fresh Food",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit",
                com.example.myapplication.R.drawable.fizer
            )
        )
        mList.add(
            ScreenItem(
                "Fast Delivery",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit",
                com.example.myapplication.R.drawable.entete
            )
        )
        mList.add(
            ScreenItem(
                "Easy Payment",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit",
               R.drawable.entete2
            )
        )

        // setup viewpager
     val   screenPager:ViewPager = findViewById(R.id.screen_viewpager)
        introViewPagerAdapter = IntroViewPagerAdapter(this, mList)
        screenPager.adapter=introViewPagerAdapter

        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager)

        // next button click Listner
        btnNext.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                position = screenPager.getCurrentItem()
                if (position < mList.size) {
                    position++
                    screenPager.setCurrentItem(position)
                }
                if (position == mList.size - 1) { // when we rech to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button
                    loaddLastScreen()
                }
            }
        })

        // tablayout add change listener
        tabIndicator.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun  onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == mList.size - 1) {
                    loaddLastScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })





        // Get Started button click listener
        btnGetStarted.setOnClickListener (object: View.OnClickListener {
            override  fun onClick(v: View?) {


                //open main activity
                val mainActivity = Intent(applicationContext, Laoding::class.java)
                startActivity(mainActivity)
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                savePrefsData()
                finish()
            }
        })



        // skip button click listener
        tvSkip.setOnClickListener(object : View.OnClickListener {
           override fun onClick(v: View?) {
                screenPager.setCurrentItem(mList.size)
            }
        })
    }

    private fun restorePrefData(): Boolean {
        val pref =
            applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        return pref.getBoolean("isIntroOpnend", false)
    }

    private fun savePrefsData() {
        val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isIntroOpnend", true)
        editor.commit()
    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private fun loaddLastScreen() {
        btnNext.setVisibility(View.INVISIBLE)
        btnGetStarted.setVisibility(View.VISIBLE)
        tvSkip!!.visibility = View.INVISIBLE
        tabIndicator!!.visibility = View.INVISIBLE
        // setup animation
    }
}