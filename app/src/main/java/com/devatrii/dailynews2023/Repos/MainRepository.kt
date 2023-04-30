package com.devatrii.dailynews2023.Repos

import android.app.DownloadManager.Request
import android.content.Context
import android.provider.SyncStateContract.Constants
import androidx.lifecycle.MutableLiveData
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.devatrii.dailynews.utils.POSTS_URL
import com.devatrii.dailynews2023.Model.ArticleModel
import org.json.JSONArray

class MainRepository(private val context: Context) {
    private val mutableLiveData = MutableLiveData<APIResponses<ArrayList<ArticleModel>>>()
    val liveData get() = mutableLiveData

    fun getArticles(page: String = "1") {
        liveData.value = APIResponses.Loading()
        val requestQueue = Volley.newRequestQueue(context)
        val url =
            POSTS_URL + "page=$page" // https://startuppakistan.com.pk/wp-json/wp/v2/posts?_embed&per_page=$POST_PER_PAGE&page=1
        val stringRequest = object : StringRequest(Method.GET, url, { response ->
            if (response.isNotEmpty()) {
                val tempList: ArrayList<ArticleModel> = ArrayList()
                val mainJsonArray = JSONArray(response)
                for (i in 0 until mainJsonArray.length()) {
                    val postObject = mainJsonArray.getJSONObject(i)
                    postObject.apply {
                        val embedded = getJSONObject("_embedded")
                        val id = getInt("id")
                        val title = getJSONObject("title").getString("rendered")
                        val content = getJSONObject("content").getString("rendered")
                        val excerpt = getJSONObject("excerpt").getString("rendered")
                        val date = getString("date")
                        val authorArray = embedded.getJSONArray("author")
                        val authorName = authorArray.getJSONObject(0).getString("name")
                        val authorUrl = authorArray.getJSONObject(0).getString("link")
                        val authorPic = authorArray.getJSONObject(0).getJSONObject("avatar_urls")
                            .getString("96")
                        val readingTime = (content.split(" ").size / 200).toString()
                        val image = embedded.getJSONArray("wp:featuredmedia").getJSONObject(0)
                            .getJSONObject("media_details").getJSONObject("sizes")
                            .getJSONObject("full").getString("source_url")
                        val category = getJSONArray("categories").get(0)

                        val model = ArticleModel(
                            id = id,
                            title = title,
                            content = content,
                            date = date,
                            excerpt = excerpt,
                            authorName = authorName,
                            authorUrl = authorUrl,
                            authorPic = authorPic,
                            readingTime = readingTime,
                            image = image,
                            category = category.toString().toInt()
                        )
                        tempList.add(model)
                    }

                }
                liveData.value = APIResponses.Success(tempList)
            }

        }, { error ->
            liveData.value = error.localizedMessage?.let { APIResponses.Error(error = it) }
        }) {

        }
        requestQueue.add(stringRequest)
    }
}