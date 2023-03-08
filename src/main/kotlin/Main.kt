import kotlinx.browser.document
import react.*
import react.dom.client.createRoot
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: Int,
    val title: String,
    val speaker: String,
    val videoUrl: String
)
@Serializable
data class Audio(
    val id: Int,
    val src: String
)


fun main() {
    val container = document.getElementById("root") ?: error("Couldn't find root container!")
    createRoot(container).render(App.create())
}