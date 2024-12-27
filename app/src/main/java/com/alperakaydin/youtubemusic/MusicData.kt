package com.alperakaydin.youtubemusic

import androidx.compose.ui.graphics.vector.ImageVector

data class Music(
    var name: String,
    var artist: String,
    var view_count: String,
    var image: String  = "album_cover"
)

fun generateQuickMusicList(): ArrayList<Music> {
    val quickMusicList = ArrayList<Music>()

    quickMusicList.add(Music("Una Velita", "Tiësto", "1.2M","unavelita"))
    quickMusicList.add(Music("Toca's Children", "John Laurant", "172B","tocas"))
    quickMusicList.add(Music("Bohemian Rhapsody", "Queen", "999M","ortak"))
    quickMusicList.add(Music("Sen En Güzelsin", "Yalın", "7M","senenguzelsin"))
    quickMusicList.add(Music("Es", "Adamlar", "78M","es"))
    quickMusicList.add(Music("Ellerim Boş", "M Lisa", "16M","ellerimbos"))
    quickMusicList.add(Music("Baytar", "SAgo", "1.2B","baytar"))
    quickMusicList.add(Music("Her Sevda", "Nilüfer", "99M","hersevda"))
    quickMusicList.add(Music("Taş Duvarlar", "Kıraç", "75M","tasduvarlar"))
    quickMusicList.add(Music("Alo", "Ezhel", "987M","alo"))

    return quickMusicList
}