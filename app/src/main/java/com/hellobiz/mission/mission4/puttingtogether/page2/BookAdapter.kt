package com.hellobiz.mission.mission4.puttingtogether.page2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.databinding.BookItemBinding
import com.hellobiz.mission.mission4.puttingtogether.page2.model.BookList

class BookAdapter() : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var mList: ArrayList<BookList>? = null
    private var context: Context? = null


    constructor(context: Context?, lists: ArrayList<BookList>) : this() {
        //엑티비티에서 넘어온 lists를 Adapter의 mList에 대입
        mList = lists
        this.context = context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        val binding = BookItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(mList!![position])

    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class BookViewHolder(val binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(book: BookList) {
            binding.bookItem.text = book.item         //장부상세 일별 품목명
            binding.bookUnit.text = book.unit         //장부상세 일별 단위
            binding.bookSales.text = book.sales       //장부상세 일별 매출액
            binding.bookProfit.text = book.profit     //장부상세 일별 순이익
        }
    }

}
