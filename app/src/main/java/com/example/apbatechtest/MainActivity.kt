package com.example.apbatechtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.apbatechtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            MyAppDatabase::class.java, "apbatech-db"
        ).build()

        binding.apply {
            buttonInput.setOnClickListener {
                insertUser(db)
            }
            adapter =
                UserListAdapter(applicationContext, getAllUser(db), object : UserClickListener {
                    override fun onUpdate(user: User) {
                        UpdateUserDialog(applicationContext, user, db) {
                            adapter.notifyDataSetChanged()
                        }.show()
                    }

                    override fun onDelete(user: User) {
                        db.userDao().delete(user)
                        adapter.notifyDataSetChanged()
                    }
                })
            listViewUsers.adapter = adapter
        }
    }

    private fun insertUser(db: MyAppDatabase) {
        var user: User
        val users = getAllUser(db)
        binding.apply {
            if (users.isNullOrEmpty()) {
                user = User(
                    "1",
                    editTextUsername.text.toString(),
                    editTextPassword.text.toString(),
                    editTextNama.text.toString(),
                    editTextHakakses.text.toString(),
                    editTextKdklinik.text.toString(),
                    editTextKdcabang.text.toString()
                )
            } else {
                val lastKdUser = users.last().kduser.toIntOrNull() ?: 1
                val newKdUser = lastKdUser + 1
                user = User(
                    newKdUser.toString(), editTextUsername.text.toString(),
                    editTextPassword.text.toString(),
                    editTextNama.text.toString(),
                    editTextHakakses.text.toString(),
                    editTextKdklinik.text.toString(),
                    editTextKdcabang.text.toString()
                )
            }
            db.userDao().insert(user)
            adapter.notifyDataSetChanged()
        }
    }

    private fun getAllUser(db: MyAppDatabase): List<User> {
        return db.userDao().getAll()
    }
}