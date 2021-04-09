package com.hellobiz.mission.mission4.puttingtogether.mission2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hellobiz.mission.databinding.FragmentMission2Binding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission2.mainview.TruckAdapter
import com.hellobiz.mission.mission2.myvehicleforsale.myvehicleinterface.MyVehicle
import com.hellobiz.mission.mission2.myvehicleforsale.service.MyVehicleService
import com.hellobiz.mission2.mainview.maininterface.MainView
import com.hellobiz.mission2.mainview.model.MainViewModel
import com.hellobiz.mission2.mainview.model.MainViewResponse
import com.hellobiz.mission2.mainview.service.MainViewService
import java.lang.Exception

/*
전체 매물, 내 차량 매물 Fragment
 */
class Mission2Frag : Fragment(), MyVehicle, MainView {
    private var mBinding: FragmentMission2Binding? = null
    private val binding get() = mBinding!!
    private var mainData: ArrayList<MainViewResponse> = ArrayList()
    private var adapter: TruckAdapter = TruckAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMission2Binding.inflate(inflater, container, false)
        getRecyclerView()     //기본 recyclerview 초기화
        getMainViewService()  //기존 화면을 전체 매물 service로 연결 adpater로 data 전송
        setTabLayout()        //tab을 클릭하여 service연결을 바꿔주어 mainData값을 바꿔줌
        return binding.root
    }

    //tab을 클릭하여 service연결을 바꿔주어 mainData값을 바꿔줌
    private fun setTabLayout(){
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> getMainViewService()
                    1 -> getMyVehicleData()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    //전체 매물 service 연결
    private fun getMainViewService() {
        val mainViewService = MainViewService(this)
        mainViewService.getMainView()
    }

    //내 차량 매물 service 연결
    private fun getMyVehicleData() {
        val myVehicleService = MyVehicleService(this)
        myVehicleService.getMyVehicle()
    }

    //recyclerview를 adapter와 연결 및 세팅
    private fun getRecyclerView() {
        adapter = TruckAdapter(requireContext(), mainData)
        binding.mainRecyclerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        binding.mainRecyclerview.adapter = adapter
    }

    override fun mainViewSuccess(mainViewModel: MainViewModel?) {
        try {
            mainData.clear()
            mainData.addAll(mainViewModel!!.data)
            Log.d("maindata",mainData.toString())
            adapter.notifyDataSetChanged()
        } catch (e: Exception) {  //입출력에 대한 오류 내용
            e.printStackTrace()   //오류 출력
        }
    }

    override fun mainViewError(errorResponse: ErrorRespose) {
    }

    override fun mainViewFailure(message: Throwable?) {
        Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun myVehicleSuccess(myVehicleModel: MainViewModel?) {
        try {
            mainData.clear()
            mainData.addAll(myVehicleModel!!.data)
            Log.d("maindata2",mainData.toString())
            adapter.notifyDataSetChanged()
        } catch (e: Exception) { //입출력에 대한 오류 내용
            e.printStackTrace()  //오류 출력
        }
    }

    override fun myVehicleError(errorResponse: ErrorRespose) {
    }

    override fun myVehicleFailure(message: Throwable?) {
        Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_SHORT).show()
    }

}
