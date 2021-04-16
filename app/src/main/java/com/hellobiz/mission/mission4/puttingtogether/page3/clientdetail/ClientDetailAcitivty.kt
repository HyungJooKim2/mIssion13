package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.hellobiz.mission.databinding.ActivityClientDetailAcitivtyBinding
import com.hellobiz.mission.databinding.SearchDialogBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.`interface`.GroupInterface
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.`interface`.ClientPatch
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.SearchDialogFragment
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.model.ClientPatchBody
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.model.ClientPatchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.service.ClientPatchService
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.ClientResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupModel
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.service.GroupService

/*
거래처관리 상세페이지 Activity
 */
class ClientDetailAcitivty : AppCompatActivity(), GroupInterface, View.OnClickListener,
    ClientPatch {
    private var mBinding: ActivityClientDetailAcitivtyBinding? = null
    private val binding get() = mBinding!!
    private lateinit var spinnerCustomAdpater: SpinnerCustomAdapter
    private var items3: ArrayList<String> = ArrayList()
    private val searchDialog: SearchDialogFragment = SearchDialogFragment()
    private var getName: String? = null
    private var getLocation: String? = null
    private var getTel: String? = null
    private var selItem:Int? = null
    private var getSrsId : Int? = null
    private var groupResponse : ArrayList<GroupResponse> = ArrayList()
    private var getMemId : Int? = null
    private var getCntType : String? = null
    private var cntSrsId:Int? = null
    private var cntPrcGrCd:Int? = null
    private var cntId :Int? = null

    private lateinit var clientPatchBody: ClientPatchBody
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityClientDetailAcitivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getGroupService(40, 37, 1)

        getCntType = intent.getStringExtra("cntType")       //선택한 업체의 거래처 유형
        cntSrsId = intent.getIntExtra("cntSrsId", 0)  //선택한 업체의 회원 가게 아이디
        val cntAddr = intent.getStringExtra("cntAddr")      //선택한 업체 위치
        val cntMemo = intent.getStringExtra("cntMemo")      //선택한 업체 메모
        val cntTel = intent.getStringExtra("cntTel")        //선택한 업체 전화번호
        val cntNm = intent.getStringExtra("cntNm")          //선택한 업체 이름

        //위 정보들로 켜졌을시 표시
        binding.clientName.text = cntNm
        binding.clientLocation.text = cntAddr
        binding.clientMemo.setText(cntMemo)
        binding.clientTel.text = cntTel

        /*
        쓰이지 않은 선택한 업체의 정보(쓰이지 않음)
        cntId = intent.getIntExtra("cntId", 0)
        getMemId = intent.getIntExtra("memId", 0)
        getSrsId = intent.getIntExtra("srsId", 0)
        cntPrcGrCd = intent.getIntExtra("cntPrcGrCd", 0)
        */

        getFromSearching()      //검색 후 선택한 가게의 정보를 가져옴

        binding.clientDetailSave.setOnClickListener(this)
        binding.cardView.setOnClickListener(this)

        //spinner을 통해 선택한 단가 그룹을 가져옴
        binding.clientType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                //단가그룹 저장
                selItem = groupResponse[position].gprId
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    /**
     어뎁터 전달을 외부에서 실행할 경우 오류 발생..
     **/
    //단가 그룹 목록을 가져온 후 스피너 어뎁터로 가져온 단가 그룹 목록을 전달
    override fun myGroupSuccess(groupModel: GroupModel?) {
        when (groupModel?.code) {
            200 -> {
                groupResponse.addAll(groupModel.data)
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

    //단가 그룹 목록을 가져오기 위한 서비스 연결
    private fun getGroupService(a: Int, b: Int, c: Int) {
        val managementService = GroupService(this)
        managementService.getManagementService(a, b, c)
    }

    //거래처 등록을 위한 서비스 연결
    private fun setClientPatchService(clientPatchBody: ClientPatchBody) {
        val clientPatchService = ClientPatchService(this)
        clientPatchService.getClientPatchService(clientPatchBody)
    }


    override fun onClick(v: View?) {
        when (v) {
            binding.cardView -> {
                searchDialog.show(supportFragmentManager, "search")
            }
            binding.clientDetailSave -> {
                //저장 입력시 patch수행
                clientPatchBody =
                    ClientPatchBody(22, 37, "P", 40, cntSrsId!!, getCntType!!, selItem!!    , binding.clientMemo.text.toString())
                setClientPatchService(clientPatchBody)
            }
        }
    }

    //검색창으로 부터 선택한 값을 가져옴
    private fun getFromSearching() {
        searchDialog.setOnItemClickListener(object : SearchDialogFragment.ItemClickListener {
            override fun onItemClick(
                name: String,
                location: String,
                tel: String,
                srsId: Int,
                cntType: String
            ) {
                getName = name          //거래처 이름
                getLocation = location  //거래처 위치
                getTel = tel            //거래처 전화번호
                getSrsId = srsId        //거래처 가게 아이디
                getCntType = cntType    //거래처 유형

                //가져온 값으로 setText
                if (getName != null && getLocation != null && getTel != null) {
                    binding.clientName.text = getName
                    binding.clientLocation.text = getLocation
                    binding.clientTel.text = getTel
                }
            }
        })
    }

    override fun clientPatchSuccess(clientPatchModel: ClientPatchModel?) {
        when (clientPatchModel?.code) {
            200 -> {
                Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun clientPatchError(errorResponse: ErrorRespose) {

    }

    override fun clientPatchFailure(message: Throwable?) {

    }

}