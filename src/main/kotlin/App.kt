import kotlinx.coroutines.async
import csstype.*
import emotion.react.css
import react.*
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.div

val mainScope = MainScope()

suspend fun fetchVideo(id: Int): Video {
    val response = window
        .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}

suspend fun fetchVideos(): List<Video> = coroutineScope {
    (1..25).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}

val App = FC<Props> {
    var currentVideo: Video? by useState(null)
    var currentListen: String? by useState(null)
    var unwatchedVideos: List<Video> by useState(emptyList())
    var watchedVideos: List<Video> by useState(emptyList())
    var listened: List<String> by useState(emptyList())
    var notListened: List<String> by useState(listOf(
        "https://file-examples.com/storage/fe0b804ac5640668798b8d0/2017/11/file_example_MP3_700KB.mp3",
        "https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_1MG.mp3",
        "https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_2MG.mp3",
        "https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_5MG.mp3"
    ))

    useEffectOnce {
        mainScope.launch {
            unwatchedVideos = fetchVideos()
        }
    }
    div{
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            justifyContent = JustifyContent.center
            alignItems = AlignItems.center
        }
        h1 {
            +"KotlinConf Explorer"
        }
        div {
            currentVideo?.let { curr ->
                VideoPlayer {
                    video = curr
                    unwatchedVideo = curr in unwatchedVideos
                    onWatchedButtonPressed = {
                        if (video in unwatchedVideos) {
                            unwatchedVideos = unwatchedVideos - video
                            watchedVideos = watchedVideos + video
                        } else {
                            watchedVideos = watchedVideos - video
                            unwatchedVideos = unwatchedVideos + video
                        }
                    }
                }
            }
        }
        div {
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                justifyContent = JustifyContent.spaceBetween
            }
            div {
                h3 {
                    +"Videos to watch"
                }
                VideoList{
                    videos = unwatchedVideos
                    selectedVideo = currentVideo
                    onSelectVideo = { video ->
                        currentVideo = video
                    }
                }
            }
            div {
                h3 {
                    +"Videos watched"
                }
                VideoList{
                    videos = watchedVideos
                    selectedVideo = currentVideo
                    onSelectVideo = { video ->
                        currentVideo = video
                    }
                }
            }
        }
        div {
            currentListen?.let { curr ->
                AudioPlayer {
                    src = curr
                    notListenedTo = curr in notListened
                    onWatchedButtonPressed = {
                        if (curr in notListened) {
                            notListened = notListened - curr
                            listened = listened + curr
                        } else {
                            listened = listened - curr
                            notListened = notListened + curr
                        }
                    }
                }
            }
        }
        div {
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                justifyContent = JustifyContent.spaceAround
            }
            div {
                h3 {
                    +"Audio to listen to"
                }
                AudioList {
                    audios = notListened
                    selectedAudio = currentListen
                    onSelectAudio = { audio ->
                        currentListen = audio
                    }
                }
            }
            div {
                h3 {
                    +"Audio listened to"
                }
                AudioList {
                    audios = listened
                    selectedAudio = currentListen
                    onSelectAudio = { audio ->
                        currentListen = audio
                    }
                }
            }
        }
    }
}