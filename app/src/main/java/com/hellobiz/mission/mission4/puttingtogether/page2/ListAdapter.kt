package com.hellobiz.mission.mission4.puttingtogether.page2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.databinding.ListItemBinding
import com.hellobiz.mission.mission4.puttingtogether.page2.model.DisList

/*
거래처별, 품목별 장부 Adapter
 */
class ListAdapter() : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private var listener: ItemClickListener? = null
    private var mList: ArrayList<DisList>? = null
    private var context: Context? = null


    constructor(context: Context?, lists: ArrayList<DisList>) : this() {
        //엑티비티에서 넘어온 Trucks를 Adapter의 mList에 대입
        mList = lists
        this.context = context
    }

    //가게명, 지역, 전체매출, 실매출 여부를 알려주는 리스너 콜백을 정의
    interface ItemClickListener {
        fun onItemClick(v: View?, store: String, location: String, sales: String, profit: String)
    }

    //리스너에 클릭리스너 연결
    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
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

    inner class ListViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(list: DisList) {
            binding.text1.text = list.store    //거래처명
            binding.text2.text = list.sales    //전체 매출
            binding.text3.text = list.location //지역
            binding.text4.text = list.profit   //실 매출
        }

        init {
            // 해당 view에 클릭 리스너를 붙인후, 해당 내용 fragment로 전달
            binding.bind.setOnClickListener { v ->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    if (listener != null) {
                        listener!!.onItemClick(
                            v,
                            mList!![pos].store,
                            mList!![pos].sales,
                            mList!![pos].location,
                            mList!![pos].profit
                        )
                    }
                }
            }
        }
    }

}
