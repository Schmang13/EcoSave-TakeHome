import react.*
import react.dom.html.ReactHTML.p
import csstype.*
import emotion.react.css


external interface AudioListProps : Props {
    var audios: List<Audio>
    var selectedAudio: Audio?
    var onSelectAudio: (Audio) -> Unit
}

val AudioList = FC<AudioListProps> { props ->
    for (audio in props.audios) {
        p {
            css {
                width = 250.px
                hover {
                    color = NamedColor.gray
                }
            }
            onClick = {
                props.onSelectAudio(audio)
            }
            if (audio == props.selectedAudio) {
                +"▶ "
            }
            + audio.title
        }
    }
}