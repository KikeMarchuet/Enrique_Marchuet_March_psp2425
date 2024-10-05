package figures

// Classe LiniaFactory per a la creacio de línies
// seguint el patro Factory.

class LiniaFactory(
    private val x: Int,
    private val y: Int,
    private val x2: Int,
    private val y2: Int,
    private val colorVora: String
) : FiguraFactory {
    override fun createFigura(): Figura {
        return Linia(x, y, x2, y2, colorVora)
    }
}