package com.hellobiz.mission.mission2.testpackage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hellobiz.mission.R
import java.text.DecimalFormat

class TestAdapter(): RecyclerView.Adapter<TestAdapter.TestViewHolder>() {
    private var mList: ArrayList<Test>? = null

    constructor(Trucks: ArrayList<Test>?) : this() {
        //엑티비티에서 넘어온 Stores를 Adapter의 mList에 대입
        mList = Trucks
    }
    inner class TestViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val mcrImg1 : ImageView = view.findViewById(R.id.mcr_img1)
        val titleText : TextView = view.findViewById(R.id.title_text)
        val descriptionLocationText : TextView = view.findViewById(R.id.description_location_text)
        val mcrKm : TextView = view.findViewById(R.id.mcr_km)
        val mcrPrice : TextView = view.findViewById(R.id.mcr_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item3,parent,false)
        return TestViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        setImageUrl(holder.mcrImg1,mList!![position].mcrImg1)
        holder.titleText.text = mList!![position].mcrBrandNm + mList!![position].mcrModelNm + mList!![position].mcrLcdNm + mList!![position].mcrTon
        holder.descriptionLocationText.text = mList!![position].mcrAddr1Nm + mList!![position].mcrAddr2Nm
        holder.mcrKm.text = setDecimalFormat(mList!![position].mcrKm)
        holder.mcrPrice.text = setDecimalFormat(mList!![position].mcrPrice)
    }

    override fun getItemCount(): Int {
       return mList!!.size
    }
    fun setImageUrl(imgView: ImageView, imgUrl: String?){
        imgUrl?.let {
            val imgUri = it.toUri().buildUpon().scheme("http").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .error(R.drawable.ic_launcher_background))
                .into(imgView)
        }
    }

    fun setDecimalFormat(number : Int): String {
        val myFormatter = DecimalFormat("###,###")
        val formattedStringPrice: String = myFormatter.format(number)
        return formattedStringPrice
    }
}
