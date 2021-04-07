package com.hellobiz.mission.mission3.distributionmain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.ActivityDistributionMainBinding
import com.hellobiz.mission.mission3.distributionmain.add.AddFrag
import com.hellobiz.mission.mission3.distributionmain.book.BookFrag
import com.hellobiz.mission.mission3.distributionmain.home.HomeFrag
import com.hellobiz.mission.mission3.distributionmain.list.ListFrag

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
        fragmentTransaction.replace(R.id.framelayout, ListFrag()).commit()

        //첫 화면이 켜졌을 경우 해당 아이템을 선택
        binding.bottomnavigationview.selectedItemId = R.id.bottom_list

        //아이템을 클릭했을때 동작하는 리스너
        //https://tedrepository.tistory.com/6 (개념 참고)
        binding.bottomnavigationview.setOnNavigationItemSelectedListener { it ->

            //Fragment Transaction 을 이용할 때,
            //이전 commit 이 있다면, 재사용시 beginTransaction() 을 재 호출해 주어야 함
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.bottom_list -> {           //다음 item을 클릭했을 시
                    if (!test.equals("list")) {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout,
                            ListFrag()
                        ).commit()
                        test = "list"           //bottomnavigationview 오동작 방지
                    }
                    //클릭시 true로 클릭, 언클릭 true false 변화로 아이템 색상 지정
                    true
                }
                R.id.bottom_add -> {
                    if (!test.equals("add")) {
                        transaction.replace(
                            R.id.framelayout,
                            AddFrag()
                        ).commit()
                        test = "add"
                    }
                    true
                }
                R.id.bottom_home -> {
                    if (!test.equals("home")) {
                        transaction.replace(
                            R.id.framelayout,
                            HomeFrag()
                        ).commit()
                        test = "home"
                    }
                    true
                }
                R.id.bottom_book -> {
                    if (!test.equals("book")) {
                        transaction.replace(
                            R.id.framelayout,
                            BookFrag()
                        ).commit()
                        test = "book"
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