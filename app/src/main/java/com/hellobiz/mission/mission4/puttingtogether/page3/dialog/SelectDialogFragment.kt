package com.hellobiz.mission.mission4.puttingtogether.page3.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellobiz.mission.databinding.DialogLayoutBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.`interface`.Dialog
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.DialogModel
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.DialogResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.service.DialogService

/*
가게 선택 Dialog Fragment
 */
class SelectDialogFragment() : DialogFragment(),
    Dialog,View.OnClickListener {

    private var mBinding: DialogLayoutBinding? = null
    private val binding get() = mBinding!!
    //private var listener2: DialClickListener? = null
    private var dialogData: ArrayList<DialogResponse> = ArrayList()
    private var adapter: DialogAdapter = DialogAdapter()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DialogLayoutBinding.inflate(inflater, container, false)

        //본 다이얼로그 생성 스타일을 따르지 않고 생성
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //액션바 타이틀 제거
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        setRecyclerview()    //가게 선택 recyclerview 기본 세팅

        binding.dialogSelect.setOnClickListener(this)
        binding.dialogUnselect.setOnClickListener(this)

        getDialogService(17, "L", 1)    //가게 선택 리스트 서버 연결

        return binding.root
    }

//    interface DialClickListener {
//        fun DialMemberClick(check: Boolean)
//    }

//    fun setOnItemClickListener(listener2: DialClickListener) {
//        this.listener2 = listener2
//    }

    //가게 선택 recyclerview 기본 세팅
    private fun setRecyclerview() {
        adapter = DialogAdapter(requireContext(), dialogData)
        binding.dialogRecycerview.adapter = adapter
        binding.dialogRecycerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        //구분선 추가
        binding.dialogRecycerview.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

    }

    override fun dialogSuccess(dialogModel: DialogModel?) {
        when (dialogModel?.code) {
            200 -> {
                dialogData.clear()
                dialogData.addAll(dialogModel.data)
                setRadioButtonEnable()
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun dialogError(errorResponse: ErrorRespose) {

    }

    override fun dialogFailure(message: Throwable?) {

    }
    //가게 선택 리스트 서버 연결
    private fun getDialogService(a: Int, b: String, c: Int) {
        val dialogService = DialogService(this)
        dialogService.getDialogtService(a, b, c)
    }

    //몇번째 라디오 버튼이 선택되었는지 및 체크 여부 콜백하여 받아옴
    private fun setRadioButtonEnable() {
        adapter.setOnItemClickListener(object : DialogAdapter.ItemClickListener {
            override fun onItemClick(v: View?, position: Int, check: Boolean) {

            }
        })
    }

    override fun onClick(v: View?) {
        when(v){
            binding.dialogSelect->{
                dismiss()       //선택시 동작 될 부분
            }
            binding.dialogUnselect->{
                dismiss()       //취소시 동작 될 부분
            }
        }
    }
}