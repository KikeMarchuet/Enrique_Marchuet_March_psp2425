package figures

// Classe PoligonFactory per a la creacio de poligons
// seguint el patro Factory.

class PoligonFactory(
    private val lasX:MutableList<Int>,
    private val lasY: MutableList<Int>,
    private val color: String,
    private val colorVora: String
) : FiguraFactory {
    override fun createFigura(): Figura {
        //Com a posició de generació de poligon, em quede en la primera coordenada
        return Poligon(lasX[0], lasY[0], lasX, lasY, color, colorVora)
    }
}