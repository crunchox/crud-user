package com.example.apbatechtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class UserListAdapter(
    val context: Context,
    val userList: List<User>,
    val userClickListener: UserClickListener
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)

        val user = getItem(position) as User

        view.findViewById<TextView>(R.id.textViewKodeuser).append(user.kduser)
        view.findViewById<TextView>(R.id.textViewUsername).append(user.username)
        view.findViewById<TextView>(R.id.textViewPassword).append(user.password)
        view.findViewById<TextView>(R.id.textViewNama).append(user.nama)
        view.findViewById<TextView>(R.id.textViewHakakses).append(user.hakakses)
        view.findViewById<TextView>(R.id.textViewKdklinik).append(user.kdklinik)
        view.findViewById<TextView>(R.id.textViewKdcabang).append(user.kdcabang)
        view.findViewById<Button>(R.id.buttonUpdate)
            .setOnClickListener { userClickListener.onUpdate(user) }
        view.findViewById<Button>(R.id.buttonDelete)
            .setOnClickListener { userClickListener.onDelete(user) }

        return view
    }

    override fun getItem(position: Int): Any {
        return userList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return userList.size
    }
}
