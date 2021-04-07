package com.hellobiz.mission.mission2.mainview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.MainItem2Binding
import com.hellobiz.mission2.mainview.model.MainViewResponse
import java.text.DecimalFormat

class TruckAdapter(
    mContext: Context?, mTruckList: ArrayList<MainViewResponse>
):RecyclerView.Adapter<TruckAdapter.TruckViewHolder>() {
    private var mList: ArrayList<MainViewResponse> = mTruckList
    private var context: Context? = mContext

/*
    constructor(context: Context?, Trucks: ArrayList<MainViewResponse>) : this() {
        //엑티비티에서 넘어온 Trucks를 Adapter의 mList에 대입
        mList = Trucks
        this.context = context
    }
*/

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TruckViewHolder {
        val binding = MainItem2Binding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return TruckViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
        holder.bind(mList[position])

    }

    override fun getItemCount(): Int {
            return mList.size
    }

    class TruckViewHolder(val binding: MainItem2Binding) : RecyclerView.ViewHolder(binding.root) {
        //MainViewResponse 자료형의 데이터를 넣으면 그 데이터로 imageview 또는 textview 출력
        @SuppressLint("SetTextI18n")
        fun bind(truck: MainViewResponse){
            //setImageUrl 매소드를 통해 이미지를 대입
            setImageUrl(binding.mcrImg1, truck.mcrImg1)
            //차량 ID, 차량 브랜드, 차량 모델, 차량 종류, 차량 무게(ton)
            binding.titleText.text = truck.mcrId.toString() + " " + truck.mcrBrandNm + " " + truck.mcrModelNm + " " + truck.mcrLcdNm + " " + truck.mcrTon.toString()+ "톤"
            //위치(시),위치(구)
            binding.descriptionLocationText.text = truck.mcrAddr1Nm + " " + truck.mcrAddr2Nm
            //타코데이터(km)
            binding.mcrKm.text = setDecimalFormat(truck.mcrKm)+"km"
            //차량 가격(만원)
            binding.mcrPrice.text = setDecimalFormat(truck.mcrPrice)+"만원"
        }

        //숫자에 3자리마다 콤마를 표시
        fun setDecimalFormat(number : Int): String {
            val myFormatter = DecimalFormat("###,###")
            val formattedStringPrice: String = myFormatter.format(number)
            return formattedStringPrice
        }

        //이미지 String값을 대입 시 Glide라이브러리를 활용하여 이미지뷰에 띄워주는 매소드
        fun setImageUrl(imgView: ImageView, imgUrl: String?){
            imgUrl?.let {
                val imgUri = it.toUri().buildUpon().scheme("http").build()
                Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .error(R.drawable.ic_launcher_background)
                    )
                    .into(imgView)
            }
        }
    }
}

