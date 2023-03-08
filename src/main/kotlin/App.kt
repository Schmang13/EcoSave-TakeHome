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
    var currentAudio: Audio? by useState(null)
    var unwatchedVideos: List<Video> by useState(emptyList())
    var watchedVideos: List<Video> by useState(emptyList())
    var notListened: List<Audio> by useState(emptyList())
    var listened: List<Audio> by useState(emptyList())

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
                        currentAudio = video
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
                        currentAudio = video
                    }
                }
            }
        }
        div {
            currentAudio?.let { curr ->
                AudioPlayer {
                    src = curr
                }
            }
        }
        div {
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
            }
            div {
                h3 {
                    +"Audio to listen"
                }
//                AudioList {
//                    audios = notListened
//                    selectedAudio = currentAudio
//                    onSelectAudio = { audio ->
//                        currentAudio = audio
//                    }
//                }
            }
            div {
                h3 {
                    +"Audio listened to"
                }
//                AudioList {
//                    audios = listened
//                    selectedAudio = currentVideo
//                    onSelectAudio = { audio ->
//                        currentAudio = audio
//                    }
//                }
            }
        }
    }
}