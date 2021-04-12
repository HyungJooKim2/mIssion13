package com.hellobiz.mission.mission4.puttingtogether.page3.Dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.databinding.DialogItemBinding
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.model.DialogResponse

class DialogAdapter(): RecyclerView.Adapter<DialogAdapter.DialogViewHolder>(){
    private var listener: ItemClickListener? = null
    private var mList : ArrayList<DialogResponse>? = null
    private var context : Context? = null

    constructor(context: Context?, datas: ArrayList<DialogResponse>) : this() {
        //엑티비티에서 넘어온 data를 Adapter의 mList에 대입
        mList = datas
        this.context = context
    }

    //position과 check 여부를 알려주는 리스너 콜백을 정의
    interface ItemClickListener{
        fun onItemClick(v: View?, position: Int, check: Int)
    }

    //리스너에 클릭리스너 연결
    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val binding = DialogItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return DialogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        holder.bind(mList!![position])
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class DialogViewHolder(val binding: DialogItemBinding) : RecyclerView.ViewHolder(binding.root){
        //서버에서 받아온 값으로 setText
        fun bind(dialog : DialogResponse){
            binding.itemId.text = dialog.srsId.toString()
            binding.itemName.text = dialog.srsNm
            binding.itemSrsAddr1.text = dialog.srsAddr1
            binding.itemSrsAddr2.text = dialog.srsAddr2
        }

//        init {
//            // 해당 view에 클릭 리스너를 붙인다
//            binding.bind.setOnClickListener{v->
//                val pos = adapterPosition
//                if (pos != RecyclerView.NO_POSITION){
//                    if (listener != null) {
//                        listener!!.onItemClick(v,pos,mList!![pos].id)
//                    }
//                }
//            }
//        }
    }
}