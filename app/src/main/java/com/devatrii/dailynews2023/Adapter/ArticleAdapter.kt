package com.devatrii.dailynews2023.Adapter

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devatrii.dailynews2023.Activities.ArticleActivity
import com.devatrii.dailynews2023.Model.ArticleModel
import com.devatrii.dailynews2023.databinding.ItemNewsCardBinding
import com.devatrii.dailynews2023.databinding.ItemNewsListBinding
import com.devatrii.dailynews2023.loadImageWithGlide

const val LAYOUT_CARD = 1
const val LAYOUT_LIST = 2

class ArticleAdapter(var list: ArrayList<ArticleModel>, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class cardViewHolder(var binding: ItemNewsCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ArticleModel, context: Context) {
            binding.apply {
                mArticleTitleCard.text = Html.fromHtml(model.title)
                mArticleExcerptCard.text = Html.fromHtml(model.excerpt)
                loadImageWithGlide(model.image, imageView, context)
                mArticleCard.setOnClickListener {
                    val intent = Intent().apply {
                        putExtra("model", model)
//                        putExtra("image", model.image)
//                        putExtra("title", model.title)
//                        putExtra("content", model.content)
//                        putExtra("authorName", model.authorName)
//                        putExtra("authorPic", model.authorPic)
//                        putExtra("authorUrl", model.authorUrl)
//                        putExtra("date", model.date)
                        setClass(context, ArticleActivity::class.java)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    class listViewHolder(var binding: ItemNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, model: ArticleModel) {
            binding.apply {
                mListTitle.text = Html.fromHtml(model.title)
                mListAuthorName.text = model.authorName
                mListReadTime.text = "• ${model.readingTime}m Read" // • 3m Read
                loadImageWithGlide(model.image, mListImage, context)
                mListItem.setOnClickListener {
                    val intent = Intent().apply {
                        putExtra("model", model)
                        setClass(context, ArticleActivity::class.java)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount() = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LAYOUT_CARD) {
            return cardViewHolder(
                ItemNewsCardBinding.inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
                )
            )
        }
        return listViewHolder(
            ItemNewsListBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (getItemViewType(position) == LAYOUT_CARD) {
            (holder as cardViewHolder).bind(model, context)
        } else {
            (holder as listViewHolder).bind(context, model)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].LAYOUT_TYPE == LAYOUT_CARD) {
            LAYOUT_CARD
        } else {
            LAYOUT_LIST
        }
    }

}