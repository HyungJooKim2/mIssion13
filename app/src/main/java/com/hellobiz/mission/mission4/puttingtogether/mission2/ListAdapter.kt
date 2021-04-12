package com.hellobiz.mission.mission4.puttingtogether.mission2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.databinding.ListItemBinding

class ListAdapter(): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private var mList: ArrayList<DisList>? = null
    private var context: Context? = null


    constructor(context: Context?, lists: ArrayList<DisList>) : this() {
        //엑티비티에서 넘어온 Trucks를 Adapter의 mList에 대입
        mList = lists
        this.context = context
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mList!![position])

    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    class ListViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //MainViewResponse 자료형의 데이터를 넣으면 그 데이터로 imageview 또는 textview 출력
        @SuppressLint("SetTextI18n")
        fun bind(list : DisList){
            binding.text1.text = list.a
            binding.text2.text = list.c
            binding.text3.text = list.b
            binding.text4.text = list.d
        }
    }
}
