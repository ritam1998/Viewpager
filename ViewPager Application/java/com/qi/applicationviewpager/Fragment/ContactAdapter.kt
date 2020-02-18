package com.qi.applicationviewpager.Fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qi.applicationviewpager.R

class ContactAdapter(var arrayList: ArrayList<ContactModel>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val viewList = LayoutInflater.from(parent.context).inflate(R.layout.fragment_listview,parent,false)
        return ContactViewHolder(viewList)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBindUserName(arrayList[position])
    }

    override fun getItemCount(): Int {
        Log.d("list Size","${arrayList.size}")
        return arrayList.size
    }
    class ContactViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private var userNameToText : TextView? = null

        fun onBindUserName(contactModel: ContactModel){

            userNameToText = itemView.findViewById(R.id.usernameTextView)
            userNameToText?.text = contactModel.userName
        }
    }

}