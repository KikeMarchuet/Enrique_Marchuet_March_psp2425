package figures

// Classe RectangleFactory per a la creacio de rectangles
// seguint el patro Factory.

class RectangleFactory(
    private val x: Int,
    private val y: Int,
    private val amplada: Int,
    private val altura: Int,
    private val color: String,
    private val colorVora: String
) : FiguraFactory {
    override fun createFigura(): Figura {
        return Rectangle(x, y, amplada, altura, color, colorVora)
    }
}