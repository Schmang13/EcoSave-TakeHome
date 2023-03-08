import react.*
import react.dom.html.ReactHTML.p
import csstype.*
import emotion.react.css


external interface VideoListProps : Props {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (Video) -> Unit
}

val VideoList = FC<VideoListProps> {props ->
    for (video in props.videos) {
        p {
            css {
                hover {
                    color = NamedColor.gray
                }
            }
            key = video.id.toString()
            onClick = {
                props.onSelectVideo(video)
            }
            if (video == props.selectedVideo) {
                +"▶ "
            }
            +"${video.speaker}: ${video.title}"
        }
    }
}