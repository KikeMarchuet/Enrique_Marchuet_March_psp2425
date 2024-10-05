package figures

// Definim la classe poligon a partir de la classe figura
// Heretarem per tant, la posició i els colors

class Poligon(
    x: Int = 0,
    y: Int = 0,
    private var lasX: MutableList<Int>,
    private var lasY: MutableList<Int>,
    color: String = "#000000",
    colorVora: String = "#000000"
) : Figura(Punt(x, y), color, colorVora) {

    // Mètode per mostrar les propietats de la figura
    override fun renderText():String {
        return "Poligon en (${posicio.x}, ${posicio.y}) de color $color"
    }

    // Mètode per mostrar una descripció en XML de la figura
    override fun renderXML():String {
        // Implementació del mètode per dibuixar el poligon en XML
        // Cadena amb multiples linies. TrimIndent elimina espais innecessaris

        //Cal tractar els vectors amb els punts per a passar al format SVG
        var punts: String = ""
        //Sé la quantitat de punts i ho guarde en "llarg"
        val llarg = lasX.size
        //Recorrec la llista i afegisc X i Y a parelles
        for(i in 0 until llarg - 1) {
            if (i==0) {
                //al principi no pose espai
                punts += lasX[i]
            } else {
                //en els següents punts ja si
                punts += " " + lasX[i]
            }
            //la Y sempre precedida de espai
            punts += " " + lasY[i]
        }

        //Li passe la cadena de llargària variable al format acceptat per SVG
        return """
            <polygon points="$punts" fill="$color" stroke="$colorVora" />
        """.trimIndent()

    }
}
