import csstype.*
import react.*
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3

external interface HeaderProps : Props {
    var user: String
}

val Header = FC<HeaderProps> { props ->
    div {
        css {
            position = Position.fixed
            left = 0.px
            right = 0.px
            display = Display.flex
            justifyContent = JustifyContent.spaceBetween
            alignItems = AlignItems.center
            height = 75.px
            marginTop = (-75).px
            backgroundColor = NamedColor.whitesmoke
        }
        img {
            src = "data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNzIiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA3MiA2NCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cGF0aCBkPSJNMTQuMTU2NyAxNC4xNTY3SDBMMTQuMTU2NyAwVjE0LjE1NjdaTTI4LjQ2MDggNDIuNjE3NUw0OS44NDMzIDY0SDcxLjIyNThMNDkuODQzMyA0Mi42MTc1SDI4LjQ2MDhaTTQyLjYxNzUgMEwxNC4xNTY3IDI4LjQ2MDhMMjguMzEzNCA0Mi42MTc1TDcxLjA3ODMgMEg0Mi42MTc1WiIgZmlsbD0iIzdGNTJGRiIvPjwvc3ZnPg=="
        }
        h1 {
            css {
                color = NamedColor.darkgray
            }
            +"KotlinConf Explorer"
        }
        h3 {
            css {
                color = NamedColor.darkgray
                paddingRight = 15.px
            }
            +"Welcome!"
        }
    }
}