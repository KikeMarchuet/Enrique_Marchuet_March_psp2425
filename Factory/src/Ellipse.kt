package figures

// Definim la ellipse a partir de la classe figura
// Heretarem per tant, la posició i els colors

class Ellipse(
    private var radix: Int = 0,
    private var radiy: Int = 0,
    x: Int = 0,
    y: Int = 0,
    color: String = "#000000",
    colorVora: String = "#000000"
) : Figura(Punt(x - radix, y - radiy), color, colorVora) {

    // Mètode per mostrar les propietats de la figura
    override fun renderText():String {
        return "El.lipse en (${posicio.x}, ${posicio.y}) de radis $radix i $radiy, i color $color"
    }

    // Mètode per mostrar una descripció en XML de la figura
    override fun renderXML():String {
        // Implementació del mètode per dibuixar la ellipse en XML
        // Cadena amb multiples linies. TrimIndent elimina espais innecessaris
        return """
            <ellipse cx="${posicio.x + radix}" cy="${posicio.y + radiy}" rx="$radix" ry="$radiy" fill="$color" stroke="$colorVora" />
        """.trimIndent()

    }
}
