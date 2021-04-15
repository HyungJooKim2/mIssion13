package com.hellobiz.mission.mission4.puttingtogether.page3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hellobiz.mission.databinding.FragmentMission3Binding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.ClientAdapter
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.SelectDialogFragment
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.`interface`.Client
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.ClientModel
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.ClientResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.service.ClientService
import com.hellobiz.mission.mission4.puttingtogether.page3.`interface`.GroupInterface
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.ClientDetailAcitivty
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.GroupDetailActivity
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupModel
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.service.GroupService
import kotlinx.android.synthetic.main.search_dialog.*

/*
거래처 관리 페이지 Activity
 */
class Mission3Frag : Fragment(), GroupInterface, View.OnClickListener, Client {
    private var mBinding: FragmentMission3Binding? = null
    private val binding get() = mBinding!!
    private var groupData: ArrayList<GroupResponse> = ArrayList()
    private var clientData: ArrayList<ClientResponse> = ArrayList()
    private var groupAdapter: GroupAdapter = GroupAdapter()
    private var clientAdapter: ClientAdapter = ClientAdapter()
    private var selectDialog = SelectDialogFragment()
    private var a : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMission3Binding.inflate(inflater, container, false)

        getClientRecyclerview()     //거래처관리 recyclerview 기본 세팅

        //서버 연결하여 거래처관리 data 어뎁터 전송, 거래처관리 아이템 클릭 기능
        getClientService(37, "P", 40, 1)

        //tab을 클릭하여 service연결을 바꿔줌
        setTabLayout()

        if(a) {
            binding.refreshLayout.setOnRefreshListener {
                getGroupService(40, 37, 1)
                groupAdapter.notifyDataSetChanged()
                refresh_layout.isRefreshing = false
            }
        }
       else {
            binding.refreshLayout.setOnRefreshListener {
                getClientService(37, "P", 40, 1)
                clientAdapter.notifyDataSetChanged()
                refresh_layout.isRefreshing = false
            }
        }

        //클릭리스너 연동
        binding.manageText1.setOnClickListener(this)

        return binding.root
    }

    //그룹관리 서비스 연동
    private fun getGroupService(a: Int, b: Int, c: Int) {
        val managementService = GroupService(this)
        managementService.getManagementService(a, b, c)
    }

    //거래처관리 서비스 연동
    private fun getClientService(a: Int, b: String, c: Int, d: Int) {
        val clientService = ClientService(this)
        clientService.getCleintService(a, b, c, d)
    }

    //그룹관리 recyclerview 기본 세팅
    private fun getGroupRecyclerview() {
        groupAdapter = GroupAdapter(requireContext(), groupData)
        binding.manageRecyclerview.adapter = groupAdapter
        binding.manageRecyclerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    //거래처 관리 recyclerview 기본 세팅
    private fun getClientRecyclerview() {
        clientAdapter = ClientAdapter(requireContext(), clientData)
        binding.manageRecyclerview.adapter = clientAdapter
        binding.manageRecyclerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    //클라이언트 recyclerview 리스너 콜백, 클라이언트 상세화면으로 이동 및 정보 전달
    private fun getTouchableClientItem() {
        clientAdapter.setOnItemClickListener(object : ClientAdapter.ItemClickListener {

            override fun onItemClick(
                v: View?,
                cntId: Int,
                memId: Int,
                srsId: Int,
                cntSrsId: Int,
                cntPrcGrCd: Int,
                cntNm: String,
                cntAddr: String,
                cntTel: String,
                cntType: String,
                cntMemo: String
            ) {
                val intent = Intent(requireContext(), ClientDetailAcitivty::class.java)
                intent.putExtra("cntAddr", cntAddr)
                intent.putExtra("cntMemo", cntMemo)
                intent.putExtra("cntTel", cntTel)
                intent.putExtra("cntType", cntType)
                intent.putExtra("cntNm", cntNm)
                intent.putExtra("cntId",cntId)
                intent.putExtra("memId",memId)
                intent.putExtra("srsId",srsId)
                intent.putExtra("cntSrsId",cntSrsId)
                intent.putExtra("cntPrcGrCd",cntPrcGrCd)

                startActivity(intent)
            }
        })
    }

    //그룹 recyclerview 리스너 콜백, 그룹 상세화면으로 이동 및 정보 전달
    private fun getTouchableGroupItem() {
        groupAdapter.setOnItemClickListener(object : GroupAdapter.ItemClickListener {
            override fun onItemClick(v: View?, gprPer: Int, gprName: String, gprId: String) {
                val intent = Intent(requireContext(), GroupDetailActivity::class.java)
                intent.putExtra("gprPer", gprPer)
                intent.putExtra("gprName", gprName)
                intent.putExtra("gprId",gprId)
                startActivity(intent)
            }

        })
    }


    //tab을 클릭하여 service연결을 바꿔줌
    private fun setTabLayout() {
        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        getClientService(17, "L", 5, 1)
                        a=false

                    }

                    1 -> {getGroupService(40, 37, 1)
                        a=true

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun myGroupSuccess(groupModel: GroupModel?) {
        when (groupModel?.code) {
            200 -> {
                try {
                    getGroupRecyclerview()      //그룹관리 recyclerview 기본 세팅
                    getTouchableGroupItem()     //그룹 recyclerview 리스너 콜백, 그룹 상세화면으로 이동 및 정보 전달
                    groupData.clear()
                    groupModel.let { groupData.addAll(it.data) }
                    groupAdapter.notifyDataSetChanged()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun myGroupError(errorResponse: ErrorRespose) {

    }

    override fun myGroupFailure(message: Throwable?) {

    }

    override fun onClick(v: View?) {
        when (v) {
            //유통회사 textview 클릭시 가게선택 dialog 띄움
            binding.manageText1 -> {
                fragmentManager?.let { selectDialog.show(it, "dialogfragment") }
//                selectDialog.setOnItemClickListener(object :
//                    SelectDialogFragment.DialClickListener {
//                    override fun DialMemberClick(check: Boolean) {
//
//                    }
//                })
            }
        }
    }

    override fun clientSuccess(clientModel: ClientModel?) {
        when (clientModel?.code) {
            200 -> {
                try {
                    getClientRecyclerview()      //거래처 관리 recyclerview 기본 세팅
                    getTouchableClientItem()     //클라이언트 recyclerview 리스너 콜백, 클라이언트 상세화면으로 이동 및 정보 전달
                    clientData.clear()
                    clientData.addAll(clientModel.data)
                    clientAdapter.notifyDataSetChanged()
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
