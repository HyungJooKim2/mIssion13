package com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hellobiz.mission.databinding.ActivityGroupDetailBinding
import kotlinx.android.synthetic.main.activity_client_detail_acitivty.*

/*
그룹관리 상세페이지 Activity
 */
class GroupDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityGroupDetailBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGroupDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.groupPercent.setText(intent.getIntExtra("gprPer", 0).toString())
        binding.groupName.setText(intent.getStringExtra("gprName"))

    }
}