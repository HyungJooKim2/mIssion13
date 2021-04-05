package com.hellobiz.mission2.mainview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellobiz.mission1.databinding.ActivityTruckBinding
import com.hellobiz.mission1.error.model.ErrorRespose
import com.hellobiz.mission2.mainview.Interface.MainView
import com.hellobiz.mission2.mainview.model.MainViewModel
import com.hellobiz.mission2.mainview.model.MainViewResponse
import com.hellobiz.mission2.mainview.service.MainViewService
import java.lang.Exception

class TruckActivity : AppCompatActivity(), MainView {
    private var mBinding: ActivityTruckBinding? = null
    private val binding get() = mBinding!!
    private var storeData: ArrayList<MainViewResponse> = ArrayList()
    private var adapter = TruckAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTruckBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMainViewService()

    }

    //recyclerview를 adapter와 연결 및 세팅
    private fun getRecyclerView(){
        adapter = TruckAdapter(this,storeData)
        binding.mainRecyclerview.adapter = adapter
        binding.mainRecyclerview.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    //MainViewService를 해당 Activity와 연결
    private fun getMainViewService() {
        val mainViewService = MainViewService(this)
        mainViewService.getMainView()
    }

    override fun MainViewSuccess(myViewModel: MainViewModel?) {
        try {
            storeData = myViewModel!!.data
            getRecyclerView()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun MainViewError(errorResponse: ErrorRespose) {

    }

    override fun MainViewFailure(message: Throwable?) {

    }


}