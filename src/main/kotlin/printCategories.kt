import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Font
import org.jetbrains.skija.Rect

private val categoriesShift = 50f
private val categoriesFontSize = 15f
private val font = Font(typeface, categoriesFontSize)

fun printCategories(canvas: Canvas, categories: List<String>, gridPoints: GridPoints) {
    val line = categoriesLine(categories)
    val center = gridPoints.left + gridPoints.width * 0.5f
    val start = center - stringCenterShift(line, categoriesFontSize)
    val y = gridPoints.bottom + categoriesShift
    drawString(canvas, line, start, y, font, paintBlack)

    val scaledShift = categoriesFontSize * typefaceCoefficient
    var currentLeft = start
    categories.forEachIndexed { index, it ->
        val rect = Rect(currentLeft, y, currentLeft + scaledShift, y - scaledShift)
        drawRect(canvas, rect, basePaints[index])
        currentLeft += (it.length + 4) * scaledShift // two space it.length two spaces
    }
}

fun categoriesLine(categories: List<String>): String {
    val line = StringBuilder()
    categories.forEach {
        line.append("  ")
        line.append(it)
        line.append("  ")
    }
    return line.toString()
}