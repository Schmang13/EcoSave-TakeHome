import csstype.*
import react.*
import emotion.react.css
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3

external interface AudioPlayerProps : Props {
    var src: Audio
}

val AudioPlayer = FC<AudioPlayerProps> { props ->
    ReactAudioPlayer {
        src = props.src
    }
}