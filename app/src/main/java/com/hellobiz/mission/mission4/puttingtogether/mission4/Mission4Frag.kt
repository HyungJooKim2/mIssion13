package com.hellobiz.mission.mission4.puttingtogether.mission4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.FragmentMission4Binding
import com.hellobiz.mission.mission3.signup.DistributionSignUpActivity
import com.hellobiz.mission.mission4.puttingtogether.mission3.Mission3Frag

class Mission4Frag : Fragment(),View.OnClickListener {
    private lateinit var callback: OnBackPressedCallback
    private var mBinding: FragmentMission4Binding? = null
    private val binding get() = mBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMission4Binding.inflate(inflater, container, false)

        binding.myProfile.setOnClickListener(this)
        return binding.root
    }

    //프로필 업데이트 화면을 띄워줌
    private fun moveMission3(){
        startActivity(Intent(requireContext(),DistributionSignUpActivity::class.java))
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.myProfile -> {
                moveMission3()
            }
        }
    }


//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//               //추후 뒤로가기 버튼 사용 시 해당 코드
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        callback.remove()
//    }
}