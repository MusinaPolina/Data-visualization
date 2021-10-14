data class PlotData(val categories : List<String>, val values: List < List < Float >>, val label: String)

data class GridPoints(val left: Float, val bottom: Float, val right: Float, val top: Float) {
    val width = right - left
    val height = bottom - top
}