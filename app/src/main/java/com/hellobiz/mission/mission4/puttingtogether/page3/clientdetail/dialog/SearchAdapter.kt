package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.databinding.SearchDialogBinding
import com.hellobiz.mission.databinding.SearchItemBinding
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.model.SearchResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.DialogResponse

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var listener: ItemClickListener? = null
    private var mList: ArrayList<SearchResponse>? = null
    private var context: Context? = null
    private var mSelectedItemCount = -1

    constructor(context: Context?, datas: ArrayList<SearchResponse>) : this() {
        //엑티비티에서 넘어온 data를 Adapter의 mList에 대입
        mList = datas
        this.context = context
    }

    //position과 check 여부를 알려주는 리스너 콜백을 정의
    interface ItemClickListener {
        fun onItemClick(name : String, location : String, tel : String ,memId:Int,srsId:Int,cntType:String)
    }

    //리스너에 클릭리스너 연결
    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(mList!![position])
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class SearchViewHolder(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //서버에서 받아온 값으로 setText
        @SuppressLint("SetTextI18n")
        fun bind(dialog: SearchResponse) {
            binding.searchId.text = dialog.srsId.toString()
            binding.searchName.text = dialog.srsNm
            binding.searchTel.text = dialog.srsTel
            binding.searchRadioButton.isChecked = position.equals(mSelectedItemCount)
        }

        init {
            //해당 view에 클릭 리스너를 붙인다
            binding.searchRadioButton.setOnClickListener { v ->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION && listener != null) {
                    if (listener != null) {
                        listener!!.onItemClick(mList!![pos].srsNm, mList!![pos].srsAddr, mList!![pos].srsTel,mList!![pos].memId,mList!![pos].srsId,mList!![pos].cntType)
                        if (pos == mSelectedItemCount) {
                            binding.searchRadioButton.isChecked = false
                            mSelectedItemCount = -1

                        } else {
                            mSelectedItemCount = pos
                            notifyDataSetChanged()
                        }
                    }
                }
            }
            }
        }
    }
