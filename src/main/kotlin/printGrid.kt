import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Font
import java.lang.Float.min

fun printGrid(canvas: Canvas, chartData: ChartData) {
    printAxes(canvas, chartData.gridPoints)
    printRows(canvas, chartData.plotData.rows, chartData.gridPoints)
    printRange(canvas, chartData.valuesRange, chartData.gridPoints)
    printCategories(canvas, chartData.gridPoints, chartData.plotData.categories, chartData.plotData.label)
}

fun printAxes(canvas: Canvas, gridPoints: GridPoints) {
    drawLine(canvas, gridPoints.left, gridPoints.bottom, gridPoints.left, gridPoints.top, paintBlack)
    drawLine(canvas, gridPoints.left, gridPoints.bottom, gridPoints.right, gridPoints.bottom, paintBlack)
}

private val rangeShift = 0.3f
private val rangeFont = Font(typeface, 15f)
private val rangeNameShift = 5f

fun printRange(canvas: Canvas, valueRange: ValuesRange, gridPoints: GridPoints) {
    val shift = gridPoints.height / (valueRange.number + 1)
    repeat(valueRange.number) { index ->
        val currentShift = gridPoints.bottom - shift * (index + 1)
        val currentNumber = valueRange.start + valueRange.step * index
        val xShift =  min((gridPoints.originalWidth - gridPoints.width) * 0.5f * rangeShift, 40f)
        drawString(canvas, currentNumber.toString(),
            gridPoints.left - xShift, currentShift + rangeNameShift,
            rangeFont, paintBlack)
        drawLine(canvas,gridPoints.left, currentShift,
            gridPoints.right, currentShift, paintGrey)
    }
}

private val rowsShift = 20f
private val rowsFontSize = 15f
private val rowsFont = Font(typeface, rowsFontSize)
private val rowsPoint = 5f

fun printRows(canvas: Canvas, rows: List<String>, gridPoints: GridPoints) {
    val shift = gridPoints.width / rows.size
    rows.forEachIndexed { index, name ->
        val currentShift = gridPoints.left + shift * (index + 0.5f)
        val nameShift = stringCenterShift(name, rowsFontSize)
        drawString(canvas, name, currentShift - nameShift, gridPoints.bottom + rowsShift,
            rowsFont, paintBlack)
        drawLine(canvas, currentShift, gridPoints.bottom + rowsPoint,
            currentShift, gridPoints.bottom - rowsPoint, paintBlack)
    }
}