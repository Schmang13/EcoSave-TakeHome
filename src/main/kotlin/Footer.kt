import csstype.*
import react.*
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h4
import react.dom.html.ReactHTML.h3

external interface FooterProps : Props {
    var user: String
}

val Footer = FC<FooterProps> { props ->
    div {
        css {
            display = Display.flex
            justifyContent = JustifyContent.spaceBetween
            alignItems = AlignItems.center
            height = 50.px
            backgroundColor = NamedColor.whitesmoke
        }
        h3 {
            css {
                color = NamedColor.darkgray
            }
            +"KotlinConf'23"
        }
        h4 {
            css {
                color = NamedColor.darkgray
                paddingRight = 15.px
            }
            +"Created by Matthew Dias"
        }
    }
}