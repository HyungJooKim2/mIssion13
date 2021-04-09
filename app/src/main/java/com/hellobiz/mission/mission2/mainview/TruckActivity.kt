package com.hellobiz.mission.mission2.mainview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.ActivityTruckBinding
import com.hellobiz.mission.mission2.magazine.MagazineFrag
import com.hellobiz.mission.mission2.mainview.chatting.ChattingFrag
import com.hellobiz.mission.mission2.mainview.plate.PlateFrag
import com.hellobiz.mission.mission2.mainview.sell.SellFrag
import com.hellobiz.mission.mission2.setting.SettingFrag

class TruckActivity : AppCompatActivity() {
    private var mBinding: ActivityTruckBinding? = null
    private val binding get() = mBinding!!
    private var test = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTruckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationConnect()

    }

    private fun bottomNavigationConnect() {
        //첫 화면 지정
        //supportFragmentManager.beginTransaction() : 어떤 대상에 대해 추가, 제거, 변경 등의 작업들이 발생하는것을 묶어서 얘기하는 것
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.truck_frame_layout, SellFrag()).commit()

        //첫 화면이 켜졌을 경우 해당 아이템을 선택
        binding.bottomnavigationview.selectedItemId = R.id.bottom_sell

        //아이템을 클릭했을때 동작하는 리스너
        //https://tedrepository.tistory.com/6 (개념 참고)
        binding.bottomnavigationview.setOnNavigationItemSelectedListener { it ->
            //Fragment Transaction 을 이용할 때,
            //이전 commit 이 있다면, 재사용시 beginTransaction() 을 재 호출해 주어야 함
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.bottom_sell -> {           //다음 item을 클릭했을 시
                    if (test != "sell") {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.truck_frame_layout,
                            SellFrag()
                        ).commit()
                        test = "sell"           //bottomnavigationview 오동작 방지
                    }
                    //클릭시 true로 클릭, 언클릭 true false 변화로 아이템 색상 지정
                    true
                }
                R.id.bottom_plate -> {
                    if (test != "plate") {
                        transaction.replace(
                            R.id.truck_frame_layout,
                            PlateFrag()
                        ).commit()
                        test = "plate"
                    }
                    true
                }
                R.id.bottom_chatting -> {
                    if (!test.equals("chatting")) {
                        transaction.replace(
                            R.id.truck_frame_layout,
                            ChattingFrag()
                        ).commit()
                        test = "chatting"
                    }
                    true
                }
                R.id.bottom_magazine -> {
                    if (!test.equals("magazine")) {
                        transaction.replace(
                            R.id.truck_frame_layout,
                            MagazineFrag()
                        ).commit()
                        test = "magazine"
                    }
                    true
                }
                R.id.bottom_setting -> {
                    if (!test.equals("setting")) {
                        transaction.replace(
                            R.id.truck_frame_layout,
                            SettingFrag()
                        ).commit()
                        test = "setting"
                    }
                    true
                }
                else -> {
                    //클릭이 안되어 있을시 false
                    false
                }
            }
        }
    }
}