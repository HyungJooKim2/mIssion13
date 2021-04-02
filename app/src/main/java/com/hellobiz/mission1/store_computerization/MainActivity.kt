package com.hellobiz.mission1.store_computerization

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission1.detailview.DetailViewActivity
import com.hellobiz.mission1.R
import com.hellobiz.mission1.error.model.ErrorRespose
import com.hellobiz.mission1.store_computerization.interfaces.MainActivityView
import com.hellobiz.mission1.store_computerization.model.MyStoreModel
import com.hellobiz.mission1.store_computerization.model.MyStoreResponse
import com.hellobiz.mission1.store_computerization.service.StoreService
import java.lang.Exception

class MainActivity : AppCompatActivity(), MainActivityView {

    private var Storedata: ArrayList<MyStoreResponse>? = ArrayList()
    private var adapter = Store_Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMainService()
    }

    private fun getMainService() {
        val MainService = StoreService(this)
        MainService.GetMain()
    }

    @SuppressLint("CutPasteId")
    override fun MainSuccess(myStoreModel: MyStoreModel) {
        when (myStoreModel.code) {
            200 -> {
                try {
                    Storedata = myStoreModel.data
                    adapter = Store_Adapter(this, Storedata)
                    findViewById<RecyclerView>(R.id.store_recyclerview).adapter = adapter
                    findViewById<RecyclerView>(R.id.store_recyclerview).layoutManager =
                        LinearLayoutManager(
                            this,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    adapter.setOnItemClickListener(object : Store_Adapter.ItemClickListener {
                        override fun onItemClick(v: View?, position: Int, check: Int) {
                            Toast.makeText(this@MainActivity, "$position item checked, $check",
                                Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@MainActivity, DetailViewActivity::class.java)
                            intent.putExtra("MyStoreList", check)
                            startActivity(intent)
                            adapter.notifyDataSetChanged()
                        }
                    })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            else -> Toast.makeText(this, myStoreModel.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun MainError(errorResponse: ErrorRespose) {
            //When을 활용해서 마찬가지로 status에 따라 처리
    }

    override fun MainFailure(message: Throwable?) {
        Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show()
    }

    companion object {
        val TAG = "MainActivity"
    }
}