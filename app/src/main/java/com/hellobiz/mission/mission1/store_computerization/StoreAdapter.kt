package com.hellobiz.mission.store_computerization

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.MainItem2Binding
import com.hellobiz.mission.databinding.StoreItem2Binding
import com.hellobiz.mission.mission2.mainview.TruckAdapter
import com.hellobiz.mission.store_computerization.model.MyStoreResponse
import com.hellobiz.mission2.mainview.model.MainViewResponse


class StoreAdapter() : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>(){
    private var listener: ItemClickListener? = null
    private var mList : ArrayList<MyStoreResponse>? = null
    private var context : Context? = null

    constructor(context: Context?, Stores: ArrayList<MyStoreResponse>?) : this() {
        //엑티비티에서 넘어온 Stores를 Adapter의 mList에 대입
        mList = Stores
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = StoreItem2Binding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(mList!![position])
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class StoreViewHolder(val binding: StoreItem2Binding) :RecyclerView.ViewHolder(binding.root){
        //서버에서 받아온 값으로 setText
        fun bind(store : MyStoreResponse){
            binding.reqName.text = store.reqName
            binding.memNick.text = store.memNick
            binding.areaNm.text = store.areaNm
            binding.index.text = store.id.toString()
        }

        init {
            // 해당 view에 클릭 리스너를 붙인다
            binding.bind.setOnClickListener{v->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION){
                    if (listener != null) {
                        listener!!.onItemClick(v,pos,mList!![pos].id)
                    }
                }
            }
        }
    }
}