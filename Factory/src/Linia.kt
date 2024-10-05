package figures

// Definim la linia cercle a partir de la classe figura
// Heretarem per tant, la posició i els colors

class Linia(
    x: Int = 0,
    y: Int = 0,
    private var x2: Int = 0,
    private var y2: Int = 0,
    color: String = "#000000",
    colorVora: String = "#000000"
) : Figura(Punt(x, y), color, colorVora) {

    // Mètode per mostrar les propietats de la figura
    override fun renderText():String {
        return "Línia en (${posicio.x}, ${posicio.y}) a ($x2,$y2) i color $color"
    }

    // Mètode per mostrar una descripció en XML de la figura
    override fun renderXML():String {
        // Implementació del mètode per dibuixar la línia en XML
        // Cadena amb multiples linies. TrimIndent elimina espais innecessaris
        return """
            <line x1="${posicio.x}" y1="${posicio.y}" x2="$x2" y2="$y2" stroke="$colorVora" />
        """.trimIndent()

    }
}
