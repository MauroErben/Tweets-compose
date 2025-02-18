package com.example.tweets

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class TweetActions(
    @DrawableRes val icon: Int,
    val currentCount: Int,
    val onClick: () -> Unit,
    val iconColor: Color = Color(0xFF7E8898),
    val iconType: Int
)
