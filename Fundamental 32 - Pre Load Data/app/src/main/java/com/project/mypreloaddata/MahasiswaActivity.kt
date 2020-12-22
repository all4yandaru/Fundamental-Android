package com.project.mypreloaddata

import android.content.ComponentName
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Messenger
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mypreloaddata.adapter.MahasiswaAdapter
import com.project.mypreloaddata.database.MahasiswaHelper
import com.project.mypreloaddata.databinding.ActivityMahasiswaBinding

// TODO 12: lengkapi kode utk baca semua data
class MahasiswaActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMahasiswaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa)
        binding = ActivityMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val mahasiswaAdapter = MahasiswaAdapter()
        binding.recyclerview.adapter = mahasiswaAdapter

        val mahasiswaHelper = MahasiswaHelper(this)
        mahasiswaHelper.open()

        val mahasiswaModels = mahasiswaHelper.getAllData()
        mahasiswaHelper.close()
        mahasiswaAdapter.setData(mahasiswaModels)
    }




}
