package id.kotlin.android.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory.NORMAL
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.kotlin.android.deps.GlideApp

internal fun ImageView.load(url: String) {
    Glide.get(this.context).setMemoryCategory(NORMAL)
    GlideApp.with(this.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
}