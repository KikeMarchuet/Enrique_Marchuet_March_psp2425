package figures

class Punt {
    // Classe que representa una ubicació a l'escena
    var x: Int
        private set
    var y: Int
        private set

    constructor() {
        // Mètode Constructor
        this.x = 0
        this.y = 0
    }

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    // Mètode setter
    fun set(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
}