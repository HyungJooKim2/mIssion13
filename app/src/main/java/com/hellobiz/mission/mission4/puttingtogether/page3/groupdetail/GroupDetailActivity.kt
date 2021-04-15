package com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hellobiz.mission.databinding.ActivityGroupDetailBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.`interface`.GroupPatch
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupPatchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.service.GroupPatchService
import com.hellobiz.mission.mission4.puttingtogether.page3.service.GroupService
import kotlinx.android.synthetic.main.activity_client_detail_acitivty.*

/*
그룹관리 상세페이지 Activity
 */
class GroupDetailActivity : AppCompatActivity(), GroupPatch,View.OnClickListener {
    private lateinit var groupResponse : GroupResponse
    private var mBinding: ActivityGroupDetailBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGroupDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.groupPercent.setText(intent.getIntExtra("gprPer", 0).toString())
        binding.groupName.setText(intent.getStringExtra("gprName"))
        binding.groupDetailSave.setOnClickListener(this)
    }

    override fun groupPatchSuccess(groupPatchModel: GroupPatchModel?) {
        when(groupPatchModel?.code){
            200->{
                Toast.makeText(this,"수정 성공",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun groupPatchError(errorResponse: ErrorRespose) {

    }

    override fun groupPatchFailure(message: Throwable?) {

    }

    //단가 그룹 수정 인터페이스 연결
    private fun setPatchService(a:GroupResponse) {
        val groupPatchService = GroupPatchService(this)
        groupPatchService.getGroupPatchService(a)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.groupDetailSave->{
                val id :String? = intent.getStringExtra("gprId")
                groupResponse = GroupResponse(id!!,binding.groupName.text.toString(),binding.groupPercent.text.toString().toInt())
               setPatchService(groupResponse) }
            }
        }
    }
