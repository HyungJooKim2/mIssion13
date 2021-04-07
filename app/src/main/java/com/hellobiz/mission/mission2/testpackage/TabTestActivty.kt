package com.hellobiz.mission.mission2.testpackage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.hellobiz.mission.R
import kotlinx.android.synthetic.main.activity_tab_test_activty.*

class TabTestActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_test_activty)

        findViewById<TabLayout>(R.id.test_tab_layout).addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.text){
                    "전체 매물" -> test_linear.setBackgroundColor(Color.parseColor("#FFBBBB"))
                    "내 차량 매물" -> test_linear.setBackgroundColor(Color.parseColor("#BBFFBB"))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
//                when (tab!!.position) {
//                    0 -> test_linear.setBackgroundColor(Color.parseColor("#FFBBBB"))
//                    1 -> test_linear.setBackgroundColor(Color.parseColor("#BBFFBB"))
//                }
            }


        })
    }
}