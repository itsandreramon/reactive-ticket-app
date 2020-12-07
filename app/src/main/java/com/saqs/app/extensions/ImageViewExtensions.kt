/*
 * Copyright 2020 - André Thiele, Allan Fodi, Hüseyin Celik, Bertin Junior Wagueu Nkepgang
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.transform.CircleCropTransformation
import com.saqs.app.R

fun ImageView.loadImageElsePlaceholder(
    @DrawableRes imageRes: Int? = null,
    @DrawableRes placeholderRes: Int = R.drawable.ic_no_photography
) {
    if (imageRes != null) {
        // try loading image resource
        load(imageRes) {
            placeholder(placeholderRes)
            transformations(listOf(CircleCropTransformation()))
        }
    } else {
        // default to placeholder
        load(placeholderRes) {
            transformations(listOf(CircleCropTransformation()))
        }
    }
}

fun ImageView.loadImageElsePlaceholder(
    imageUrl: String? = null,
    @DrawableRes placeholderRes: Int = R.drawable.ic_no_photography
) {
    if (imageUrl != null) {
        // try loading image resource
        load(imageUrl) {
            placeholder(placeholderRes)
            transformations(listOf(CircleCropTransformation()))
        }
    } else {
        // default to placeholder
        load(placeholderRes) {
            transformations(listOf(CircleCropTransformation()))
        }
    }
}
