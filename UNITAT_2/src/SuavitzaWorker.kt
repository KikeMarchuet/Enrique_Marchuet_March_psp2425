import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

fun main(args: Array<String>) {
    // Rebem com a primer argument el nom del fitxer a tractar
    val fitxerEntrada = args[0]

    // Com a segon i tercer argument, rebem les línies inicial i final de la imatge
    // que com a Worker tenim que treballar
    val primeraFila = args[1].toInt()
    val ultimaFila = args[2].toInt()

    // Llegim el quart argument que serà el radi a aplicar per al suavitzat
    val radiAplicacio = args[3].toInt()

    // I com a quint argument el nom del fitxer d'eixida
    val fitxerEixida = args[4]

    // Llegim la imatge original
    val img = ImageIO.read(File(fitxerEntrada))

    // Suavitzem només el bloc que ens correspon segons els paràmetres d'entrada
    val blocImg = suavitzaBloc(img, primeraFila, ultimaFila, radiAplicacio)

    // Escrivim el bloc al fitxer que li hem indicat al Worker
    ImageIO.write(blocImg, "jpg", File(fitxerEixida))
}

// Esta funció funciona igual que la Suavitza, però centrant-se en un rango de files
fun suavitzaBloc(img: BufferedImage, primeraFila: Int, ultimaFila: Int, radiAplicacio: Int): BufferedImage {
    // Obtenim les dimensions del Bloc
    val width = img.width
    val height = ultimaFila - primeraFila

    // Creem una imatge resultant amb les mateixes dimensions
    val result = BufferedImage(width, height, img.type)

    // Recorrem cada píxel del bloc original i fent el que feia Suavitza
    // quan no era multiprocés
    for (y in primeraFila until ultimaFila) {
        for (x in 0 until width) {
            result.setRGB(x, y - primeraFila, suavitzaPixel(img, x, y,radiAplicacio))
        }
    }
    return result
}

// Tal qual el teníem a la funció Suavitza
fun suavitzaPixel(img: BufferedImage, x: Int, y: Int, radi: Int): Int {
    // Canvie a radi=6 per a fer més notable el canvi
    val radi = radi
    // Variables que ens van sumant el roig verd i blau dels pixels al voltant del analitzat
    // a una distància de radi
    var roig = 0
    var verd = 0
    var blau = 0
    // Variable per a veure quants pixels s'han considerat i fer la mitjana
    var total = 0

    // Doble bucle que fa el barrido en 'x' i 'y' al voltant del pixel que volem suavitzar
    for (distancia_x in -radi..radi) {
        for (distancia_y in -radi..radi) {
            // Observem el pixel dins del radi
            val posicio_x = x + distancia_x
            val posicio_y = y + distancia_y
            // Si cau dins la imatge, el tenim en conte
            if (posicio_x in 0 until img.width && posicio_y in 0 until img.height) {
                // Veiem el valor de R,G,B i el sumem a les variables que arrepleguen la suma
                // del R,G,B de tot el voltant
                val rgb = img.getRGB(posicio_x, posicio_y)
                roig += (rgb shr 16) and 0xFF
                verd += (rgb shr 8) and 0xFF
                blau += rgb and 0xFF
                // Incrementem esta variable per a poder fer la mitjana després
                total++
            }
        }
    }
    // Tornem ja la mitjana calculada dividint els valors entre el total de pixels
    // que hem trovat al voltant del pixel a suavitzar
    return (roig / total shl 16) or (verd / total shl 8) or (blau / total)
}