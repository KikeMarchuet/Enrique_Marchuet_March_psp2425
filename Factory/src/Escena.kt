package figures

import java.io.File

fun validaColor(color: String): Boolean {
    // Expressió regular per validar el format hexadecimal
    val hexColorPattern = Regex("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")
    // Comprova si el color compleix l'expressió regular
    return hexColorPattern.matches(color)
}

class Escena(
    var x: Int = 800,
    var y: Int = 600,
    var LlistaFigures: MutableList<Figura> = ArrayList()
) {

    fun addFigura(factory: FiguraFactory) {
        val figura = factory.createFigura()
        if (figura.posicio.x < this.x && figura.posicio.y < this.y) {
            if (validaColor(figura.color)) {
                LlistaFigures.add(figura)
                println("\u001B[32m OK \u001B[0m")
            } else {
                println("\u001B[31m Color no vàlid \u001B[0m")
            }
        } else {
            println("\u001B[31m La imatge cau fora de l'escena. \u001B[0m")
        }
    }

    fun renderText() {
        for (f in this.LlistaFigures) {
            println(f.renderText())
        }
    }

    fun renderSVG(filePath: String) {
        val svgContent = StringBuilder()
        svgContent.append("""<svg width="$x" height="$y" xmlns="http://www.w3.org/2000/svg">""")
        for (f in this.LlistaFigures) {
            svgContent.append(f.renderXML())
        }
        svgContent.append("</svg>")
        File(filePath).writeText(svgContent.toString())
    }
}