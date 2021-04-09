package com.hellobiz.mission.mission4.puttingtogether.mission1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellobiz.mission.databinding.FragmentMission1Binding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission1.detailview.DetailViewActivity
import com.hellobiz.mission.store_computerization.StoreAdapter
import com.hellobiz.mission.store_computerization.interfaces.MainActivityView
import com.hellobiz.mission.store_computerization.model.MyStoreModel
import com.hellobiz.mission.store_computerization.model.MyStoreResponse
import com.hellobiz.mission.store_computerization.service.StoreService
import java.lang.Exception

//수리  및 정비 리스트
class Mission1Frag : Fragment(), MainActivityView {
    private var mBinding: FragmentMission1Binding? = null
    private val binding get() = mBinding!!
    private var storeData: ArrayList<MyStoreResponse> = ArrayList()
    private var adapter: StoreAdapter = StoreAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMission1Binding.inflate(inflater, container, false)

        getMainRecyclerview()       //recyclerview 연동
        getMainService()            //서비스 연동, response받아 온 후 adapter에 연결
        getTouchableItem()          //recyclerview item 클릭시 detailview로 화면전환

        return binding.root
    }

    //서비스 연동, response받아 온 후 adapter에 연결
    private fun getMainService() {
        val mainService = StoreService(this)
        mainService.GetMain()
    }

    //recyclerview 연동
    private fun getMainRecyclerview() {
        adapter = StoreAdapter(requireContext(), storeData)
        binding.storeRecyclerview.adapter = adapter
        binding.storeRecyclerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    //recyclerview item 클릭시 detailview로 화면전환
    private fun getTouchableItem() {
        adapter.setOnItemClickListener(object : StoreAdapter.ItemClickListener {
            override fun onItemClick(v: View?, position: Int, check: Int) {
                Toast.makeText(
                    requireContext(), "$position item checked, $check",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(requireContext(), DetailViewActivity::class.java)
                intent.putExtra("MyStoreList", check)
                startActivity(intent)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun mainSuccess(myStoreModel: MyStoreModel) {
        when (myStoreModel.code) {
            200-> {
                try {
                    storeData.addAll(myStoreModel.data)
                    adapter.notifyDataSetChanged()
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    override fun mainError(errorResponse: ErrorRespose) {

    }

    override fun mainFailure(message: Throwable?) {

    }
}