package com.hellobiz.mission.mission4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.ActivityDistributionMainBinding
import com.hellobiz.mission.mission4.puttingtogether.page2.Mission2Frag
import com.hellobiz.mission.mission4.puttingtogether.page4.Mission4Frag
import com.hellobiz.mission.mission4.puttingtogether.page3.Mission3Frag
import com.hellobiz.mission.mission4.puttingtogether.page1.Mission1Frag

class DistributionMainActivity : AppCompatActivity() {
    private var mBinding: ActivityDistributionMainBinding? = null
    private val binding get() = mBinding!!
    private var test = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDistributionMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //첫 화면 지정
        //supportFragmentManager.beginTransaction() : 어떤 대상에 대해 추가, 제거, 변경 등의 작업들이 발생하는것을 묶어서 얘기하는 것
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout2, Mission1Frag()).commit()

        //첫 화면이 켜졌을 경우 해당 아이템을 선택
        binding.bottomnavigationview.selectedItemId = R.id.mission1

        //아이템을 클릭했을때 동작하는 리스너
        //https://tedrepository.tistory.com/6 (개념 참고)
        binding.bottomnavigationview.setOnNavigationItemSelectedListener { it ->

            //Fragment Transaction 을 이용할 때,
            //이전 commit 이 있다면, 재사용시 beginTransaction() 을 재 호출해 주어야 함
            val fm = supportFragmentManager
            val transaction = fm.beginTransaction()
            when (it.itemId) {
                R.id.mission1 -> {           //다음 item을 클릭했을 시
                    if (test != "mission1") {
//                        fm.popBackStack("mission1", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout2,
                            Mission1Frag()
                        ).commit()
//                        transaction.addToBackStack("mission1")
                        test = "mission1"           //bottomnavigationview 오동작 방지
                    }
                    //클릭시 true로 클릭, 언클릭 true false 변화로 아이템 색상 지정
                    true
                }
                R.id.mission2 -> {
                    if (test != "mission2") {
//                        fm.popBackStack("mission2", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        transaction.replace(
                            R.id.framelayout2,
                            Mission2Frag()
                        ).commit()
//                        transaction.addToBackStack("mission2")
                        test = "mission2"
                    }
                    true
                }
                R.id.mission3 -> {
                    if (test != "mission3") {
//                        fm.popBackStack("mission3", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        transaction.replace(
                            R.id.framelayout2,
                            Mission3Frag()
                        ).commit()
//                        transaction.addToBackStack("mission3")
                        test = "mission3"
                    }
                    true
                }
                R.id.mission4 -> {
                    if (test != "mission4") {
//                        fm.popBackStack("mission4", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        transaction.replace(
                            R.id.framelayout2,
                            Mission4Frag()
                        ).commit()
//                        transaction.addToBackStack("mission4")
                        test = "mission4"
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