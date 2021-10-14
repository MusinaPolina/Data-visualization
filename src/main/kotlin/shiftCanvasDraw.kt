import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Paint
import org.jetbrains.skija.Font
import org.jetbrains.skija.Rect

fun drawLine(canvas: Canvas, x0: Float, y0: Float, x1: Float, y1: Float, paint: Paint,
             xShift: Float = 0f, yShift: Float = 0f) {
    canvas.drawLine(x0 + xShift, y0 + yShift, x1 + xShift, y1 + yShift, paint)
}

fun drawString(canvas: Canvas, string: String, x: Float, y: Float, font: Font, paint: Paint,
             xShift: Float = 0f, yShift: Float = 0f) {
    canvas.drawString(string, x + xShift, y + yShift, font, paint)
}

fun drawRect(canvas: Canvas, rect: Rect, paint: Paint,
             xShift: Float = 0f, yShift: Float = 0f) {
    val currentRect = Rect(rect.left + xShift, rect.top + yShift, rect.right + xShift, rect.bottom + yShift)
    canvas.drawRect(currentRect, paint)
}