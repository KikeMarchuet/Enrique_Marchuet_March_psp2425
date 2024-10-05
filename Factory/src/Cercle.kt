package figures

// Definim la classe cercle a partir de la classe figura
// Heretarem per tant, la posició i els colors

class Cercle(
    private var radi: Int = 0,
    x: Int = 0,
    y: Int = 0,
    color: String = "#000000",
    colorVora: String = "#000000"
) : Figura(Punt(x - radi, y - radi), color, colorVora) {

    // Mètode per mostrar les propietats de la figura
    override fun renderText():String {
        return "Cercle en (${posicio.x}, ${posicio.y}) de radi $radi i color $color"
    }

    // Mètode per mostrar una descripció en XML de la figura
    override fun renderXML():String {
        // Implementació del mètode per dibuixar el cercle en XML
        // Cadena amb multiples linies. TrimIndent elimina espais innecessaris
        return """
            <circle cx="${posicio.x + radi}" cy="${posicio.y + radi}" r="$radi" fill="$color" stroke="$colorVora" />
        """.trimIndent()

    }
}
