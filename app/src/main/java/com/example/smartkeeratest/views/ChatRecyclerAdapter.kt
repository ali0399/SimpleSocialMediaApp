package com.example.smartkeeratest.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartkeeratest.R
import com.example.smartkeeratest.models.FriendxChat
import java.util.*

class ChatRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<ChatRecyclerAdapter.ChatViewHolder>(), Filterable {

    private var chatList: List<FriendxChat>? = null
    private var chatListTemp: List<FriendxChat>? = null
    lateinit var chatFilterList: List<FriendxChat>

    fun setChats(chats: List<FriendxChat>) {
        this.chatList = chats
        this.chatListTemp = chats
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(context, chatList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (chatList != null) chatList?.size!!
        else 0
    }

    class ChatViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {
        val chatImg = view.findViewById<ImageView>(R.id.chatFriendImg)
        val chatName = view.findViewById<TextView>(R.id.chatNameTv)
        val chatLastMsg = view.findViewById<TextView>(R.id.chatLastMsgTv)
        fun bind(context: Context, data: FriendxChat) {
            Glide.with(context).load(data.ProfileImage).into(chatImg)
            chatName.text = data.Name
            chatLastMsg.text = data.LastMessage
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    chatFilterList = chatListTemp!!
                    chatList = chatListTemp
                } else {
                    val resultList = ArrayList<FriendxChat>()
                    for (row in chatList!!) {
                        if (row.Name.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    chatFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = chatFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                chatList = results?.values as ArrayList<FriendxChat>
                notifyDataSetChanged()
            }

        }
    }

}