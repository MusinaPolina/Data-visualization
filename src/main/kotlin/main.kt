import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.swing.Swing
import org.jetbrains.skija.*
import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Font
import org.jetbrains.skija.Paint
import org.jetbrains.skiko.SkiaLayer
import org.jetbrains.skiko.SkiaRenderer
import org.jetbrains.skiko.SkiaWindow
import java.awt.*
import java.awt.event.*
import java.lang.Math.*
import javax.management.InstanceAlreadyExistsException
import javax.swing.JFileChooser
import javax.swing.WindowConstants
import kotlin.math.nextUp


fun main() {
    createWindow("diagram manager")
}

fun createWindow(title: String) = runBlocking(Dispatchers.Swing) {
    val window = SkiaWindow()
    window.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
    window.title = title

    window.layer.renderer = Renderer(window.layer)
    window.layer.addMouseMotionListener(MyMouseMotionAdapter)
    window.layer.addMouseListener(MyMouseAdapter)

    window.preferredSize = Dimension(800, 600)
    window.minimumSize = Dimension(100,100)
    window.pack()
    window.layer.awaitRedraw()
    window.isVisible = true
}

val buttons: MutableList<Button> = mutableListOf()
val buttonOpenFile = Button("open file", 50f, 50f, 150f, 75f)

val buttonColumnChart = Button("column", 50f, 200f, 200f, 225f)
val buttonStackedColumnChart = Button("stacked column", 50f, 250f, 200f, 275f)
val buttonNormedStackedColumnChart = Button("100% stacked column", 50f, 300f, 200f, 325f)
val buttonPieChart = Button("pie", 50f, 350f, 200f, 375f)
val buttonLineChart = Button("line",  50f, 400f, 200f, 425f)
val chartButtons = listOf(
    buttonColumnChart,
    buttonStackedColumnChart,
    buttonNormedStackedColumnChart,
    buttonPieChart,
    buttonLineChart
)
var currentChoosen = buttonColumnChart

class Renderer(val layer: SkiaLayer): SkiaRenderer {

    private fun printButtons(canvas: Canvas, width: Int, height: Int) {
        buttons.forEach {
            it.setChoosen(currentChoosen == it)
            it.print(canvas, width, height)
        }
    }

    private fun updateChartButtons() {
        chartButtons.forEach {
            if (it.clicked()) {
                currentChoosen = it
            }
        }
    }

    private fun printChart(canvas: Canvas, w: Int, h: Int) {
        when (currentChoosen) {
            buttonColumnChart -> columnChart(canvas, w, h)
            buttonStackedColumnChart -> stackedColumnChart(canvas, w, h)
            buttonNormedStackedColumnChart -> normedStackedColumnChart(canvas, w, h)
            buttonPieChart -> pieChart(canvas, w, h)
            buttonLineChart -> lineChart(canvas, w, h)
        }
    }

    override fun onRender(canvas: Canvas, width: Int, height: Int, nanoTime: Long) {
        val contentScale = layer.contentScale
        canvas.scale(contentScale, contentScale)
        val w = (width / contentScale).toInt()
        val h = (height / contentScale).toInt()
        printButtons(canvas, w, h)
        updateChartButtons()
        printChart(canvas, w, h)
/*        if (buttonOpenFile.clicked()) {
            buttonOpenFile.setVisible(false)
            readFile()
        }*/
        layer.needRedraw()
    }
}

object State {
    var mouseX = 0f
    var mouseY = 0f
    var clickCount = 0
}

object MyMouseMotionAdapter : MouseMotionAdapter() {
    override fun mouseMoved(event: MouseEvent) {
        State.mouseX = event.x.toFloat()
        State.mouseY = event.y.toFloat()
    }
}

object MyMouseAdapter : MouseAdapter() {
    override fun mouseClicked(event: MouseEvent) {
        State.clickCount += event.clickCount
    }
}