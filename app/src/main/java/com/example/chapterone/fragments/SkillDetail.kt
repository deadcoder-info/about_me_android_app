package com.example.chapterone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chapterone.DumyData.DummyContent

import com.example.chapterone.R
import kotlinx.android.synthetic.main.fragment_skill_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SkillDetail : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    var showId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            showId = param1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (showId == null) {
            skill_detail_text.text = "No Detail"
            return
        }
        skill_detail_text.text = DummyContent.ITEMS[showId!!.toInt()].details
        rating.rating = DummyContent.ITEMS[showId!!.toInt()].expertise.toFloat()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            SkillDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
