package com.alperakaydin.youtubemusic

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alperakaydin.youtubemusic.ui.theme.YoutubeMusicTheme
import com.alperakaydin.youtubemusic.ui.theme.arkaplanKirmizi2
import com.alperakaydin.youtubemusic.ui.theme.buttonArkaplan
import com.alperakaydin.youtubemusic.ui.theme.buttonBorder
import com.alperakaydin.youtubemusic.ui.theme.buttonPlayall
import com.alperakaydin.youtubemusic.ui.theme.robotocondensed
import com.alperakaydin.youtubemusic.ui.theme.robotoregular

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubeMusicTheme {
                MusicScreen()
            }
        }
    }
}

@Composable
fun MusicScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        arkaplanKirmizi2,
                        Color.Black
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 600f)
                )
            )
    ) {
        Scaffold(
            topBar = { MusicTopBar() },
            bottomBar = {
                MusicBottomBar()
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                MusicTabs()
                NewReleases()
                Spacer(modifier = Modifier.height(16.dp))
                MusicQuickPicks()

            }
        }
    }
}

@Composable
fun NewReleases() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "New releases",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            fontFamily = robotoregular,
            textAlign = TextAlign.Center
        )
        // ıcon
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Play",
            tint = Color.White,
            modifier = Modifier.background(Color.Transparent)
        )
    }
    MusicGrid()

}

@Composable
fun MusicGrid() {
    val items = generateQuickMusicList()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),

    ) {
        items(items) { item ->
            MusicItemCard(item)
        }
    }
}

@Composable
fun MusicItemCard(music: Music) {
    Column(
        modifier = Modifier
            .width(175.dp)
            .padding(4.dp)
    ) {
        // Albüm kapağı
        Card(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(1.dp)),

        ) {
            val activity = (LocalContext.current as Activity)

            Image(

                bitmap = ImageBitmap.imageResource(
                    id = activity.resources.getIdentifier(
                        music.image,
                        "drawable",
                        activity.packageName
                    )
                ), // Albüm kapağı
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp)) // Görsel ile metin arasındaki boşluk

        // Şarkı adı
        Text(
            text = music.name,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = robotoregular,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        // Sanatçı adı ve kategori
        Text(
            text = "${music.view_count} • ${music.artist}",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = robotoregular,
            color = Color.Gray,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}


@Composable
fun MusicQuickPicks() {
    val quickMusicListNormal: ArrayList<Music> = generateQuickMusicList()
    val quickMusicList = quickMusicListNormal.asReversed()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "Quick Picks",
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = buttonPlayall,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .height(20.dp)
            ) {
                Text(
                    text = "Play all",
                    fontSize = 10.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(quickMusicList.chunked(4)) { musicGroup ->
            Column {
                musicGroup.forEach {
                    MusicQuickCard(it)
                }
            }
        }
    }

}

@Composable
fun MusicQuickCard(music: Music) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(58.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(1.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .width(340.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


            val activity = (LocalContext.current as Activity)

            Image(
                bitmap = ImageBitmap.imageResource(
                    id = activity.resources.getIdentifier(
                        music.image,
                        "drawable",
                        activity.packageName
                    )
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(58.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,


                )
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = music.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    fontFamily = robotocondensed,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${music.artist} • ${music.view_count} plays",
                    color = Color.Gray,
                    fontWeight = FontWeight.Thin,
                    fontSize = 12.sp,
                    fontFamily = robotocondensed,
                    textAlign = TextAlign.Center
                )

            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,

                    ),
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.background(Color.Transparent)

                )
            }
        }
    }
}

@Composable
fun MusicBottomBar() {
    NavigationBar(
        containerColor = Color.Black, // Arka plan rengi
        contentColor = Color.White // Varsayılan içerik rengi
    ) {
        val items = listOf(
            BottomBarItem(
                "Home",
                painterResource(id = R.drawable.ic_home),
                isSelected = true
            ),
            BottomBarItem(
                "Samples",
                painterResource(id = R.drawable.ic_samples),
                isSelected = false
            ),
            BottomBarItem(
                "Explore",
                painterResource(id = R.drawable.ic_explore),
                isSelected = false
            ),
            BottomBarItem(
                "Library",
                painterResource(id = R.drawable.ic_library),
                isSelected = false
            )
        )

        items.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.title,
                            tint = if (item.isSelected) Color.White else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = item.title,
                            fontSize = 10.sp,
                            color = if (item.isSelected) Color.White else Color.Gray
                        )
                    }
                }
            )
        }
    }
}

data class BottomBarItem(
    val title: String,
    val icon: Painter,
    val isSelected: Boolean
)

@Composable
fun MusicTabs() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 12.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Butonlar arasındaki boşluk
    ) {
        val tabs = listOf("Energize", "Feel good", "Workout", "Sad", "Relax")
        items(tabs) { tab ->
            MusicTabButton(label = tab)
        }
    }
}

@Composable
fun MusicTabButton(label: String) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,

            ),
        contentPadding = PaddingValues(vertical = 6.dp, horizontal = 14.dp),
        modifier = Modifier
            .height(40.dp)
            .border(
                width = 1.dp,
                color = buttonBorder,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = buttonArkaplan,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 1.dp, vertical = 4.dp)


    ) {

        Text(
            text = label,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            fontFamily = robotoregular,
            textAlign = TextAlign.Center,

        )
    }
}


@Composable
fun MusicTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 16.dp, top = 26.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_yt_music_with_text_logo),
                contentDescription = "Music Logo",
                modifier = Modifier.size(77.dp, 50.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(2.dp))
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Bildirim simgesi
            Box {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications), // Bildirim ikonu
                    contentDescription = "Notifications",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
                // Bildirim sayısı
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(Color.Red, CircleShape)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = "3",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            // Arama simgesi
            Icon(
                painter = painterResource(id = R.drawable.ic_search), // Arama ikonu
                contentDescription = "Search",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )

            // Profil simgesi
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(Color(0xFF35651F), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "A",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YoutubeMusicTheme {
        MusicScreen()
    }
}