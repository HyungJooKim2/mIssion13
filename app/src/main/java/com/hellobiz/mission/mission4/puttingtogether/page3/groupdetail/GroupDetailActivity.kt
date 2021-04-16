package com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hellobiz.mission.databinding.ActivityGroupDetailBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.`interface`.GroupPatch
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupPatchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupPatchResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.service.GroupPatchService

/*
그룹관리 상세페이지 Activity
 */
class GroupDetailActivity : AppCompatActivity(), GroupPatch,View.OnClickListener {
    private lateinit var groupPatchResponse : GroupPatchResponse
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
    private fun setPatchService(a:GroupPatchResponse) {
        val groupPatchService = GroupPatchService(this)
        groupPatchService.getGroupPatchService(a)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.groupDetailSave->{
                val id :String? = intent.getStringExtra("gprId")
                groupPatchResponse = GroupPatchResponse(id!!,binding.groupName.text.toString(),binding.groupPercent.text.toString().toInt())
               setPatchService(groupPatchResponse) }
            }
        }
    }
