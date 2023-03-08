import csstype.*
import react.*
import emotion.react.css
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3

external interface AudioPlayerProps : Props {
    var audio: Audio
    var onWatchedButtonPressed: (String) -> Unit
    var notListenedTo: Boolean
}

val AudioPlayer = FC<AudioPlayerProps> { props ->
    div {
        h3 {

            +props.audio.title
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
                    backgroundColor = if (props.notListenedTo) NamedColor.lightgreen else NamedColor.red
                    paddingLeft = 10.px
                    paddingRight = 10.px
                    borderRadius = 10.px
                }
                onClick = {
                    props.onWatchedButtonPressed(props.audio.src)
                }
                if (props.notListenedTo) {
                    +"Mark as listened"
                } else {
                    +"Mark as to listen"
                }
            }
            Sharing{
                url = props.audio.src
            }
        }
        ReactAudioPlayer {
            src = props.audio.src
            autoPlay = false
            controls = true
        }
    }
}