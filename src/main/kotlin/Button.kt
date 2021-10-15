import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Font
import org.jetbrains.skija.Rect

class Button( val label: String,
             val x0: Float, val y0: Float, val x1: Float, val y1: Float) {
    init {
        buttons.add(this)
    }
    private var isVisible: Boolean = true
    private val eps = 1f
    private val fontSize = (y1 - y0) * 0.3f
    private val font = Font(typeface, fontSize)

    fun visible(state: Boolean) { isVisible = state }

    fun print(canvas: Canvas) {
        if (isVisible) {
            printRect(canvas)
            printLabel(canvas)
        }
    }

    private fun printLabel(canvas: Canvas) {
        val xm = (x0 + x1) / 2
        drawString(canvas, label, xm - stringCenterShift(label, fontSize),
            (y0 + y1) * 0.5f + fontSize * (1 - typefaceCoefficient), font, paintBlack)
    }

    private fun printRect(canvas: Canvas) {
        val paint = if (checkMouse()) paintGrey else paintWhite
        val rect = Rect(x0, y0, x1, y1)
        val rectEps = Rect(x0 + eps, y0 + eps, x1 - eps, y1 - eps)
        drawRect(canvas, rect, paintBlack)
        drawRect(canvas, rectEps, paint)
    }

    fun clicked(): Boolean {
        return if (State.clickCount > 0 && checkMouse() && isVisible) {
            State.clickCount = 0
            true
        } else
            false
    }
    private fun checkMouse(): Boolean {
        return (State.mouseX in x0..x1 && State.mouseY in y0..y1)
    }
}