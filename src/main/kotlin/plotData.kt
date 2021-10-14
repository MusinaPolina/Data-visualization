data class PlotData(val categories : List<String>, val rows: List <String >,
                    val values: List < List < Float >>, val label: String)

val shiftCoefficientLeft = 0.135f
val shiftCoefficientRight = 1 - shiftCoefficientLeft
val shiftCoefficientTop = 0.15f
val shiftCoefficientBottom = 1 - shiftCoefficientTop

data class GridPoints(private val originalWidth: Int, private val originalHeight: Int) {
    val left: Float = originalWidth.toFloat() * shiftCoefficientLeft
    val bottom: Float = originalHeight.toFloat() * shiftCoefficientBottom
    val right: Float = originalWidth.toFloat() * shiftCoefficientRight
    val top: Float = originalHeight.toFloat() * shiftCoefficientTop
    val width = right - left
    val height = bottom - top
}

data class ValuesRange(val start: Int, val step: Int, val number: Int)

data class ChartData(val plotData: PlotData, val gridPoints: GridPoints, val valuesRange: ValuesRange)
