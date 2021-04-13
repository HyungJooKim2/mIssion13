package com.hellobiz.mission.mission4.puttingtogether.page2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hellobiz.mission.databinding.FragmentMission2Binding
import com.hellobiz.mission.mission4.puttingtogether.page2.model.DisList

/*
거래처별, 품목별 장부 Fragment
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
        getTouchableItem()    //거래처 아이템 클릭 시 상세 내역으로 이동 및 정보 전달

        return binding.root
    }

    private fun putData(){
        listData.add(DisList("신토불이 오삼불고기","서울특별시 서대문구 연희동 31","4,420,000원","1,220,000원"))
        listData.add(DisList("연희식당","서울특별시 서대문구 연희동 11-1","14,620,000원","4,200,000원"))
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

    //장부 recyclerview를 adapter와 연결 및 세팅
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

    //거래처 아이템 클릭 시 상세 내역으로 이동 및 정보 전달
    private fun getTouchableItem(){
            adapter.setOnItemClickListener(object : ListAdapter.ItemClickListener {
                override fun onItemClick(
                    v: View?,
                    store: String,
                    location: String,
                    sales: String,
                    profit: String
                ) {
                    val intent = Intent(requireContext(),BookDetailActivity::class.java)
                    intent.putExtra("store",store)
                    intent.putExtra("location",location)
                    intent.putExtra("sales",sales)
                    intent.putExtra("profit",profit)
                    startActivity(intent)
                }
            })
    }
}
