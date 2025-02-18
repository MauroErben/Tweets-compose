package com.example.tweets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TweetsScreen(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C2438))
    ) {
        AvatarImageContainer(Modifier.weight(1f))
        TweetContainer(Modifier.weight(4f))
    }
}

@Composable
fun TweetContainer(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Column {
            Header()
            TweetContent()
        }
    }
}

@Composable
fun TweetImage() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "Image Tweet",
        modifier = Modifier.clip(
            RoundedCornerShape(22.dp)
        )
    )
}

@Composable
fun TweetContent() {
    Column(
        modifier = Modifier.padding(end = 22.dp)
    ) {
        Text(text = "Descripcion ld w arga sobre dwd", color = Color.White, fontSize = 14.sp)
        Text(text = "texto Descripcion larga sobre el texto", color = Color.White, fontSize = 14.sp)
        Text(text = "Descripcion larga sobre el texto", color = Color.White, fontSize = 14.sp)
        Text(text = "Descripcion larga sobre el texto", color = Color.White, fontSize = 14.sp)
        Text(text = "Descripcion larga dw dadsobre el texto", color = Color.White, fontSize = 14.sp)
        Spacer(Modifier.size(12.dp))
        TweetImage()
        Spacer(Modifier.size(8.dp))
        TweeActions()
    }
}

@Composable
fun getActions(icons: List<Int>): List<TweetActions> {
    return icons.map { icon ->
        var currentCount by rememberSaveable { mutableIntStateOf(if (icon == R.drawable.ic_chat) 25 else 0) }
        var iconColor by remember { mutableStateOf(Color(0xFF7E8898)) }
        var iconType by rememberSaveable { mutableIntStateOf(icon) }

       TweetActions(
           icon = icon,
           currentCount = currentCount,
           iconColor = iconColor,
           iconType = iconType,
           onClick = {
               if (icon != R.drawable.ic_chat) {
                   if (currentCount <= 0) {
                       when(icon) {
                           R.drawable.ic_rt -> iconColor = Color(0xFF00A86B)
                           R.drawable.ic_like -> {
                               iconType = R.drawable.ic_like_filled
                               iconColor = Color(0xFFFF0000)
                           }
                       }
                       currentCount += 1
                   } else {
                       when(icon) {
                           R.drawable.ic_rt -> iconColor = Color(0xFF7E8898)
                           R.drawable.ic_like -> {
                               iconType = R.drawable.ic_like
                               iconColor = Color(0xFF7E8898)
                           }
                       }
                       currentCount = 0
                   }
               }
           }
       )
    }
}

@Composable
fun TweeActions() {
    val actions = getActions(
        listOf(R.drawable.ic_chat, R.drawable.ic_rt, R.drawable.ic_like)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        actions.forEach {
            Row(
                modifier = Modifier.clickable { it.onClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = it.iconType),
                    contentDescription = "action",
                    tint = it.iconColor
                )
                Spacer(Modifier.size(2.dp))
                Text(text = it.currentCount.toString(), color = Color(0xFF7E8898))
            }
        }
    }

}

@Composable
fun Header() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Aris", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
        Text(
            text = "@AristiDevs",
            modifier = Modifier.padding(start = 8.dp),
            color = Color(0xFF7E8898)
        )
        Text(text = "4h", modifier = Modifier.padding(start = 8.dp), Color(0xFF7E8898))
        Dots()
    }
}

@Composable
fun Dots() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dots),
            contentDescription = "icon dots",
            tint = Color.White
        )
    }
}

@Composable
fun AvatarImageContainer(modifier: Modifier) {
    Box(
        modifier = modifier.padding(12.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "",
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(50.dp)
        )
    }
}
