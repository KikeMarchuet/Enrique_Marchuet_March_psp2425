package figures

// Classe EllipseFactory per a la creacio de el.lipses
// seguint el patro Factory.

class EllipseFactory(
    private val radix: Int,
    private val radiy: Int,
    private val x: Int,
    private val y: Int,
    private val color: String,
    private val colorVora: String
) : FiguraFactory {
    override fun createFigura(): Figura {
        return Ellipse(radix,radiy, x, y, color, colorVora)
    }
}