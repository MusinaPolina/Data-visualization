import org.jetbrains.skija.Canvas

fun printLineChart(canvas: Canvas, plotData: PlotData, x: Float, y: Float, width: Int, height: Int) {
    setShift(x, y)
    val chartData = ChartData(plotData, GridPoints(width, height), normalizationLine(plotData.values))
    printGrid(canvas, chartData)
    printLines(canvas, chartData)
    setShift(0f, 0f)
}

private fun printLines(canvas: Canvas, chartData: ChartData) {
    val number = chartData.plotData.values[0].size
    val shift = chartData.gridPoints.width / number
    chartData.plotData.values.forEachIndexed { colourNumber, value ->
        printLine(canvas, chartData.gridPoints, value, chartData.valuesRange, shift, colourNumber)
    }
}

private fun printLine(canvas: Canvas, gridPoints: GridPoints,
    value: List<Float>, normal: ValuesRange, shift: Float, colourNumber: Int) {
    var previousX = gridPoints.left + shift * 0.5f
    var previousY = pointPositionY(value[0], gridPoints, normal)
    value.forEachIndexed { index, it ->
        val currentX = gridPoints.left + shift * (index + 0.5f)
        val currentY = pointPositionY(value[index], gridPoints, normal)
        drawLine(canvas,
            previousX, previousY,
            currentX, currentY, basePaints[colourNumber]
        )
        previousX = currentX
        previousY = currentY
    }
}

private fun pointPositionY(value: Float, gridPoints: GridPoints, normal: ValuesRange): Float {
    val shift = gridPoints.height / (normal.number + 1)
    val realHeight = gridPoints.height / (normal.number + 1)
    return gridPoints.bottom - shift - realHeight * (value - normal.start) / normal.step
}
