import react.*
import react.dom.html.ReactHTML.p
import csstype.*
import emotion.react.css


external interface AudioListProps : Props {
    var audios: List<String>
    var selectedAudio: String?
    var onSelectAudio: (String) -> Unit
}

val AudioList = FC<AudioListProps> { props ->
    for (audio in props.audios) {
        p {
            css {
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
            + audio
        }
    }
}