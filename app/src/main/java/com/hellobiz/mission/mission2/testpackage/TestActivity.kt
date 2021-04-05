package com.hellobiz.mission.mission2.testpackage

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.ActivityTruckBinding
import com.hellobiz.mission.mission2.mainview.TruckAdapter
import com.hellobiz.mission2.mainview.model.MainViewResponse
import java.lang.Exception

class TestActivity : AppCompatActivity() {
    private var mainData: ArrayList<Test> = ArrayList()
    private var mainData2 : ArrayList<Test> = ArrayList()
    private lateinit var adapter : TestAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        getRecyclerView()
        putdata()
    }
    @SuppressLint("CutPasteId")
    private fun getRecyclerView() {
        adapter = TestAdapter(mainData2)
        findViewById<RecyclerView>(R.id.test_recyclerview).layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        findViewById<RecyclerView>(R.id.test_recyclerview).adapter = adapter
    }
    private fun putdata(){
        try{
        mainData.add(Test("http://15.165.252.49:9090/images/mycar/C-01-4-20210227025927.png","현대","봉고","카고",6,"서울특별시","강남구",4300,4360))
        mainData.add(Test("http://15.165.252.49:9090/images/mycar/C-01-4-20210227025628.png","현대","봉고","카고",6,"서울특별시","강남구",4300,4360))
        mainData.add(Test("http://15.165.252.49:9090/images/mycar/C-01-4-20210227023651.png","현대","봉고","카고",6,"서울특별시","강남구",4300,4360))
                mainData2 = mainData

            adapter.notifyDataSetChanged()
    }catch (e:Exception){
            e.printStackTrace()
        }}
}