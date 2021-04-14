package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellobiz.mission.databinding.SearchDialogBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.`interface`.Search
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.model.SearchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.model.SearchResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.service.SearchService
import kotlinx.android.synthetic.main.search_dialog.*

/*
거래처 찾기 Dialog 페이지
 */
class SearchDialogFragment() : DialogFragment(),
    Search, View.OnClickListener {
    private var listener: ItemClickListener? = null
    private var mBinding: SearchDialogBinding? = null
    private val binding get() = mBinding!!
    private var keyWordText : String = ""
    private var searchData: ArrayList<SearchResponse> = ArrayList()
    private var adapter: SearchAdapter = SearchAdapter()
    private var getName : String? = null
    private var getLocation : String? = null
    private var getTel : String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = SearchDialogBinding.inflate(inflater, container, false)

        //본 다이얼로그 생성 스타일을 따르지 않고 생성
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //액션바 타이틀 제거
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        setRecyclerview()    //거래처 찾기 recyclerview 기본 세팅

        binding.searchSelect.setOnClickListener(this)
        binding.dialogUnselect.setOnClickListener(this)
        searchClient()

        getSearchService("R", "", 1, 40)   //거래처 찾기 서버 연결

        binding.refreshLayout.setOnRefreshListener {
            adapter.notifyDataSetChanged()
            refresh_layout.isRefreshing = false
        }


        return binding.root
    }

    //edittext에서 엔터(돋보기) 누를 시 키보드 내려가며 입력한 값 저장
    private fun searchClient() {
        binding.editSearch.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                keyWordText = binding.editSearch.text.toString()
                getSearchService("R",keyWordText,1,40)
                hideKeyBoard()
                true
            } else {
                false
            }
        }
    }

    //키보드를 내려주는 기능
    private fun hideKeyBoard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editSearch.windowToken, 0)
    }

    //거래처 찾기 recyclerview 기본 세팅
    private fun setRecyclerview() {
        adapter = SearchAdapter(requireContext(), searchData)
        binding.searchRecycerview.adapter = adapter
        binding.searchRecycerview.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

        //구분선 추가
        binding.searchRecycerview.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    //거래처 찾기 서버 연결
    private fun getSearchService(a: String, b: String, c: Int, d: Int) {
        val searchService = SearchService(this)
        searchService.getSearchService(a, b, c, d)
    }
    //position과 check 여부를 알려주는 리스너 콜백을 정의
    interface ItemClickListener {
        fun onItemClick(name : String, location : String, tel : String )
    }

    //리스너에 클릭리스너 연결
    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.searchSelect -> {
               if(listener!=null&&getName!=null&&getLocation!=null&&getTel!=null){
                   listener!!.onItemClick(getName!!,getLocation!!,getTel!!)
               }
                dismiss()
            }
            binding.dialogUnselect ->{
                dismiss()
            }
        }
    }

    override fun searchSuccess(searchModel: SearchModel?) {
        when (searchModel?.code) {
            200 -> {
                searchData.clear()
                setSearchRadioButtonEnable()
                searchData.addAll(searchModel.data)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun searchError(errorResponse: ErrorRespose) {

    }

    override fun searchFailure(message: Throwable?) {

    }

    //몇번째 라디오 버튼이 선택되었는지 및 체크 여부 콜백하여 받아옴
    private fun setSearchRadioButtonEnable() {
        adapter.setOnItemClickListener(object : SearchAdapter.ItemClickListener {
            override fun onItemClick(name: String, location: String, tel: String) {
                getName = name
                getLocation = location
                getTel = tel
            }
        })
    }
}