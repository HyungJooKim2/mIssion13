package com.hellobiz.mission2.mainview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hellobiz.mission.R

class LifeCycleTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_test)
    }

    override fun onStart() {
        super.onStart()
        Log.d("#####","start")
    }

    override fun onResume() {
        super.onResume()
        Log.d("#####","resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("#####","pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("#####","stop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("#####","restart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("#####","destory")
    }
}