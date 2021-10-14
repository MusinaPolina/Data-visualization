import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Rect

fun printColumnChart(canvas: Canvas, plotData: PlotData, x: Float, y: Float, width: Int, height: Int) {
    setShift(x, y)
    val chartData = ChartData(plotData, GridPoints(width, height), normalizationColumn(plotData.values))
    printGrid(canvas, chartData)
    printColumnSets(canvas, chartData)
    printCategories(canvas, plotData.categories, chartData.gridPoints)
}

private fun printColumnSets(canvas: Canvas, chartData: ChartData) {
    chartData.plotData.values.forEachIndexed { setNumber, values ->
        printColumnSet(canvas, chartData.gridPoints, chartData.valuesRange, values,
            chartData.plotData.values.size, setNumber)
    }
}

private fun printColumnSet(canvas: Canvas, gridPoints:GridPoints, valueRange: ValuesRange,
                           values: List<Float>, setSize: Int, setNumber: Int) {
    values.forEachIndexed { number, it ->
        printColumn(canvas, gridPoints, valueRange, setSize, values.size, setNumber, number, it)
    }
}

private fun printColumn(canvas: Canvas, gridPoints: GridPoints, valueRange: ValuesRange,
                        setSize: Int, size: Int,
                        setNumber: Int, number: Int,
                        value: Float) {
    val setWidth = gridPoints.width / size
    val setShift = gridPoints.left +  setWidth * number
    val columnWidth = setWidth / (setSize + 1)
    val shiftX = setShift + columnWidth * (setNumber + 0.5f)
    val shiftY = gridPoints.height / (valueRange.number + 1)
    val range = (valueRange.number - 1) * valueRange.step
    val top = (gridPoints.height - shiftY * 2) * (value / range)
    val rect = Rect(shiftX, gridPoints.bottom - shiftY - top,
        shiftX + columnWidth, gridPoints.bottom - shiftY)
    drawRect(canvas, rect, basePaints[setNumber])
}