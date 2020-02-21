package com.nexters.towhom.vo

import android.graphics.drawable.Drawable

data class FontVO(
    val fontSize: String,
    val fontResource: Int,
    val fontName: String
)

data class FontWeightVO(
    val text: String,
    val weight: String
)

data class FontColorVO (
    val name: String,
    val colorResource: Int,
    val colorBg: Int
)

