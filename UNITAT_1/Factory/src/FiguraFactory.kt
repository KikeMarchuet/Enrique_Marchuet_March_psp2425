package figures

// Interficie FiguraFactory, per tal d'implementar
// el patro Factory per a la creacio de figures
interface FiguraFactory {
    fun createFigura(): Figura
}
