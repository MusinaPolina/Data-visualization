import org.jetbrains.skija.Canvas

var userLink = ""
var useDefaultLink = true

fun defaultLink(name: String): String {
    return "defaultCharts\\$name.csv"
}

val defaultWidth = 600
val defaultHeight = 450
val cWidth = 800f
val cHeight = 600f

fun getCoefficient(w: Int, h: Int): Pair<Float,Float> {
    return Pair(w / cWidth, h / cHeight)
}

fun lineChart(canvas: Canvas, w: Int, h: Int, xShift: Float, yShift: Float) {
    val link = if (useDefaultLink) defaultLink("line") else userLink
    val plotData = readFile(link)!!
    val (cw, ch) = getCoefficient(w, h)
    printLineChart(canvas, plotData, xShift * cw, yShift * ch,
        (defaultWidth * cw).toInt(), (defaultHeight * ch).toInt())
}

fun columnChart(canvas: Canvas, w: Int, h: Int, xShift: Float, yShift: Float) {
    val link = if (useDefaultLink) defaultLink("column") else userLink
    val plotData = readFile(link)!!
    val (cw, ch) = getCoefficient(w, h)
    printColumnChart(canvas, plotData, xShift * cw, yShift * ch,
        (defaultWidth * cw).toInt(), (defaultHeight * ch).toInt())
}

fun stackedColumnChart(canvas: Canvas, w: Int, h: Int, xShift: Float, yShift: Float) {
    val link = if (useDefaultLink) defaultLink("columnStacked") else userLink
    val plotData = readFile(link)!!
    val (cw, ch) = getCoefficient(w, h)
    printStackedColumnChart(canvas, plotData, xShift * cw, yShift * ch,
        (defaultWidth * cw).toInt(), (defaultHeight * ch).toInt())
}

fun normedStackedColumnChart(canvas: Canvas, w: Int, h: Int, xShift: Float, yShift: Float) {
    val link = if (useDefaultLink) defaultLink("columnNormedStacked") else userLink
    val plotData = readFile(link)!!
    val (cw, ch) = getCoefficient(w, h)
    printNormedStackedColumnChart(canvas, plotData, xShift * cw, yShift * ch,
        (defaultWidth * cw).toInt(), (defaultHeight * ch).toInt())
}

fun pieChart(canvas: Canvas, w: Int, h: Int, xShift: Float, yShift: Float) {
    val link = if (useDefaultLink) defaultLink("pie") else userLink
    val plotData = readFile(link)!!
    val (cw, ch) = getCoefficient(w, h)
    printPieChart(canvas, plotData, xShift * cw, yShift * ch,
        (defaultWidth * cw).toInt(), (defaultHeight * ch).toInt())
}