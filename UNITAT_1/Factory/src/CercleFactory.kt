package figures

// Classe CercleFactory per a la creacio de cercles
// seguint el patro Factory.

class CercleFactory(
    private val radi: Int,
    private val x: Int,
    private val y: Int,
    private val color: String,
    private val colorVora: String
) : FiguraFactory {
    override fun createFigura(): Figura {
        return Cercle(radi, x, y, color, colorVora)
    }
}