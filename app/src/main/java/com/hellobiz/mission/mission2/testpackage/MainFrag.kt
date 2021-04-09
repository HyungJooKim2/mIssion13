package com.hellobiz.mission.mission2.testpackage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.FragMainBinding
import com.hellobiz.mission.mission4.puttingtogether.mission2.Mission2Frag
import com.hellobiz.mission.mission4.puttingtogether.mission4.Mission4Frag
import com.hellobiz.mission.mission4.puttingtogether.mission3.Mission3Frag
import com.hellobiz.mission.mission4.puttingtogether.mission1.Mission1Frag

class MainFrag : Fragment() {
    private lateinit var myContext: Context
    private var _binding: FragMainBinding? = null
    private val binding get() = _binding!!
    private var test = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragMainBinding.inflate(inflater, container, false)
        val view = binding.root
        myContext = requireContext()

        binding.bottomnavigationview.setOnNavigationItemSelectedListener { it ->
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            when (it.itemId) {
                R.id.mission1 -> {
                    if (!test.equals("list")) {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout2,
                            Mission1Frag()
                        ).commit()
                        test = "list"
                    }
                    false
                }
                R.id.mission2 -> {
                    if (!test.equals("add")) {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout2,
                            Mission2Frag()
                        ).commit()
                        test = "add"
                    }
                    false
                }
                R.id.mission3 -> {
                    if (!test.equals("home")) {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout2,
                            Mission3Frag()
                        ).commit()
                        test = "home"
                    }
                    false
                }
                R.id.mission4 -> {
                    if (!test.equals("book")) {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout2,
                            Mission4Frag()
                        ).commit()
                        test = "book"
                    }
                    false
                }
                else -> {
                    false
                }
            }
        }




        return view
    }
}