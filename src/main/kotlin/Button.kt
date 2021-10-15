import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Font
import org.jetbrains.skija.Rect
import java.lang.Float.min

class Button(val label: String,
             val stx0: Float, val sty0: Float, val stx1: Float, val sty1: Float) {
    init {
        buttons.add(this)
    }
    private var choosen: Boolean= false
    private var visible: Boolean = true
    private var x0 = stx0
    private var y0 = sty0
    private var x1 = stx1
    private var y1 = sty1
    private val originalWidth = 800f
    private val originalHeight = 600f
    private val eps = 1f
    private var fontSize = (y1 - y0) * 0.3f
    private var font = Font(typeface, fontSize)

    fun setVisible(state: Boolean) { visible = state }
    fun setChoosen(state: Boolean) { choosen = state }

    private fun set(width: Int, height: Int) {
        val cWidth = width.toFloat() / originalWidth
        val cHeight = height.toFloat() / originalHeight
        x0 = stx0 * cWidth
        y0 = sty0 * cHeight
        x1 = stx1 * cWidth
        y1 = sty1 * cHeight
        fontSize = min((y1 - y0) * 0.6f, (x1 - x0 - 6 * eps) / (label.length * typefaceCoefficient))
        font = Font(typeface, fontSize)
    }

    fun print(canvas: Canvas, width: Int, height: Int) {
        set(width, height)
        if (visible) {
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
        val paint = when {
            choosen -> paintPink
            checkMouse() -> paintGrey
            else -> paintWhite
        }
        val rect = Rect(x0, y0, x1, y1)
        val rectEps = Rect(x0 + eps, y0 + eps, x1 - eps, y1 - eps)
        drawRect(canvas, rect, paintBlack)
        drawRect(canvas, rectEps, paint)
    }

    fun clicked(): Boolean {
        return if (State.clickCount > 0 && checkMouse() && visible) {
            State.clickCount = 0
            true
        } else
            false
    }
    private fun checkMouse(): Boolean {
        return (State.mouseX in x0..x1 && State.mouseY in y0..y1)
    }
}