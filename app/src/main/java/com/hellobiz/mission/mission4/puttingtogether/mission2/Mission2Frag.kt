package com.hellobiz.mission.mission4.puttingtogether.mission2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hellobiz.mission.databinding.FragmentMission2Binding
import com.hellobiz.mission.mission2.mainview.TruckAdapter

/*
전체 매물, 내 차량 매물 Fragment
 */
class Mission2Frag : Fragment() {
    private var mBinding: FragmentMission2Binding? = null
    private val binding get() = mBinding!!
    private var listData: ArrayList<DisList> = ArrayList()
    private var adapter: ListAdapter = ListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMission2Binding.inflate(inflater, container, false)
        putData()
        getRecyclerView()     //기본 recyclerview 초기화


        return binding.root
    }

    private fun putData(){
        listData.add(DisList("신토불이 오삼불고기","서울특별시 서대문구 연희동 31","4,420,000원","1,220,000원"))
    }

    //tab을 클릭하여 service연결을 바꿔주어 mainData값을 바꿔줌
    private fun setTabLayout(){
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    //recyclerview를 adapter와 연결 및 세팅
    private fun getRecyclerView() {
        adapter = ListAdapter(requireContext(), listData)
        binding.mainRecyclerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        binding.mainRecyclerview.adapter = adapter
    }



}