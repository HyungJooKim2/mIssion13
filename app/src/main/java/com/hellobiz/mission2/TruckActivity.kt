package com.hellobiz.mission2.mainview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellobiz.mission1.databinding.ActivityTruckBinding
import com.hellobiz.mission1.error.model.ErrorRespose
import com.hellobiz.mission2.mainview.maininterface.MainView
import com.hellobiz.mission2.mainview.model.MainViewModel
import com.hellobiz.mission2.mainview.model.MainViewResponse
import com.hellobiz.mission2.mainview.service.MainViewService
import java.lang.Exception

class TruckActivity : AppCompatActivity(), MainView {
    private var mBinding: ActivityTruckBinding? = null
    private val binding get() = mBinding!!
    private var mainData: ArrayList<MainViewResponse> = ArrayList()
    private lateinit var adapter :TruckAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTruckBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRecyclerView()
        getMainViewService()

    }

    //recyclerview를 adapter와 연결 및 세팅
    private fun getRecyclerView() {
        adapter = TruckAdapter(this, mainData)
        binding.mainRecyclerview.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        binding.mainRecyclerview.adapter = adapter

    }

    override fun mainViewSuccess(mainViewModel: MainViewModel?) {
        mainData.clear()
        try {
            mainData = mainViewModel!!.data  //서버로 부터 받은 응답값을 리스트에 넣어줌
            adapter.notifyDataSetChanged()
//            adapter.notifyItemRangeChanged(0,mainData.size)
//            adapter = TruckAdapter(this, mainData)
            Log.d("mainData", mainData.toString())
        } catch (e: Exception) {    //입출력에 대한 오류 내용
            e.printStackTrace()  //오류 출력
        }

    }

    //MainViewService를 해당 Activity와 연결
    private fun getMainViewService() {
        val mainViewService = MainViewService(this)
        mainViewService.getMainView()
    }

    override fun mainViewError(errorResponse: ErrorRespose) {

    }

    override fun mainViewFailure(message: Throwable?) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
    }
}