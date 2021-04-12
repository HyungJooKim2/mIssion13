package com.hellobiz.mission.mission4.puttingtogether.page3.Dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellobiz.mission.databinding.DialogLayoutBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.`interface`.Dialog
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.model.DialogModel
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.model.DialogResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.service.DialogService

class DialogFragment() : DialogFragment(),
         Dialog {

    private var mBinding: DialogLayoutBinding? = null
    private val binding get() = mBinding!!
    private var listener2 : DialClickListener? = null
    private var dialogData : ArrayList<DialogResponse> = ArrayList()
    private var adapter : DialogAdapter = DialogAdapter()
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DialogLayoutBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        setRecyclerview()
        val mArgs = arguments
//        val mValue = mArgs!!.getString("key")

        binding.dialogSelect.setOnClickListener {
            dismiss()
        }

        binding.dialogUnselect.setOnClickListener {
            dismiss()
        }
        getDialogService(17,"L",1)

        return binding.root
    }

    interface DialClickListener {       //리스너 인터페이스를 만듬
        fun DialMemberClick(check: Boolean)
    }
    fun setOnItemClickListener(listener2: DialClickListener) {
        this.listener2 = listener2
    }

    private fun setRecyclerview(){
        adapter = DialogAdapter(requireContext(), dialogData)
        binding.dialogRecycerview.adapter = adapter
        binding.dialogRecycerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

    }
    override fun dialogSuccess(dialogModel: DialogModel?) {
        when(dialogModel?.code){
            200->{
                dialogData.clear()
                dialogData.addAll(dialogModel.data)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun dialogError(errorResponse: ErrorRespose) {

    }

    override fun dialogFailure(message: Throwable?) {

    }

    private fun getDialogService(a:Int,b:String,c:Int) {
        val dialogService = DialogService(this)
        dialogService.getDialogtService(a,b,c)
    }
}