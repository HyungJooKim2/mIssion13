package com.hellobiz.mission.mission4.puttingtogether.page3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.ActivityClientDetailAcitivtyBinding
import com.hellobiz.mission.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_client_detail_acitivty.*

/*
거래처관리 상세페이지 Activity
 */
class ClientDetailAcitivty : AppCompatActivity() {
    private var mBinding: ActivityClientDetailAcitivtyBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityClientDetailAcitivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cntAddr = intent.getStringExtra("cntAddr")
        val cntMemo = intent.getStringExtra("cntMemo")
        val cntTel = intent.getStringExtra("cntTel")
        val cntType = intent.getStringExtra("cntType")
        val cntNm = intent.getStringExtra("cntNm")

        val items = arrayOf("A","B","C","D")
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        binding.clientType.adapter = myAdapter

        binding.clientType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작
                when(position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {

                    }
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        binding.clientName.text = cntNm
        binding.clientLocation.text = cntAddr
        binding.clientMemo.setText(cntMemo)
        binding.clientTel.text = cntTel
//        binding.clientType.setText(cntType)
    }
}