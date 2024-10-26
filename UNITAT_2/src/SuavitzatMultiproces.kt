import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

fun main(args: Array<String>) {
    // Fem ús de la classe javax.imageio.ImageIO ImageIO per llegir imatge jpg

    try {

        // En base al nom fitxer de entrada proporcionat, averigue el que serà el d'eixida
        val fitxerEntrada = args[0]
        val fitxer = args[0].split(".")
        val fitxerEixida = fitxer[0] + "_suavitzada." + fitxer[1]

        // Llegim fitxer i guardem imatge en "img"
        println("Llegint fitxer d'entrada: $fitxerEntrada")
        val img = ImageIO.read(File(fitxerEntrada))

        // Anem a donar l'opció de que ens donen el radi per teclat
        // tinguent per defecte el valor de 6, en cas de no entrar-lo o bé entrar algo
        // no convertible a int
        println("Quin valor de radi vols aplicar: ")
        val entrada = readLine() + ""
        var radiAplicacio = 6
        if (entrada != "") {
            try {
                radiAplicacio = entrada.toInt()
            } catch (e: NumberFormatException) {
                radiAplicacio = 6
            }
        }

        // Donem informació de que anem a suavitzar i el valor del radi aplicat
        println("Suavitzant imatge aplicant radi: $radiAplicacio")

        // Arrepleguem el resultat de la imatge suavitzada
        // Per a lo que requerix la imatge, el fitxer i el radi amb el que volem suavitzar
        // Necessitem el fitxer d'entrada per a injectar-lo als workers
        val img_suavitzada = suavitzaImatgeProcessos(img, fitxerEntrada,radiAplicacio)

        // Guardem el resultat final en un fitxer al que li afegim "_suavitzada" al nom
        println("Escrivint imatge suavitzada en: $fitxerEixida")
        ImageIO.write(img_suavitzada, "jpg", File(fitxerEixida))

    } catch (e: Exception) {
        // Missatge d'error quan no indiquem cap fitxer, o el fitxer no existeix
        println("Necessite un fitxer .jpg vàlid com a primer argument!")
    }
}

// Funció que suavitza la imatge per blocs
fun suavitzaImatgeProcessos(img: BufferedImage, fitxerEntrada: String, radi: Int): BufferedImage {
    // Obtenim les dimensions de la imatge original
    val width = img.width
    val height = img.height

    // Creem una imatge resultant, amb les mateixes
    // dimensions i tipus que la imatge original
    // Fem ús de la classe java.awt.image.BufferedImage
    val resultat = BufferedImage(width, height, img.type)

    // Dividim la imatge en blocs, tants com nuclis té el micro del sistema
    val numBlocs = Runtime.getRuntime().availableProcessors()

    // Obtenim l'altura de cada bloc, dividint-la entre el número de blocs
    val alturaBlocs = height / numBlocs

    // Creem una llista de processos per a després poder anar esperant-los a tots
    val processos = mutableListOf<Process>()

    // Per a cada bloc averiguem la primera fila y la ultima; seran arguments per al worker
    // També passarem el fitxer d'entrada, radi que vol l'usuari i fitxer d'eixida
    for (i in 0 until numBlocs) {

        // Calculem les files de cada bloc
        val primeraY = i * alturaBlocs
        val ultimaY = if (i == numBlocs - 1) height else (i + 1) * alturaBlocs
    
        // Creem el procés per a cada bloc i el posem en marxa
        // Passem
        val proces = ProcessBuilder("kotlin", "-cp", ".", "SuavitzaWorkerKt", fitxerEntrada, primeraY.toString(), ultimaY.toString(), radi.toString(), "bloc_$i.jpg")
            .start()
    
        // Ara l'afegim a la llista de processos que havíem creat
        processos.add(proces)
        
    }

    println("Esperant la finalització dels $numBlocs processos")
    // Esperem que tots els processos acaben
    processos.forEach { it.waitFor() }

    // Anem a juntar tots els blocs suavitzats, ja que els processos han acabat
    // I tots els processos han generat el corresponent fitxer d'eixida
    for (i in 0 until numBlocs) {
        try{
            // Anem llegint els fitxers que són la senyal de que han finalitzat els processos
            val blocImg = ImageIO.read(File("bloc_$i.jpg"))
            // Per a cada bloc, el punt de inserció dels pixels en "resultat", vindrà
            // determinat per el número de bloc
            val primeraY = i * alturaBlocs
            for (y in 0 until blocImg.height) {
                for (x in 0 until blocImg.width) {
                    // Es a dir, les x es corresponen, pero les 'y' van decalades
                    // a la variable final de "resultat"
                    resultat.setRGB(x, primeraY + y, blocImg.getRGB(x, y))
                }
            }
        } catch (e: javax.imageio.IIOException) {
            // Missatge d'error si un dels blocs ens ha fallat
            println("Error de lectura del fitxer d'un procés");
        }
    }
    return resultat
}