package com.example.apbatechtest

import android.app.Dialog
import android.content.Context
import com.example.apbatechtest.databinding.DialogUpdateBinding

class UpdateUserDialog(context: Context, _user: User, db: MyAppDatabase, onFinish: (() -> Unit)) : Dialog(context) {

    var binding: DialogUpdateBinding = DialogUpdateBinding.inflate(layoutInflater)


    init {
        setContentView(binding.root)
        setTitle("Update User")
        binding.apply {
            buttonSave.setOnClickListener {
                val user = User(
                    _user.kduser,
                    editTextUsername.text.toString(),
                    editTextPassword.text.toString(),
                    editTextNama.text.toString(),
                    editTextHakakses.text.toString(),
                    editTextKdklinik.text.toString(),
                    editTextKdcabang.text.toString()
                )
                db.userDao().update(user)
                onFinish.invoke()
                dismiss()
            }
        }
    }
}
