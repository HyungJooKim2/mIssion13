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
import com.hellobiz.mission.mission3.distributionmain.add.AddFrag
import com.hellobiz.mission.mission3.distributionmain.book.BookFrag
import com.hellobiz.mission.mission3.distributionmain.home.HomeFrag
import com.hellobiz.mission.mission3.distributionmain.list.ListFrag

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
                R.id.bottom_list -> {
                    if (!test.equals("list")) {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout,
                            ListFrag()
                        ).commit()
                        test = "list"
                    }
                    false
                }
                R.id.bottom_add -> {
                    if (!test.equals("add")) {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout,
                            AddFrag()
                        ).commit()
                        test = "add"
                    }
                    false
                }
                R.id.bottom_home -> {
                    if (!test.equals("home")) {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout,
                            HomeFrag()
                        ).commit()
                        test = "home"
                    }
                    false
                }
                R.id.bottom_book -> {
                    if (!test.equals("book")) {
                        transaction.replace(    //fragment가 해당 layout으로 대체됨
                            R.id.framelayout,
                            BookFrag()
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