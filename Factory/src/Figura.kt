package figures

abstract class Figura (
    /* Constructor primari */
    var posicio: Punt = Punt(),
    var color: String = "#000000",
    protected var colorVora: String = "#000000"
) {
    // Constructor secundari proporcionant la posicio
    // en lloc d'un punt
    constructor(x: Int, y: Int) : this(Punt(x, y))

    // Constructor secundari per a la posició i els colors
    constructor(
        x: Int,
        y: Int,
        color: String,
        colorVora: String) : this(Punt(x, y), color, colorVora)

    // Mètodes abstractes que hauran d'implementar les subclasses
    abstract fun renderText():String // Retorna un text amb la descripció de la figura geomètrica
    abstract fun renderXML():String // Retorna un text amb la representacio vectorial de la figura
}
