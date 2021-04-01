package com.hellobiz.mission1.Store_computerization

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hellobiz.mission1.DetailView.DetailViewActivity
import com.hellobiz.mission1.R
import com.hellobiz.mission1.Store_computerization.error.model.ErrorRespose
import com.hellobiz.mission1.Store_computerization.interfaces.MainActivityView
import com.hellobiz.mission1.Store_computerization.model.MyStoreModel
import com.hellobiz.mission1.Store_computerization.model.MyStoreResponse
import com.hellobiz.mission1.Store_computerization.service.StoreService

class MainActivity : AppCompatActivity(),MainActivityView{

    private var Storedata: ArrayList<MyStoreResponse>? = ArrayList()
    private var adapter = Store_Adapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getJsonData()
        ClickEvent()
    }

    companion object{
        val TAG = "MainActivity"
    }

    private fun getJsonData(){
        val MainService = StoreService(this)
        MainService.GetMain()
    }

    @SuppressLint("CutPasteId")
    override fun MainSuccess(myStoreModel: MyStoreModel) {
        when(myStoreModel.code){
            200->{
                Storedata = myStoreModel.data
                adapter = Store_Adapter(this,Storedata)
                findViewById<RecyclerView>(R.id.store_recyclerview).adapter = adapter
                findViewById<RecyclerView>(R.id.store_recyclerview).layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

            }
            else -> Toast.makeText(this,myStoreModel.message,Toast.LENGTH_SHORT).show()
        }
    }

    override fun MainError(errorResponse: ErrorRespose) {

    }

    override fun MainFailure(message: Throwable?) {

    }
    private fun ClickEvent(){
        adapter.setOnItemClickListener(object : Store_Adapter.ItemClickListener {
            override fun onItemClick(v: View?, position: Int, check: Boolean) {
                val intent = Intent(this@MainActivity,DetailViewActivity::class.java)
                startActivity(intent)
            }
        })

    }
}