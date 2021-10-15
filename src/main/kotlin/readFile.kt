import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.filechooser.FileSystemView

fun getLink(): String? {
    val fileOpen = JFileChooser()
    val filterCsv = FileNameExtensionFilter("CSV files (*.csv)", "csv")
    val filterTxt = FileNameExtensionFilter("Text files (*.txt)", "txt")
    fileOpen.addChoosableFileFilter(filterCsv)
    fileOpen.addChoosableFileFilter(filterTxt)
    fileOpen.fileSelectionMode = JFileChooser.FILES_ONLY
    val getFile = fileOpen.showDialog(null, "Open")
    if (getFile == JFileChooser.APPROVE_OPTION) {
        return fileOpen.selectedFile.toString()
    }
    return null
}

fun readFile(): PlotData? {
    val link = getLink().toString()
    if (link.isEmpty())
        return null
    println()
    return when (link.substring(link.length - 4)) {
        ".txt" -> parseTxt(link)
        ".csv" -> parseCsv(link)
        else -> null
    }
}

fun parseCsv(link: String): PlotData? {
    val reader = Files.newBufferedReader(Paths.get(link))
    val csvParser = CSVParser(reader, CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .withIgnoreHeaderCase()
        .withTrim())
    return csvPlotData(csvParser)
}

fun csvPlotData(csvParser: CSVParser): PlotData? {
    val categories = mutableListOf<String>()
    val values = mutableListOf<List<Float>>()
    val headerList = csvParser.headerMap.toList().sortedBy { it.second }.map { it.first }
    val label = headerList[0]
    val rows: List< String > = headerList.drop(0)
    csvParser.forEach { record ->
        categories.add(record.get(0).toString())
        values.add(record.toList().drop(1).map { it.toFloat() })
    }
    return PlotData(categories, rows, values, label)
}

fun parseTxt(link: String): PlotData? {
    TODO()
}
