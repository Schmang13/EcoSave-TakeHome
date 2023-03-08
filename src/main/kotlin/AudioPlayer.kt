import csstype.*
import react.*
import emotion.react.css
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3

external interface AudioPlayerProps : Props {
    var src: String
    var onWatchedButtonPressed: (String) -> Unit
    var notListenedTo: Boolean
}

val AudioPlayer = FC<AudioPlayerProps> { props ->
    div {
        h3 {
            +"Audio Clips"
        }
        div {
            css {
                display = Display.flex
                justifyContent = JustifyContent.spaceBetween
            }
            button {
                css {
                    display = Display.block
                    backgroundColor = if (props.notListenedTo) NamedColor.lightgreen else NamedColor.red
                }
                onClick = {
                    props.onWatchedButtonPressed(props.src)
                }
                if (props.notListenedTo) {
                    +"Mark as watched"
                } else {
                    +"Mark as unwatched"
                }
            }
            div {
                EmailShareButton {
                    url = props.src
                    EmailIcon {
                        size = 32
                        round = true
                    }
                }
                TelegramShareButton {
                    url = props.src
                    TelegramIcon {
                        size = 32
                        round = true
                    }
                }
            }
        }
        ReactAudioPlayer {
            src = props.src
            autoPlay = true
            controls = true
        }
    }
}