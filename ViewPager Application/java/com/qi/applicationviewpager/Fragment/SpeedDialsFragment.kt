package com.qi.applicationviewpager.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.qi.applicationviewpager.R

class SpeedDialsFragment : Fragment() {

    private var textItem : TextView? = null

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_speed_dials,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textItem = view.findViewById(R.id.section_label)

        textItem?.text = "Contact not available in Speed Dial"
    }
}