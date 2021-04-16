package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.hellobiz.mission.R
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupResponse

class SpinnerCustomAdapter() : BaseAdapter() {
    private var mList : ArrayList<GroupResponse>? = null
    private var context : Context? = null

    constructor(context: Context?, types: ArrayList<GroupResponse>) : this() {
        //엑티비티에서 넘어온 types를 Adapter의 mList에 대입
        mList = types
        this.context = context
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        return super.getDropDownView(position, convertView, parent)
        val mInflater: LayoutInflater = LayoutInflater.from(context)
        val view: View
        if(convertView == null){
            view = mInflater.inflate(R.layout.sp_drop_down, parent, false)
        }
        else {
            view = convertView
        }
        view.findViewById<TextView>(R.id.txtDropDownLabel).text =mList!![position].gprName
        return view
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        val mInflater: LayoutInflater = LayoutInflater.from(context)
            if(convertView == null){
                view = mInflater.inflate(R.layout.sp_normal, parent, false)
                vh = ItemRowHolder(view)
                view?.tag = vh
            }
            else {
                view = convertView
                vh = view.tag as ItemRowHolder
            }
        vh.label.text = mList!![position].gprName

        return view
    }


    private class ItemRowHolder(row: View?) {

        val label: TextView

        init {
            this.label = row?.findViewById(R.id.tv_normal) as TextView
        }
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
}

/*
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.sp_drop_down, parent, false)
        }
        view.findViewById<TextView>(R.id.txtDropDownLabel).text = mList!![position]

        return view

 */