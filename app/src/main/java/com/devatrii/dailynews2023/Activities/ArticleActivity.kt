package com.devatrii.dailynews2023.Activities

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.devatrii.dailynews2023.Model.ArticleModel
import com.devatrii.dailynews2023.Utilities.HTMLImageGetter
import com.devatrii.dailynews2023.convertDateFormat
import com.devatrii.dailynews2023.databinding.ActivityArticleBinding
import com.devatrii.dailynews2023.loadImageWithGlide
import com.zzhoujay.richtext.RichText


class ArticleActivity : AppCompatActivity() {
    val activity = this
    lateinit var binding: ActivityArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val model = intent.getSerializableExtra("model") as ArticleModel
            loadImageWithGlide(model.image, mPostImage, activity)
            loadImageWithGlide(model.authorPic, mAuthorImage, activity)
            mAuthorName.text = model.authorName
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                mDate.text = convertDateFormat(model.date)
            else
                mDate.text = "Unknown"
            val richText = RichText.fromHtml(model.content)
                .imageGetter(HTMLImageGetter(resources, mArticle, activity))
            richText.autoFix(true)
            richText.urlClick {
                try {
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(activity, Uri.parse(it))
                } catch (e: Exception) {
                   e.printStackTrace()
                }
                true
            }
            richText.into(mArticle)
        }

    }
}