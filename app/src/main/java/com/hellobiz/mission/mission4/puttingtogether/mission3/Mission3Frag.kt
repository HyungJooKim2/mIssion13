package com.hellobiz.mission.mission4.puttingtogether.mission3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hellobiz.mission.databinding.FragmentMission3Binding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.mission3.Dialog.DialogFragment
import com.hellobiz.mission.mission4.puttingtogether.mission3.`interface`.Management
import com.hellobiz.mission.mission4.puttingtogether.mission3.model.ManagementModel
import com.hellobiz.mission.mission4.puttingtogether.mission3.model.ManagementResponse
import com.hellobiz.mission.mission4.puttingtogether.mission3.service.ManagementService

class Mission3Frag : Fragment() , Management, View.OnClickListener {
    private var mBinding: FragmentMission3Binding? = null
    private val binding get() = mBinding!!
    private var managementData : ArrayList<Manage> = ArrayList()
    private var managementData2 : ArrayList<ManagementResponse> = ArrayList()
    private var adapter : ManagementAdapter = ManagementAdapter()
    private var dialog2 = DialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMission3Binding.inflate(inflater, container, false)

        getMainRecyclerview()
        getMainService(24,17,1)
        setTabLayout()
        binding.manageText1.setOnClickListener(this)

        return binding.root
    }

    private fun putData(){
        managementData.add(Manage(12,"서울 동대문",150))
        managementData.add(Manage(11,"서울 강남",10))
        managementData.add(Manage(10,"서울 송파",300))
        managementData.add(Manage(5,"서울 광화문",500))
        managementData.add(Manage(4,"서울시내 식당그룹",50))
        adapter.notifyDataSetChanged()
    }


    private fun getMainService(a:Int,b:Int,c:Int) {
        val managementService = ManagementService(this)
        managementService.getManagementService(a,b,c)
    }

    private fun getMainRecyclerview(){
        adapter = ManagementAdapter(requireContext(), managementData2)
        binding.manageRecyclerview.adapter = adapter
        binding.manageRecyclerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    //tab을 클릭하여 service연결을 바꿔주어 mainData값을 바꿔줌
    private fun setTabLayout(){
        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> getMainService(24,17,1)
                    1 -> getMainService(30,32,1)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }


    override fun managementSuccess(managementModel: ManagementModel?) {
        when(managementModel?.code) {
            200 -> {
                try {
                    managementData2.clear()
                    managementModel.let { managementData2.addAll(it.data) }
                    adapter.notifyDataSetChanged()
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    override fun managementError(errorResponse: ErrorRespose) {

    }

    override fun managementFailure(message: Throwable?) {

    }

    override fun onClick(v: View?) {
        when(v){
            binding.manageText1 ->{
                fragmentManager?.let { dialog2.show(it,"dialogfragment") }
                dialog2.setOnItemClickListener(object : DialogFragment.DialClickListener{
                    override fun DialMemberClick(check: Boolean) {

                    }

                })
            }
        }
    }
}
