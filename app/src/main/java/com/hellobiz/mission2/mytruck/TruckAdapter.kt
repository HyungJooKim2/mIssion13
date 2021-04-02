package com.hellobiz.mission2.mytruck

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hellobiz.mission1.R
import com.hellobiz.mission1.databinding.MainItemBinding
import com.hellobiz.mission1.store_computerization.model.MyStoreResponse
import com.hellobiz.mission2.mytruck.model.Truck

class TruckAdapter():RecyclerView.Adapter<TruckAdapter.TruckViewHolder>() {
    private var mList: ArrayList<Truck>? = null
    private var context: Context? = null

    constructor(context: Context?, Trucks: ArrayList<Truck>?) : this() {
        //엑티비티에서 넘어온 Stores를 Adapter의 mList에 대입
        mList = Trucks
        this.context = context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TruckViewHolder {
        val binding = MainItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return TruckViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TruckAdapter.TruckViewHolder, position: Int) {
        holder.bind(mList!![position])

    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    class TruckViewHolder(val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(truck:Truck){
            setImageUrl(binding.mcrImg1,truck.MCRIMG1)
            binding.mcrBrandNm.text = truck.MCR_BRAND_NM
            binding.mcrModelNm.text = truck.MCR_MODEL_NM
            binding.mcrLcdNm.text = truck.MCR_LCD_NM
            binding.mcrTon.text = truck.MCR_TON
            binding.mcrAddr1Nm.text = truck.MCR_ADDR1_NM
            binding.mcrAddr2Nm.text = truck.MCR_ADDR2_NM
            binding.mcrKm.text = truck.MCR_KM
            binding.mcrPrice.text = truck.MCR_PRICE
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
    }
}

