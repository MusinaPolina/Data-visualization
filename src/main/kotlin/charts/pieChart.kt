import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Paint

fun printPieChart(canvas: Canvas, plotData: PlotData, x: Float, y: Float, width: Int, height: Int) {
    setShift(x, y)
    val gridPoints = GridPoints(width, height)
    printCategories(canvas, gridPoints, plotData.categories, plotData.label)
    printPie(canvas, gridPoints, plotData.values[0])
}

fun printPie(canvas: Canvas, gridPoints: GridPoints, values: List<Float>) {
    val sum = values.sum()
    var already = 0f
    values.forEachIndexed { index, it ->
        printSector(canvas, gridPoints, already, it / sum, basePaints[index])
        already += it / sum
    }
}

val radiusCofficient = 0.35f

fun printSector(canvas: Canvas, gridPoints: GridPoints, left: Float, right: Float, paint: Paint) {
    val center = Pair(gridPoints.originalWidth * 0.5f, gridPoints.originalHeight * 0.5f)
    val shift = gridPoints.originalHeight * radiusCofficient
    canvas.drawArc(center.first - shift, center.second - shift,
        center.first + shift, center.second + shift,
        left * 360f, right * 360f, true, paint)
}