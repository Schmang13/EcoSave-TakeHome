@file:JsModule("react-audio-player")
@file:JsNonModule

import react.*

@JsName("default")
external val ReactAudioPlayer: ComponentClass<ReactAudioPlayerProps>

external interface ReactAudioPlayerProps : Props {
    var src: String
    var autoPlay: Boolean
    var controls: Boolean
}