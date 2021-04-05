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
import com.hellobiz.mission.error.model.ErrorRespose
import java.lang.Exception

// ---- 상세화면
// 리스트 아이템 클릭 후, 아이디를 받아와서 상세화면을 구성한다.
// 구성 시, 인터페이스를 호출하여 뷰에 반환한다.
class DetailViewActivity : AppCompatActivity(), DetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        //click한 id값을 가져옴
        val id = intent.getIntExtra("MyStoreList", -1)
        findViewById<TextView>(R.id.id_number).text = id.toString()
        setService(id)
    }

    @SuppressLint("SetTextI18n")
    override fun DetailSuccess(myDetailModel: MyDetailModel) {
        when (myDetailModel.code) {
            200 -> {
                try {
                    val Detaildata = myDetailModel.data
                    findViewById<TextView>(R.id.RAQ_BRAND_NM).text = Detaildata[0].RAQ_BRAND_NM // 브랜드 명칭
                    findViewById<TextView>(R.id.RAQ_CAR_NM).text = Detaildata[0].RAQ_CAR_NM     // 차량명칭
                    findViewById<TextView>(R.id.MEM_NICK).text = Detaildata[0].MEM_NICK
                    findViewById<TextView>(R.id.RAQ_YEARS).text = Detaildata[0].RAQ_YEARS+"년"
                    findViewById<TextView>(R.id.RAQ_DISTANCE).text = Detaildata[0].RAQ_DISTANCE+"km"
                    findViewById<TextView>(R.id.RAQ_AREA).text = Detaildata[0].RAQ_AREA
                    findViewById<TextView>(R.id.RAQ_REPAIR_TYPE).text = Detaildata[0].RAQ_REPAIR
                    findViewById<TextView>(R.id.RAQ_INSURE_TYPE_NM).text = Detaildata[0].RAQ_INSURE_TYPE_NM
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
            else -> Toast.makeText(this, myDetailModel.message, Toast.LENGTH_SHORT).show()
        }
    }
        override fun DetailError(errorResponse: ErrorRespose) {
            //When을 활용해서 마찬가지로 status에 따라 처리
        }

        override fun DetailFailure(message: Throwable?) {
            Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show()
        }

        private fun setService(id: Int) {
            val DetailService = DetailService(this)
            DetailService.GetDetail(id)
        }
    }

