package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.hellobiz.mission.databinding.ActivityClientDetailAcitivtyBinding
import com.hellobiz.mission.databinding.SearchDialogBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.`interface`.GroupInterface
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.SearchDialogFragment
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupModel
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.service.GroupService

/*
거래처관리 상세페이지 Activity
 */
class ClientDetailAcitivty : AppCompatActivity(), GroupInterface,View.OnClickListener {
    private var mBinding: ActivityClientDetailAcitivtyBinding? = null
    private val binding get() = mBinding!!
    private lateinit var spinnerCustomAdpater: SpinnerCustomAdapter
    private var groupData: ArrayList<GroupResponse> = ArrayList()
    private val items = arrayOf("A", "B", "C", "D")
    private var items2: Array<String> = arrayOf("")
    private var items3 : ArrayList<String> = ArrayList()
    private val searchDialog : SearchDialogFragment = SearchDialogFragment()
    private var getName : String? = null
    private var getLocation : String? = null
    private var getTel : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityClientDetailAcitivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getGroupService(40, 37, 1)
        val cntAddr = intent.getStringExtra("cntAddr")
        val cntMemo = intent.getStringExtra("cntMemo")
        val cntTel = intent.getStringExtra("cntTel")
        val cntType = intent.getStringExtra("cntType")
        val cntNm = intent.getStringExtra("cntNm")
        getFromSearching()

        binding.cardView.setOnClickListener(this)
        binding.clientType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selItem = binding.clientType.selectedItem
                // 아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작
                when (position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {

                    }
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        binding.clientName.text = cntNm
        binding.clientLocation.text = cntAddr
        binding.clientMemo.setText(cntMemo)
        binding.clientTel.text = cntTel
        //binding.clientType.setText(cntType)
    }

    override fun myGroupSuccess(groupModel: GroupModel?) {
        when (groupModel?.code) {
            200 -> {
                for (i in 0 until groupModel.data.size) {
                    items3.add(groupModel.data[i].gprName)
                }
                spinnerCustomAdpater = SpinnerCustomAdapter(this, items3)
                binding.clientType.adapter = spinnerCustomAdpater


            }
        }
    }

    override fun myGroupError(errorResponse: ErrorRespose) {

    }

    override fun myGroupFailure(message: Throwable?) {

    }

    private fun getGroupService(a: Int, b: Int, c: Int) {
        val managementService = GroupService(this)
        managementService.getManagementService(a, b, c)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.cardView->{
                searchDialog.show(supportFragmentManager,"search")
            }
        }
    }
    private fun getFromSearching(){
        searchDialog.setOnItemClickListener(object: SearchDialogFragment.ItemClickListener{
            override fun onItemClick(name: String, location: String, tel: String) {
                getName = name
                getLocation = location
                getTel = tel
            }
        })
        if(getName!=null&&getLocation!=null&&getTel!=null){
            binding.clientName.text = getName
            binding.clientLocation.text = getLocation
            binding.clientTel.text = getTel
        }

    }

}