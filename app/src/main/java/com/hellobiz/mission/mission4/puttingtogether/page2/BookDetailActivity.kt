package com.hellobiz.mission.mission4.puttingtogether.page2

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellobiz.mission.databinding.ActivityBookBinding
import com.hellobiz.mission.mission4.puttingtogether.page2.model.BookList
import kotlinx.android.synthetic.main.activity_book.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/*
장부상세 Activity
 */
class BookDetailActivity : AppCompatActivity(), View.OnClickListener {
    private var mBinding: ActivityBookBinding? = null
    private val binding get() = mBinding!!
    private var bookData: ArrayList<BookList> = ArrayList()
    private var adapter: BookAdapter = BookAdapter()
    private var dialog = CalendarDialogFragment()


    @RequiresApi(Build.VERSION_CODES.O)  //26버전(오레오,8) 이상 지원
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setText()       //날짜 초기값 현 시간으로 세팅
        getData()       //가게명, 위치, 총매출, 실매출 정보 장부 페이지에서 받아옴
        putBookData()   //더미데이터 입력
        getRecyclerView()   //일별 recyclerview 기본 설정

        binding.dateButton.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    //날짜 초기값 현 시간으로 세팅
    private fun setText() {
        val now = LocalDate.now()
        val Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val arr = Strnow.split("-")
        binding.bookYear.text = arr[0]
        binding.bookMonth.text = "-" + arr[1]
        binding.bookDate.text = "-" + arr[2]


    }
    //가게명, 위치, 총매출, 실매출 정보 장부 페이지에서 받아옴
    private fun getData() {
        val store = intent.getStringExtra("store")
        val location = intent.getStringExtra("location")
        val sales = intent.getStringExtra("sales")
        val profit = intent.getStringExtra("profit")
        binding.store.text = store
        binding.profit.text = profit
        binding.location.text = sales
        binding.sales.text = location
    }

    //일별 recyclerview 기본 설정
    private fun getRecyclerView() {
        adapter = BookAdapter(this@BookDetailActivity, bookData)
        binding.bookRecyclerview.layoutManager =
            LinearLayoutManager(
                this@BookDetailActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        binding.bookRecyclerview.adapter = adapter
    }

    //더미데이터 입력
    private fun putBookData() {
        bookData.add(BookList("국내산 당근", "5 Box", "11,000원", "2,000원"))
        bookData.add(BookList("국내산 당근", "5 Box", "11,000원", "2,000원"))
        bookData.add(BookList("국내산 당근", "5 Box", "11,000원", "2,000원"))
        bookData.add(BookList("국내산 당근", "5 Box", "11,000원", "2,000원"))
    }

    override fun onClick(v: View?) {
        when (v) {
            //날짜 클릭 시 캘린더 dialog 띄움
            binding.dateButton -> {
                this.dialog.show(supportFragmentManager, "hi")
                dialog.setOnItemClickListener(object : CalendarDialogFragment.DialClickListener2 {
                    @SuppressLint("SetTextI18n")
                    //선택한 년도, 월, 일 을 textview에 표시
                    override fun DialMemberClick(year: Int, month: Int, date: Int) {
                        binding.bookYear.text = year.toString()
                        val y: Int = month + 1
                        binding.bookMonth.text = "-$y"
                        binding.bookDate.text = "-$date"
                    }
                })
            }
        }
    }
}