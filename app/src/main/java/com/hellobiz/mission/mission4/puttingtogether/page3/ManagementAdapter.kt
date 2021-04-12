package com.hellobiz.mission.mission4.puttingtogether.page3

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.databinding.ManageItemBinding
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.ClientAdapter
import com.hellobiz.mission.mission4.puttingtogether.page3.model.ManagementResponse

class ManagementAdapter() : RecyclerView.Adapter<ManagementAdapter.ManagementViewHolder>() {
    private var listener: ManagementAdapter.ItemClickListener? = null
    private var mList: ArrayList<ManagementResponse>? = null
    private var context: Context? = null

    constructor(context: Context?, Stores: ArrayList<ManagementResponse>) : this() {
        //엑티비티에서 넘어온 Stores를 Adapter의 mList에 대입
        mList = Stores
        this.context = context
    }

    //position과 check 여부를 알려주는 리스너 콜백을 정의
    interface ItemClickListener{
        fun onItemClick(v: View?, gprPer:Int, gprName:String)
    }

    //리스너에 클릭리스너 연결
    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
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

    inner class ManagementViewHolder(val binding: ManageItemBinding) : RecyclerView.ViewHolder(binding.root){
        //서버에서 받아온 값으로 setText
        @SuppressLint("SetTextI18n")
        fun bind(params : ManagementResponse){
            binding.manageId.text = params.gprId.toString()
            binding.manageGrade.text = params.gprName
            binding.managePercent.text = "("+params.gprPer.toString()+"%)"
        }
        init {
            // 해당 view에 클릭 리스너를 붙인다
            binding.bind.setOnClickListener{v->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION){
                    if (listener != null) {
                        listener!!.onItemClick(v,mList!![pos].gprPer,mList!![pos].gprName)
                    }
                }
            }
        }
    }


}