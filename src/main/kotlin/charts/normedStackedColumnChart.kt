import org.jetbrains.skija.Canvas

fun normedPlotData(plotData: PlotData): PlotData {
    val columns = getColumns(plotData.values)
    val normedColumns = columns.map { column ->
        column.map { it / column.sum() * 100}
    }
    return PlotData(plotData.categories, plotData.rows, getColumns(normedColumns), plotData.label)
}

fun printNormedStackedColumnChart(canvas: Canvas, plotData: PlotData, x: Float, y: Float, width: Int, height: Int) {
    setShift(x, y)
    val normedPlotData = normedPlotData(plotData)
    val chartData = ChartData(normedPlotData, GridPoints(width, height), normalizationNormedStackedColumn())

    printGrid(canvas, chartData)
    printStackedColumnSets(canvas, chartData)
}