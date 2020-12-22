package com.project.mypreloaddata.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mypreloaddata.R
import com.project.mypreloaddata.databinding.ItemMahasiswaRowBinding
import com.project.mypreloaddata.model.MahasiswaModel
import java.util.*
import kotlin.collections.ArrayList

// TODO 11: buat adapter mahasiswa
class MahasiswaAdapter : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaHolder>() {
    private val listMahasiswa = ArrayList<MahasiswaModel>()

    fun setData(listMahasiswa : ArrayList<MahasiswaModel>) {
        if (listMahasiswa.size > 0) {
            this.listMahasiswa.clear()
        }

        this.listMahasiswa.addAll(listMahasiswa)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MahasiswaAdapter.MahasiswaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa_row, parent, false)
        return MahasiswaHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = listMahasiswa.size

    override fun onBindViewHolder(holder: MahasiswaAdapter.MahasiswaHolder, position: Int) {
        holder.bind(listMahasiswa[position])
    }

    inner class MahasiswaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMahasiswaRowBinding.bind(itemView)
        fun bind(mahasiswa: MahasiswaModel) {
            binding.txtNim.text = mahasiswa.nim
            binding.txtName.text = mahasiswa.name

            val random = Random()
            val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            binding.imageView.setColorFilter(color)
        }
    }
}