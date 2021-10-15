import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Rect

fun printStackedColumnChart(canvas: Canvas, plotData: PlotData, x: Float, y: Float, width: Int, height: Int) {
    setShift(x, y)
    val columns = getColumns(plotData.values)
    val chartData = ChartData(plotData, GridPoints(width, height), normalizationStackedColumn(columns))
    printGrid(canvas, chartData)
    printStackedColumnSets(canvas, chartData)
    setShift(0f, 0f)
}


fun printStackedColumnSets(canvas: Canvas, chartData: ChartData) {
    val column = getColumns(chartData.plotData.values)
    column.forEachIndexed { index, it ->
        printColumn(canvas, chartData, it, index, column.size)
    }
}

fun getColumns(values: List<List<Float>>): List<List<Float>> {
    val columns = mutableListOf<List<Float>>()
    repeat(values[0].size) { index ->
        val column = mutableListOf<Float>()
        values.forEach {
            column.add(it[index])
        }
        columns.add(column)
    }
    return columns
}

fun printColumn(canvas: Canvas, chartData: ChartData, values: List<Float>, indexSet: Int, number: Int) {
    val gridPoints = chartData.gridPoints
    val valueRanges = chartData.valuesRange
    val setWidth = gridPoints.width / number
    val columnWidth = setWidth * 0.5f
    val setShift = gridPoints.left + setWidth * (indexSet + 0.25f)

    val zeroShift = gridPoints.height / (valueRanges.number + 1)
    val range = valueRanges.step * (valueRanges.number - 1)
    var sum = 0f

    values.forEachIndexed { index, it ->
        val yBottom = gridPoints.bottom - zeroShift - sum
        val yShift = (gridPoints.height - zeroShift * 2) * (it / range)
        sum += yShift
        val rect = Rect(setShift, yBottom - yShift, setShift + columnWidth, yBottom)
        drawRect(canvas, rect, basePaints[index])
    }
}