package com.project.githubuser

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout.view.*

class GithubAdapter(private var list:ArrayList<GithubUser>) :
    RecyclerView.Adapter<GithubAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubAdapter.VH {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.layout, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: GithubAdapter.VH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(data : GithubUser){
            with(itemView){
                Glide.with(this).load(data.avatar).into(ci_photo)
                tv_username.text = data.username
                setOnClickListener(View.OnClickListener {
                    Toast.makeText(context, "Showing ${tv_username.text.toString().trim()} profile", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.EXTRA_DATA, data)
                    context.startActivity(intent)
                })
            }
        }
    }
}