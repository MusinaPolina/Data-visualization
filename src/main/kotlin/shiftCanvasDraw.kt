import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Paint
import org.jetbrains.skija.Font
import org.jetbrains.skija.Rect

object Shift {
    var xShift: Float = 0f
    var yShift: Float = 0f
}

fun setShift(x: Float, y: Float) {
    Shift.xShift = x
    Shift.yShift = y
}

fun drawLine(canvas: Canvas, x0: Float, y0: Float, x1: Float, y1: Float, paint: Paint) {
    canvas.drawLine(x0 + Shift.xShift, y0 + Shift.yShift,
        x1 + Shift.xShift, y1 + Shift.yShift, paint)
}

fun drawString(canvas: Canvas, string: String, x: Float, y: Float, font: Font, paint: Paint) {
    canvas.drawString(string, x + Shift.xShift, y + Shift.yShift, font, paint)
}

fun drawRect(canvas: Canvas, rect: Rect, paint: Paint) {
    val currentRect = Rect(rect.left + Shift.xShift, rect.top + Shift.yShift,
        rect.right + Shift.xShift, rect.bottom + Shift.yShift)
    canvas.drawRect(currentRect, paint)
}

fun drawArc(canvas: Canvas, left: Float, top: Float, right: Float, bottom: Float,
            startAngel: Float, sweepAngel: Float, includeCenter: Boolean, paint: Paint) {
    canvas.drawArc(left + Shift.xShift, top + Shift.yShift,
        right + Shift.xShift, bottom + Shift.yShift,
        startAngel, sweepAngel, includeCenter, paint)
}