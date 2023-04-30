package com.devatrii.dailynews2023.Utilities

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.zzhoujay.richtext.ImageHolder
import com.zzhoujay.richtext.RichTextConfig
import com.zzhoujay.richtext.callback.ImageGetter
import com.zzhoujay.richtext.callback.ImageLoadNotify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HTMLImageGetter(
    private val res: Resources,
    private val htmlTextView: TextView,
    private val context: Context
) : ImageGetter {
    override fun getDrawable(
        holder: ImageHolder?,
        config: RichTextConfig?,
        textView: TextView?
    ): Drawable {
        val source = holder?.source ?: ""
        val holder = BitmapDrawablePlaceHolder(res, null)
        GlobalScope.launch(Dispatchers.IO) {
            runCatching {
                val futureTarget: FutureTarget<Bitmap> = Glide.with(context)
                    .asBitmap()
                    .load(source)
                    .submit()
                val bitmap = futureTarget.get()
                val drawable = BitmapDrawable(res, bitmap)
                val width = getScreenWidth() - 150
                val aspectRatio: Float = (drawable.intrinsicWidth.toFloat()) / (drawable.intrinsicHeight.toFloat())
                val height = width / aspectRatio
                drawable.setBounds(10, 20, width, height.toInt())
                holder.setDrawable(drawable)
                holder.setBounds(10, 20, width, height.toInt())
                withContext(Dispatchers.Main) {
                    htmlTextView.text = htmlTextView.text
                }
            }
        }
        return holder
    }

    // Helper class for drawing BitmapDrawable
    internal class BitmapDrawablePlaceHolder(res: Resources, bitmap: Bitmap?) :
        BitmapDrawable(res, bitmap) {
        private var drawable: Drawable? = null

        override fun draw(canvas: Canvas) {
            drawable?.run { draw(canvas) }
        }

        fun setDrawable(drawable: Drawable) {
            this.drawable = drawable
        }
    }

    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    override fun recycle() {
        // No need to recycle any resources
    }

    override fun registerImageLoadNotify(imageLoadNotify: ImageLoadNotify?) {
        // Not implementing image load notification for now
    }
}
