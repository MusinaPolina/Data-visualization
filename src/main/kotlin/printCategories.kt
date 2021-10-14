import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Font
import org.jetbrains.skija.Rect
import java.util.*

private val categoriesShift = 0.6f
private val categoriesFontSize = 15f
private val font = Font(typeface, categoriesFontSize)

fun printCategories(canvas: Canvas, gridPoints: GridPoints, categories: List<String>, label: String) {
    printLabel(canvas, gridPoints, label)

    val line = categoriesLine(categories)
    val center = gridPoints.left + gridPoints.width * 0.5f
    val start = center - stringCenterShift(line, categoriesFontSize)
    val y = gridPoints.bottom + (gridPoints.originalHeight - gridPoints.height) * 0.5f * categoriesShift
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

val labelSize = 20f
val labelFont = Font(typeface, labelSize)

fun printLabel(canvas: Canvas, gridPoints: GridPoints, label: String) {
    val y = gridPoints.top - (gridPoints.originalHeight - gridPoints.height) * 0.25f
    val x = gridPoints.originalWidth * 0.5f - stringCenterShift(label, labelSize)
    drawString(canvas, label, x, y, labelFont, paintBlack)
}