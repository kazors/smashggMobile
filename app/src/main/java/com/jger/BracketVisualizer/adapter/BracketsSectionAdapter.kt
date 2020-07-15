package com.jger.BracketVisualizer.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jger.BracketVisualizer.Fragment.BracketsColomnFragment
import com.jger.BracketVisualizer.model.ColomnData
import java.util.*

/**
 * Created by Emil on 21/10/17.
 */
class BracketsSectionAdapter(
    fm: FragmentManager,
    val sectionList: ArrayList<ColomnData>,
    val isLooser : Boolean
) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putSerializable("colomn_data", sectionList[position])
        val fragment = BracketsColomnFragment(isLooser,this)
        bundle.putInt("section_number", position)
        if (position > 0) bundle.putInt(
            "previous_section_size",
            sectionList[position - 1].getMatches().size
        ) else if (position == 0) bundle.putInt(
            "previous_section_size",
            sectionList[position].getMatches().size
        )
        if(position>0){
            bundle.putSerializable("lastColomnData", sectionList[position-1])
        }
        if(sectionList.size-1 == position){
            bundle.putBoolean("isLastSection",true)
        }
        if(sectionList.size-2 == position && !isLooser){
            bundle.putBoolean("isWinnerFinnal",true)
        }
        fragment.setArguments(bundle)
        return fragment
    }

    override fun getCount(): Int {
        return sectionList.size
    }


}