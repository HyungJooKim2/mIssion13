package com.hellobiz.mission1.Store_computerization

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission1.R
import com.hellobiz.mission1.Store_computerization.model.MyStoreResponse


class Store_Adapter() : RecyclerView.Adapter<Store_Adapter.StoreViewHolder>(){
    private var listener: ItemClickListener? = null
    private var mList : ArrayList<MyStoreResponse>? = null
    private var context : Context? = null
    constructor(context: Context?, Stores: ArrayList<MyStoreResponse>?) : this() {
        // 프래그먼트에서 넘어온 Selections를 Adapter의 mList에 대입
        mList = Stores
        this.context = context
    }

    interface ItemClickListener{
        fun onItemClick(v: View?, position: Int, check: Boolean)
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.store_item2,parent,false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.REQ_NAME.text = mList?.get(position)?.REQ_NAME
        holder.MEM_NICK.text = mList?.get(position)?.MEM_NICK
        holder.AREA_NM.text = mList?.get(position)?.AREA_NM
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class StoreViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val REQ_NAME : TextView = view.findViewById(R.id.REQ_NAME)
        val MEM_NICK : TextView = view.findViewById(R.id.MEM_NICK)
        val AREA_NM : TextView = view.findViewById(R.id.AREA_NM)

        init {
            view.setOnClickListener{v->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION){
                    if (listener != null) {
                    }
                }
            }
        }
    }
}