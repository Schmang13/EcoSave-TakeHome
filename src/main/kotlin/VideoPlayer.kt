import csstype.*
import react.*
import emotion.react.css
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3

external interface VideoPlayerProps : Props {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
    var unwatchedVideo: Boolean
}

val VideoPlayer = FC<VideoPlayerProps> { props ->
    div {
        h3 {
            +"${props.video.speaker}: ${props.video.title}"
        }
        div {
            css {
                display = Display.flex
                justifyContent = JustifyContent.spaceBetween
                marginBottom = 10.px
            }
            button {
                css {
                    display = Display.block
                    backgroundColor = if (props.unwatchedVideo) NamedColor.lightgreen else NamedColor.red
                    paddingLeft = 10.px
                    paddingRight = 10.px
                    borderRadius = 10.px
                }
                onClick = {
                    props.onWatchedButtonPressed(props.video)
                }
                if (props.unwatchedVideo) {
                    +"Mark as watched"
                } else {
                    +"Mark as unwatched"
                }
            }
            Sharing{
                url = props.video.videoUrl
            }
        }
        ReactPlayer {
            url = props.video.videoUrl
            controls = true
        }
    }
}