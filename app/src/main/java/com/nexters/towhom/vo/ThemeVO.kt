package com.nexters.towhom.vo

data class ThemeCategoryVO(val theme: String, val list: ArrayList<ThemeVO>)
data class ThemeVO(val name: String, val resource: Int)


data class StickerThemeVO(val theme: String, val list: ArrayList<StickerVO>)
data class StickerVO(val name: String, val resource: Int)