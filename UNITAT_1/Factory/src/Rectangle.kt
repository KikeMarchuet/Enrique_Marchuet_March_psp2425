package figures

// Definim la classe rectangle a partir de la classe figura
// Heretarem per tant, la posició i els colors

class Rectangle(
    x: Int = 0,
    y: Int = 0,
    private var amplada: Int = 0,
    private var altura: Int = 0,
    color: String = "#000000",
    colorVora: String = "#000000"
) : Figura(Punt(x, y), color, colorVora) {

    // Mètode per mostrar les propietats de la figura
    override fun renderText():String {
        return "Rectangle en (${posicio.x}, ${posicio.y}) de amplada $amplada, altura $altura i color $color"
    }

    // Mètode per mostrar una descripció en XML de la figura
    override fun renderXML():String {
        // Implementació del mètode per dibuixar el rectangle en XML
        // Cadena amb multiples linies. TrimIndent elimina espais innecessaris
        return """
            <rect x="${posicio.x}" y="${posicio.y}" width="$amplada" height="$altura" fill="$color" stroke="$colorVora" />
        """.trimIndent()

    }
}
