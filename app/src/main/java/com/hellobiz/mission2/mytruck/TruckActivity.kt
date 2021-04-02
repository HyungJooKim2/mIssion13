package com.hellobiz.mission2.mytruck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellobiz.mission1.databinding.ActivityTruckBinding
import com.hellobiz.mission2.mytruck.model.Truck

class TruckActivity : AppCompatActivity() {
    private var mBinding: ActivityTruckBinding? = null
    private val binding get() = mBinding!!
    private var storeData: ArrayList<Truck>? = ArrayList()
    private var adapter = TruckAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTruckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        putData()
        getRecyclerview()
    }

    private fun putData(){
        storeData?.add(Truck("http://15.165.252.49:9090/images/mycar/C-01-4-20210227025927.png","현대","봉고","카고","6톤","서울특별시","강남구","4300km","4360만원"))
        storeData?.add(Truck("http://15.165.252.49:9090/images/mycar/C-01-4-20210227025628.png","현대","봉고","카고","6톤","서울특별시","강남구","4300km","4360만원"))
        storeData?.add(Truck("http://15.165.252.49:9090/images/mycar/C-01-4-20210227023651.png","현대","봉고","카고","6톤","서울특별시","강남구","4300km","4360만원"))
    }

    private fun getRecyclerview(){
        adapter = TruckAdapter(this,storeData)
        binding.mainRecyclerview.adapter = adapter
        binding.mainRecyclerview.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
    }

}