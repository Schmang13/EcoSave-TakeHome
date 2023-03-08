import react.*
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.div
import csstype.*
import emotion.react.css


external interface VideoListProps : Props {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (Video) -> Unit
}

val VideoList = FC<VideoListProps> {props ->
    for (video in props.videos) {
        div {
            css {
                display = Display.flex
                hover {
                    color = NamedColor.gray
                }
            }
            key = video.id.toString()
            onClick = {
                props.onSelectVideo(video)
            }
            p {
                css {
                    fontWeight = FontWeight.bold

                }
                if (video == props.selectedVideo) {
                    +"▶ "
                }
                +"${video.speaker}: "
            }
            p{
                + video.title
            }
        }
    }
}