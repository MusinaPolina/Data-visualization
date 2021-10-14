import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Font


val shiftCoefficientLeft = 0.135f
val shiftCoefficientRight = 1 - shiftCoefficientLeft
val shiftCoefficientTop = 0.15f
val shiftCoefficientBottom = 1 - shiftCoefficientTop

private fun printGrid(canvas: Canvas, width: Int, height: Int): GridPoints {
    val gridPoints = GridPoints(width.toFloat() * shiftCoefficientLeft,
        height.toFloat() * shiftCoefficientBottom,
        width.toFloat() * shiftCoefficientRight,
        height.toFloat() * shiftCoefficientTop)
    canvas.drawLine(gridPoints.left, gridPoints.bottom, gridPoints.left, gridPoints.top, paintBlack)
    canvas.drawLine(gridPoints.left, gridPoints.bottom, gridPoints.right, gridPoints.bottom, paintBlack)
    return gridPoints
}

data class ValuesRange(val start: Int, val step: Int, val number: Int)

private fun normalization(values: List<List<Float>>): ValuesRange {
    val all: List<Float> = values.flatten().sorted().distinct()
    require(all.isNotEmpty())
    val step: Int = Math.max(1, ((all.last() - all.first()) / Math.max(1, (all.size - 1))).toInt())
    val start = kotlin.math.floor(all.first() - step).toInt()
    return ValuesRange(start, step, (kotlin.math.ceil((all.last() - start) / step)).toInt() + 1)
}

fun printLine(canvas: Canvas, data: PlotData, width: Int, height: Int) {
    val gridPoints = printGrid(canvas, width, height)
    printCategories(canvas, data.categories, gridPoints)
    val normal = normalization(data.values)
    printRange(canvas, normal, gridPoints)

    val number = data.values[0].size
    val shift = gridPoints.width / (number + 1)
    data.values.forEachIndexed { colourNumber, value ->
        var previousX = gridPoints.left + shift
        var previousY = pointPositionY(value[0], gridPoints, normal)
        value.forEachIndexed { index, it ->
            val currentX = gridPoints.left + shift * (index + 1)
            val currentY = pointPositionY(value[index], gridPoints, normal)
            canvas.drawLine(previousX, previousY,
                currentX, currentY, basePaints[colourNumber])
            previousX = currentX
            previousY = currentY
        }
    }
}

private fun pointPositionY(value: Float, gridPoints: GridPoints, normal: ValuesRange): Float {
    val shift = gridPoints.height / (normal.number + 1)
    val realHeight = gridPoints.height / (normal.number + 1)
    return gridPoints.bottom - shift - realHeight * (value - normal.start) / normal.step
}

private val rangeShift = 40f
private val rangePoint = 5f
private val rangeFont = Font(typeface, 15f)
private val rangeNameShift = 5f

private fun printRange(canvas: Canvas, valueRange: ValuesRange, gridPoints: GridPoints) {
    val shift = gridPoints.height / (valueRange.number + 1)
    repeat(valueRange.number) { index ->
        val currentShift = gridPoints.bottom - shift * (index + 1)
        val currentNumber = valueRange.start + valueRange.step * index
        canvas.drawString(currentNumber.toString(),
            gridPoints.left - rangeShift, currentShift + rangeNameShift,
            rangeFont, paintBlack)
        canvas.drawLine(gridPoints.left - rangePoint, currentShift,
            gridPoints.left + rangePoint, currentShift, paintBlack)
    }
}

private val categoriesShift = 20f
private val categoriesFont = Font(typeface, 15f)
private val categoriesPoint = 5f
private val categoriesNameShift = 20f

private fun printCategories(canvas: Canvas, categories: List<String>, gridPoints: GridPoints) {
    val shift = gridPoints.width / (categories.size + 1)
    categories.forEachIndexed { index, name ->
        val currentShift = gridPoints.left + shift * (index + 1)
        canvas.drawString(name, currentShift - categoriesNameShift, gridPoints.bottom + categoriesShift,
            categoriesFont, paintBlack)
        canvas.drawLine(currentShift, gridPoints.bottom + categoriesPoint,
            currentShift, gridPoints.bottom - categoriesPoint, paintBlack)
    }
}

