package com.hellobiz.mission.store_computerization

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission.mission1.detailview.DetailViewActivity
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.ActivityDistributionSignUpBinding
import com.hellobiz.mission.databinding.ActivityMainBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.store_computerization.interfaces.MainActivityView
import com.hellobiz.mission.store_computerization.model.MyStoreModel
import com.hellobiz.mission.store_computerization.model.MyStoreResponse
import com.hellobiz.mission.store_computerization.service.StoreService
import java.lang.Exception

class MainActivity : AppCompatActivity(), MainActivityView {

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private var storeData: ArrayList<MyStoreResponse>? = ArrayList()
    private lateinit var adapter : StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMainRecyclerview()
        getMainService()
        getTouchableItem()
    }

    private fun getMainService() {
        val mainService = StoreService(this)
        mainService.GetMain()
    }

    private fun getMainRecyclerview(){
        adapter = StoreAdapter(this, storeData)
        binding.storeRecyclerview.adapter = adapter
        binding.storeRecyclerview.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    private fun getTouchableItem(){
        adapter.setOnItemClickListener(object : StoreAdapter.ItemClickListener {
            override fun onItemClick(v: View?, position: Int, check: Int) {
                Toast.makeText(this@MainActivity, "$position item checked, $check",
                    Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, DetailViewActivity::class.java)
                intent.putExtra("MyStoreList", check)
                startActivity(intent)
                adapter.notifyDataSetChanged()
            }
        })
    }


    @SuppressLint("CutPasteId")
    override fun mainSuccess(myStoreModel: MyStoreModel) {
        when (myStoreModel.code) {
            200 -> {
                try {
                    storeData?.addAll(myStoreModel.data)
                    adapter.notifyDataSetChanged()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            else -> Toast.makeText(this, myStoreModel.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun mainError(errorResponse: ErrorRespose) {
            //When을 활용해서 마찬가지로 status에 따라 처리
    }

    override fun mainFailure(message: Throwable?) {
        Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show()
    }

    companion object {
        val TAG = "MainActivity"
    }


}