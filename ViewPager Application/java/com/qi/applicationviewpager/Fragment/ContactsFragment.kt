package com.qi.applicationviewpager.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qi.applicationviewpager.R

class ContactsFragment  : Fragment(){

    private var recyclerView : RecyclerView? = null
    private var allNameList = ArrayList<ContactModel>()


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycleviewer,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)

        allNameList.clear()

        allNameList.add(ContactModel("Suvajit Bhattacharya"))
        allNameList.add(ContactModel("Pritam Sinha"))
        allNameList.add(ContactModel("Shubham Roy"))
        allNameList.add(ContactModel("Ritam De"))

        val contactAdapter = ContactAdapter(allNameList)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        recyclerView?.adapter = contactAdapter
    }
}
