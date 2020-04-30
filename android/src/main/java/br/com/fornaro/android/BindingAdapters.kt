package br.com.fornaro.android

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.ViewPropertyTransition

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun View.showHide(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter(value = ["image_url", "placeholder", "center_crop", "rounded_corners"], requireAll = false)
    fun ImageView.loadImage(imageUrl: String?, placeholder: Drawable?, centerCrop: Boolean = false,
                            roundedCorners: Boolean = false) {
        if (imageUrl != null) {
            val options = RequestOptions().placeholder(placeholder).error(placeholder)

            when {
                roundedCorners && centerCrop -> options.transform(CenterCrop(), RoundedCorners(16))
                roundedCorners -> options.transform(RoundedCorners(16))
                centerCrop -> options.centerCrop()
            }

            val animationObject = ViewPropertyTransition.Animator { view ->
                view.alpha = 0f
                val fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
                fadeAnim.duration = 500
                fadeAnim.start()
            }
            Glide.with(context)
                .load(imageUrl)
                .transition(GenericTransitionOptions.with(animationObject))
                .apply(options)
                .into(this)
        } else {
            Glide.with(context).load(placeholder).into(this)
        }
    }
}
