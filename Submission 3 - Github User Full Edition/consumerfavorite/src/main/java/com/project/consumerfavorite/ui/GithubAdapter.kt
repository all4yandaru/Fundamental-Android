package com.project.consumerfavorite.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.consumerfavorite.R
import com.project.consumerfavorite.data.model.GithubUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.ArrayList

class GithubAdapter: RecyclerView.Adapter<GithubAdapter.ViewHolder>() {
    private val data = ArrayList<GithubUser>()

    fun setData(items: ArrayList<GithubUser>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: GithubUser){
            with(itemView){
                tv_github_username.text = data.login
                Picasso
                    .get()
                    .load(data.avatar)
                    .placeholder(R.drawable.ic_baseline_image_24)
                        .error(R.drawable.ic_baseline_image_24)
                    .into(iv_profile)
            }
        }
    }
}