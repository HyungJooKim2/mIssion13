package com.hellobiz.mission.mission4.puttingtogether.page3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hellobiz.mission.databinding.FragmentMission3Binding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission1.detailview.DetailViewActivity
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.ClientAdapter
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.DialogFragment
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.`interface`.Client
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.model.ClientModel
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.model.ClientResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.service.ClientService
import com.hellobiz.mission.mission4.puttingtogether.page3.`interface`.Management
import com.hellobiz.mission.mission4.puttingtogether.page3.model.ManagementModel
import com.hellobiz.mission.mission4.puttingtogether.page3.model.ManagementResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.service.ManagementService
import com.hellobiz.mission.store_computerization.StoreAdapter

class Mission3Frag : Fragment(), Management, View.OnClickListener, Client {
    private var mBinding: FragmentMission3Binding? = null
    private val binding get() = mBinding!!
    private var managementData2: ArrayList<ManagementResponse> = ArrayList()
    private var clientData: ArrayList<ClientResponse> = ArrayList()
    private var adapter: ManagementAdapter = ManagementAdapter()
    private var adapter2: ClientAdapter = ClientAdapter()
    private var dialog2 = DialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMission3Binding.inflate(inflater, container, false)

        getClientRecyclerview()
        getClientService(17, "L", 5, 1)
        setTabLayout()
        binding.manageText1.setOnClickListener(this)

        return binding.root
    }


    private fun getMainService(a: Int, b: Int, c: Int) {
        val managementService = ManagementService(this)
        managementService.getManagementService(a, b, c)
    }

    private fun getClientService(a: Int, b: String, c: Int, d: Int) {
        val clientService = ClientService(this)
        clientService.getCleintService(a, b, c, d)
    }

    private fun getMainRecyclerview() {
        adapter = ManagementAdapter(requireContext(), managementData2)
        binding.manageRecyclerview.adapter = adapter
        binding.manageRecyclerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    private fun getClientRecyclerview() {
        adapter2 = ClientAdapter(requireContext(), clientData)
        binding.manageRecyclerview.adapter = adapter2
        binding.manageRecyclerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    private fun getTouchableClientItem() {
        adapter2.setOnItemClickListener(object : ClientAdapter.ItemClickListener {
            override fun onItemClick(
                v: View?,
                cntAddr: String,
                cntMemo: String,
                cntTel: String,
                cntType: String,
                cntNm: String
            ) {
                val intent = Intent(requireContext(),ClientDetailAcitivty::class.java)
                intent.putExtra("cntAddr",cntAddr)
                intent.putExtra("cntMemo",cntMemo)
                intent.putExtra("cntTel",cntTel)
                intent.putExtra("cntType",cntType)
                intent.putExtra("cntNm",cntNm)
                startActivity(intent)
            }
        })
    }

    private fun getTouchableGroupItem(){
        adapter.setOnItemClickListener(object : ManagementAdapter.ItemClickListener{
            override fun onItemClick(v: View?, gprPer: Int, gprName: String) {
                val intent = Intent(requireContext(),GroupDetailActivity::class.java)
                intent.putExtra("gprPer",gprPer)
                intent.putExtra("gprName",gprName)
                startActivity(intent)
            }

        })
    }



    //tab을 클릭하여 service연결을 바꿔주어 mainData값을 바꿔줌
    private fun setTabLayout() {
        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> getClientService(17, "L", 5, 1)
                    1 -> getMainService(30, 32, 1)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun managementSuccess(managementModel: ManagementModel?) {
        when (managementModel?.code) {
            200 -> {
                try {
                    getMainRecyclerview()
                    getTouchableGroupItem()
                    managementData2.clear()
                    managementModel.let { managementData2.addAll(it.data) }
                    adapter.notifyDataSetChanged()
                } catch (e: Exception) {
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
        when (v) {
            binding.manageText1 -> {
                fragmentManager?.let { dialog2.show(it, "dialogfragment") }
                dialog2.setOnItemClickListener(object : DialogFragment.DialClickListener {
                    override fun DialMemberClick(check: Boolean) {

                    }
                })
            }
        }
    }

    override fun clientSuccess(clientModel: ClientModel?) {
        when (clientModel?.code) {
            200 -> {
                try {
                    getClientRecyclerview()
                    getTouchableClientItem()
                    clientData.clear()
                    clientData.addAll(clientModel.data)
                    adapter2.notifyDataSetChanged()
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun clientError(errorResponse: ErrorRespose) {

    }

    override fun clientFailure(message: Throwable?) {

    }
}
