package com.hellobiz.mission.mission4.puttingtogether.page3.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.databinding.ClientItemBinding
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.ClientResponse
/*
거래처 리스트 Adapter
 */
class ClientAdapter(): RecyclerView.Adapter<ClientAdapter.ClientViewHolder>(){
    private var listener: ItemClickListener? = null
    private var mList : ArrayList<ClientResponse>? = null
    private var context : Context? = null

    constructor(context: Context?, datas: ArrayList<ClientResponse>) : this() {
        //엑티비티에서 넘어온 data를 Adapter의 mList에 대입
        mList = datas
        this.context = context
    }

    //거래처 주소, 거래처 메모, 거래처 전화번호, 거래처 타입, 거래처 명을 알려주는 리스너 콜백을 정의
    interface ItemClickListener{
        fun onItemClick(v: View?,cntAddr:String,cntMemo:String,cntTel:String,cntType:String,cntNm:String)
    }

    //리스너에 클릭리스너 연결
    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val binding = ClientItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind(mList!![position])
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class ClientViewHolder(val binding: ClientItemBinding) : RecyclerView.ViewHolder(binding.root){
        //서버에서 받아온 값으로 setText
        fun bind(client : ClientResponse){
            binding.itemClientName.text = client.cntNm
            binding.itemClientLocation.text = client.cntAddr
        }

        init {
            // 해당 view에 클릭 리스너를 붙인다
            binding.bind.setOnClickListener{v->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION){
                    if (listener != null) {
                        listener!!.onItemClick(v,mList!![pos].cntAddr,mList!![pos].cntMemo,mList!![pos].cntTel,mList!![pos].cntType,mList!![pos].cntNm)
                    }
                }
            }
        }
    }
}