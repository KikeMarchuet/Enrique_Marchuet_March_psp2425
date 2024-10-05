package figures

import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Error! Has d'indicar el nom de fitxer d'entrada!")
        return
    }

    val fileName = args[0]
    val escena = readSceneFromFile(fileName)

    escena.renderText()
    escena.renderSVG("${args[0]}.svg")
}

fun readSceneFromFile(fileName: String): Escena {
    val file = File(fileName)
    val lines = file.readLines()

    if (lines.isEmpty()) {
        throw IllegalArgumentException("El fitxer està buit.")
    }

    // Primera línia conté les dimensions del SVG
    val dimensions = lines[0].split(" ")
    if (dimensions[0] != "dimensions") {
        throw IllegalArgumentException("El fitxer ha de començar amb la línia de dimensions.")
    }
    val width = dimensions[1].toInt()
    val height = dimensions[2].toInt()

    val escena = Escena(width, height)

    // Processar les línies restants per crear les figures
    for (line in lines.drop(1)) {
        val parts = line.split(" ")
        when (parts[0]) {
            "cercle" -> {
                val params = parts[1].split(",")
                val x = params[0].toInt()
                val y = params[1].toInt()
                val radi = params[2].toInt()
                val color = params[3]
                val colorVora = params[4]
                val cercle = CercleFactory(radi, x, y, color, colorVora)
                escena.addFigura(cercle)
            }
            "rectangle" -> {
                val params = parts[1].split(",")
                val x = params[0].toInt()
                val y = params[1].toInt()
                val amplada = params[2].toInt()
                val altura = params[3].toInt()
                val color = params[4]
                val colorVora = params[5]
                val rectangle = RectangleFactory(x, y, amplada, altura, color, colorVora)
                escena.addFigura(rectangle)
            }
            "linia" -> {
                val params = parts[1].split(",")
                val x = params[0].toInt()
                val y = params[1].toInt()
                val x2 = params[2].toInt()
                val y2 = params[3].toInt()
                val colorVora = params[4]
                val linia = LiniaFactory(x, y, x2, y2, colorVora)
                escena.addFigura(linia)
            }
            "ellipse" -> {
                val params = parts[1].split(",")
                val x = params[0].toInt()
                val y = params[1].toInt()
                val radix = params[2].toInt()
                val radiy = params[3].toInt()
                val color = params[4]
                val colorVora = params[5]
                val ellipse = EllipseFactory(radix, radiy, x, y, color, colorVora)
                escena.addFigura(ellipse)
            }
            "poligon" -> {
                //Tindrem que extraure la llista de llargària de punts variable
                //Creem 2 Llistes: una amb les X i altra amb les Y
                val lasX = mutableListOf<Int>()
                val lasY = mutableListOf<Int>()
                //Extrac les parelles
                val parelles = parts[1].split(";")
                //El llarg serà la quantitat de punts(x i y) + els dos colors
                val llarg = parelles.size
                //Agafem les que seran parells X-Y
                for (i in 0 until llarg-1) {
                    //Extrac les parelles una a una
                    val parellaXY = parelles[i].split(",")
                    val valorX = parellaXY[0].toInt()
                    val valorY = parellaXY[1].toInt()
                    //Agafem la X i la Y, i les afegim a les corresponents Llistes
                    lasX.add(valorX)
                    lasY.add(valorY)
                }
                //La ultima parella son els colors
                val colores = parelles[llarg-1]
                val cadacolor = colores.split(",")
                //El primer valor el color, i el segon la vora
                val color = cadacolor[0]
                val colorVora = cadacolor[1]

                //Llance la Factory del Poligon passant les X, les Y i els dos colors
                val poligon = PoligonFactory(lasX, lasY, color, colorVora)
                escena.addFigura(poligon)
            }
            else -> {
                println("Tipus de figura desconegut: ${parts[0]}")
            }
        }
    }

    return escena
}