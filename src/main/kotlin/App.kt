import kotlinx.coroutines.async
import csstype.*
import emotion.react.css
import react.*
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.dom.html.ReactHTML.h2
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
    var currentListen: Audio? by useState(null)
    var unwatchedVideos: List<Video> by useState(emptyList())
    var watchedVideos: List<Video> by useState(emptyList())
    var listened: List<Audio> by useState(emptyList())
    var notListened: List<Audio> by useState(listOf(
        Audio("Audio/Audio_1.mp3", "Short Sample"),
        Audio("Audio/Audio_2.mp3", "Medium Sample"),
        Audio("Audio/Audio_3.mp3", "Sample"),
        Audio("Audio/Audio_4.mp3", "Long Sample")
    ))
    useEffectOnce {
        mainScope.launch {
            unwatchedVideos = fetchVideos()
        }
    }

    div{
        div{
            Header {
            }
        }
        div{
            css {
                display = Display.flex
                flexDirection = FlexDirection.column
                justifyContent = JustifyContent.center
                alignItems = AlignItems.center
                background = NamedColor.whitesmoke
                marginTop = 75.px
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
                    h2 {
                        css{
                            display = Display.flex
                            justifyContent = JustifyContent.center
                        }
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
                    h2 {
                        css{
                            display = Display.flex
                            justifyContent = JustifyContent.center
                        }
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
                css {
                    display = Display.flex
                    justifyContent = JustifyContent.center
                    alignItems = AlignItems.center
                }
                currentListen?.let { curr ->
                    AudioPlayer {
                        audio = curr
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
                    h2 {
                        css{
                            display = Display.flex
                            justifyContent = JustifyContent.center
                        }
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
                    h2 {
                        css{
                            display = Display.flex
                            justifyContent = JustifyContent.center
                        }
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
        Footer {
        }
    }
}