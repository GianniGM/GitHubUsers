package it.giangraziano.moovelcodingchallenge.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import it.giangraziano.moovelcodingchallenge.R
import it.giangraziano.moovelcodingchallenge.model.GitHubUser

class DevelopersListAdapter (private val onItemClick: (GitHubUser) -> Unit) : RecyclerView.Adapter<DevelopersListAdapter.DeveloperViewHolder>() {

    private var developers: MutableList<GitHubUser> = mutableListOf()

    fun addData(list: MutableList<GitHubUser>) {
        this.developers.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {
        //build my itemView
        val ctx = parent.context
        val inflate = LayoutInflater.from(ctx)
        val v = inflate.inflate(R.layout.item_list_activity_main, parent, false)
        return DeveloperViewHolder(v)
    }

    override fun getItemCount(): Int {
        return developers.size
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        val item = developers[position]
        holder.setImage(item.avatar_url, "remove this content description")
        holder.setName(item.login)
        holder.setOnClick { onItemClick(item) }
    }

    inner class DeveloperViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val developerUserNameImage = view.findViewById(R.id.developer_user_image) as ImageView
        private val developerUserNameText = view.findViewById(R.id.developer_user_name) as TextView


        fun setImage(imageUrl: String?, description: String?) {
            Picasso.get().load(imageUrl).into(developerUserNameImage)
            developerUserNameImage.contentDescription = description
        }

        fun setName(name: String?){
            developerUserNameText.text = name
        }

        fun setOnClick(onClick: (Context) -> Unit){
            view.setOnClickListener { onClick(it.context) }
        }
    }
}