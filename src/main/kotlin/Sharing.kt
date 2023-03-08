import react.*
import react.dom.html.ReactHTML.div

external interface SharingProps : Props {
    var url: String
}

val Sharing = FC<SharingProps> { props ->
    div {
        EmailShareButton {
            url = props.url
            EmailIcon {
                size = 32
                round = true
            }
        }
        TelegramShareButton {
            url = props.url
            TelegramIcon {
                size = 32
                round = true
            }
        }
    }
}