package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.hellobiz.mission.databinding.ItemSpinnerBinding

class SpinnerCustomAdapter() : BaseAdapter() {
    private var mList : ArrayList<String>? = null
    private var context : Context? = null

    constructor(context: Context?, types: ArrayList<String>) : this() {
        //엑티비티에서 넘어온 types를 Adapter의 mList에 대입
        mList = types
        this.context = context
    }


    override fun getCount(): Int {
        return mList!!.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemSpinnerBinding.inflate(
            LayoutInflater.from(context),parent,false)

        binding.spinnerType.text = mList!![position]

        return binding.root
    }
}