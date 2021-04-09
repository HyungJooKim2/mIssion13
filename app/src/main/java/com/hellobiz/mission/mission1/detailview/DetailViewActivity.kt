package com.hellobiz.mission.mission1.detailview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hellobiz.mission.mission1.detailview.interfaces.DetailView
import com.hellobiz.mission.mission1.detailview.model.MyDetailModel
import com.hellobiz.mission.mission1.detailview.service.DetailService
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.ActivityDetailViewBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission1.detailview.model.MyDetailResponse
import java.lang.Exception

// ---- 상세화면
// 리스트 아이템 클릭 후, 아이디를 받아와서 상세화면을 구성한다.
// 구성 시, 인터페이스를 호출하여 뷰에 반환한다.
class DetailViewActivity : AppCompatActivity(), DetailView {
    private var mBinding: ActivityDetailViewBinding? = null
    private val binding get() = mBinding!!
    private lateinit var Detaildata : MyDetailResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //click한 id값을 가져옴
        val id = intent.getIntExtra("MyStoreList", -1)
        findViewById<TextView>(R.id.id_number).text = id.toString()
        setService(id)
    }

    private fun setText(){
        binding.raqBrandNm.text = Detaildata.raqBrandNm // 브랜드 명칭
        binding.raqCarNm.text = Detaildata.raqCarNm
        binding.raqYears.text = Detaildata.raqYears
        binding.raqDistance.text = Detaildata.raqDistance
        binding.raqArea.text = Detaildata.raqArea
        binding.raqRepairType.text = Detaildata.raqRepair
        binding.raqInsureTypeNm.text = Detaildata.raqInsureTypeNm

    }

    @SuppressLint("SetTextI18n")
    override fun detailSuccess(myDetailModel: MyDetailModel) {
        when (myDetailModel.code) {
            200 -> {
                try {
                    Detaildata = myDetailModel.data[0]
                    setText()
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
            else -> Toast.makeText(this, myDetailModel.message, Toast.LENGTH_SHORT).show()
        }
    }
        override fun detailError(errorResponse: ErrorRespose) {
            //When을 활용해서 마찬가지로 status에 따라 처리
        }

        override fun detailFailure(message: Throwable?) {
            Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show()
        }

        private fun setService(id: Int) {
            val DetailService = DetailService(this)
            DetailService.getDetail(id)
        }
    }

