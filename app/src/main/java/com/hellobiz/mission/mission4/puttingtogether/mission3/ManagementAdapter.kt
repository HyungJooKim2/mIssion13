package com.hellobiz.mission.mission4.puttingtogether.mission3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.databinding.ManageItemBinding
import com.hellobiz.mission.mission4.puttingtogether.mission3.model.ManagementResponse

class ManagementAdapter() : RecyclerView.Adapter<ManagementAdapter.ManagementViewHolder>() {
    private var mList: ArrayList<ManagementResponse>? = null
    private var context: Context? = null

    constructor(context: Context?, Stores: ArrayList<ManagementResponse>) : this() {
        //엑티비티에서 넘어온 Stores를 Adapter의 mList에 대입
        mList = Stores
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagementViewHolder {
        val binding = ManageItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ManagementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ManagementViewHolder, position: Int) {
        holder.bind(mList!![position])
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

//    inner class ManagementViewHolder(val binding: ManageItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(params: Manage) {
//            binding.manageId.text = params.gprId.toString()
//            binding.manageGrade.text = params.gprName
//            binding.managePercent.text = params.gprPer.toString()
//        }
//    }

    inner class ManagementViewHolder(val binding: ManageItemBinding) : RecyclerView.ViewHolder(binding.root){
        //서버에서 받아온 값으로 setText
        fun bind(params : ManagementResponse){
            binding.manageId.text = params.gprId.toString()
            binding.manageGrade.text = params.gprName
            binding.managePercent.text = params.gprPer.toString()
        }
    }
}