package converter

import java.lang.IllegalArgumentException
import java.util.*

enum class Temperature(val measure: String) {
    C("Celsius"), F("Fahrenheit"), K("Kelvin");

    companion object {

        private fun aliases(): List<String> {
            return C.aliases() + F.aliases() + K.aliases()
        }

        fun parse(measure: String): Temperature {
            return when {
                C.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> C
                F.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> F
                K.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> K
                else -> throw IllegalArgumentException()
            }
        }

        fun isOfType(measure: String): Boolean {
            return aliases().map { it.toLowerCase() }.contains(measure.toLowerCase())
        }

    }

    fun plural(): String {
        return when (this) {
            C -> "degrees Celsius"
            F -> "degrees Fahrenheit"
            K -> "Kelvins"
        }
    }

    fun singular(): String {
        return when (this) {
            C -> "degree Celsius"
            F -> "degree Fahrenheit"
            K -> "Kelvin"
        }
    }

    fun aliases(): List<String> {
        return when (this) {
            C -> arrayListOf("degree Celsius", "degrees Celsius", "celsius", "dc", "c", "C")
            F -> arrayListOf("degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "f", "F")
            K -> arrayListOf("Kelvin", "Kelvins", "k", "K")
        }
    }

    fun convert(value: Double, measure: Temperature): Double {
        return when (this) {
            C -> fromCelsius(value, measure)
            F -> fromFahrenheit(value, measure)
            K -> fromKelvin(value, measure)
        }
    }

    private fun fromCelsius(value: Double, measure: Temperature): Double {
        return when (measure) {
            C -> 1 * value
            F -> 9.0 / 5.0 * value + 32.0
            K -> value + 273.15
        }
    }

    private fun fromFahrenheit(value: Double, measure: Temperature): Double {
        return when (measure) {
            C -> 5.0 / 9.0 * (value - 32)
            F -> 1 * value
            K -> 5.0 / 9.0 * (value + 459.67)
        }
    }

    private fun fromKelvin(value: Double, measure: Temperature): Double {
        return when (measure) {
            C -> value - 273.15
            F -> 9.0 / 5.0 * value - 459.67
            K -> 1 * value
        }
    }

}

enum class Length(val measure: String) {
    M("Meter"), KM("Kilometer"), CM("Centimeter"),
    MM("Millimeter"), MI("Mile"), YD("Yard"),
    FT("Foot"), IN("Inch");

    companion object {

        private fun aliases(): List<String> {
            return M.aliases() + KM.aliases() + CM.aliases() + MM.aliases() +
                    MI.aliases() + YD.aliases() + FT.aliases() + IN.aliases()
        }

        fun parse(measure: String): Length {
            return when {
                M.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> M
                KM.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> KM
                CM.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> CM
                MM.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> MM
                MI.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> MI
                YD.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> YD
                FT.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> FT
                else -> throw IllegalArgumentException()
            }
        }

        fun isOfType(measure: String): Boolean {
            return aliases().map { it.toLowerCase() }.contains(measure.toLowerCase())
        }

    }

    fun plural(): String {
        return when (this) {
            M -> "meters"
            KM -> "kilometers"
            CM -> "centimeters"
            MM -> "millimeters"
            MI -> "miles"
            YD -> "yards"
            FT -> "feet"
            IN -> "inches"
        }
    }

    fun singular(): String {
        return when (this) {
            M -> "meter"
            KM -> "kilometer"
            CM -> "centimeter"
            MM -> "millimeter"
            MI -> "mile"
            YD -> "yard"
            FT -> "foot"
            IN -> "inch"
        }
    }

    fun aliases(): List<String> {
        return when (this) {
            M -> arrayListOf("m", "meter", "meters")
            KM -> arrayListOf("km", "kilometer", "kilometers")
            CM -> arrayListOf("cm", "centimeter", "centimeters")
            MM -> arrayListOf("mm", "millimeter", "millimeters")
            MI -> arrayListOf("mi", "mile", "miles")
            YD -> arrayListOf("yd", "yard", "yards")
            FT -> arrayListOf("ft", "foot", "feet")
            IN -> arrayListOf("in", "inch", "inches")
        }
    }

    fun convert(value: Double, measure: Length): Double {
        return when (this) {
            M -> convertFromMeter(convertToMeter(value, this), measure)
            KM -> convertFromMeter(convertToMeter(value, this), measure)
            CM -> convertFromMeter(convertToMeter(value, this), measure)
            MM -> convertFromMeter(convertToMeter(value, this), measure)
            MI -> convertFromMeter(convertToMeter(value, this), measure)
            YD -> convertFromMeter(convertToMeter(value, this), measure)
            FT -> convertFromMeter(convertToMeter(value, this), measure)
            IN -> convertFromMeter(convertToMeter(value, this), measure)
        }
    }

    private fun convertToMeter(value: Double, measure: Length): Double {
        return when (measure) {
            M -> 1 * value
            KM -> 1000 * value
            CM -> 0.01 * value
            MM -> 0.001 * value
            MI -> 1609.35 * value
            YD -> 0.9144 * value
            FT -> 0.3048 * value
            IN -> 0.0254 * value
        }
    }

    private fun convertFromMeter(value: Double, measure: Length): Double {
        return when (measure) {
            M -> value / 1
            KM -> value / 1000
            CM -> value / 0.01
            MM -> value / 0.001
            MI -> value / 1609.35
            YD -> value / 0.9144
            FT -> value / 0.3048
            IN -> value / 0.0254
        }
    }

}

enum class Mass(val measure: String) {
    G("Gram"), KG("Kilogram"), MG("Milligram"),
    LB("Pound"), OZ("Ounce");

    companion object {

        private fun aliases(): List<String> {
            return G.aliases() + KG.aliases() + MG.aliases() +
                    LB.aliases() + OZ.aliases()
        }

        fun parse(measure: String): Mass {
            return when {
                G.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> G
                KG.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> KG
                MG.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> MG
                LB.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> LB
                OZ.aliases().map { it.toLowerCase() }.contains(measure.toLowerCase()) -> OZ
                else -> throw IllegalArgumentException()
            }
        }

        fun isOfType(measure: String): Boolean {
            return aliases().map{ it.toLowerCase() }.contains(measure.toLowerCase())
        }

    }

    fun plural(): String {
        return when (this) {
            G -> "grams"
            KG -> "kilograms"
            MG -> "milligrams"
            LB -> "pounds"
            OZ -> "ounces"
        }
    }

    fun singular(): String {
        return when (this) {
            G -> "gram"
            KG -> "kilogram"
            MG -> "milligram"
            LB -> "pound"
            OZ -> "ounce"
        }
    }

    fun aliases(): List<String> {
        return when (this) {
            G -> arrayListOf("g", "gram", "grams")
            KG -> arrayListOf("kg", "kilogram", "kilograms")
            MG -> arrayListOf("mg", "milligram", "milligrams")
            LB -> arrayListOf("lb", "pound", "pounds")
            OZ -> arrayListOf("oz", "ounce", "ounces")
        }
    }

    fun convert(value: Double, measure: Mass): Double {
        return when (this) {
            G -> convertFromGram(convertToGram(value, this), measure)
            KG -> convertFromGram(convertToGram(value, this), measure)
            MG -> convertFromGram(convertToGram(value, this), measure)
            LB -> convertFromGram(convertToGram(value, this), measure)
            OZ -> convertFromGram(convertToGram(value, this), measure)
        }
    }

    private fun convertToGram(value: Double, measure: Mass): Double {
        return when (measure) {
            G -> 1 * value
            KG -> 1000 * value
            MG -> 0.001 * value
            LB -> 453.592 * value
            OZ -> 28.3495 * value
        }
    }

    private fun convertFromGram(value: Double, measure: Mass): Double {
        return when (measure) {
            G -> value / 1
            KG -> value / 1000
            MG -> value / 0.001
            LB -> value / 453.592
            OZ -> value / 28.3495
        }
    }

}

class UnitConverter(private val scanner: Scanner) {

    fun convert(line: String) {
        val lineSplitBySpace = line.trim().split(" ")
        val fromValue: Double
        val fromMeasure: String
        val toMeasure: String
        if (lineSplitBySpace.size == 1) {
            println("Parse error")
            return
        } else if (lineSplitBySpace.size != 4 && lineSplitBySpace.size != 5  && lineSplitBySpace.size != 6) {
            println("Parse error")
            return
        } else if (lineSplitBySpace.size == 4) {
            try {
                fromValue = lineSplitBySpace[0].toDouble()
                fromMeasure = lineSplitBySpace[1]
                toMeasure = lineSplitBySpace[3]
            } catch (ex: Exception) {
                println("Parse error")
                return
            }
        } else if (lineSplitBySpace.size == 5) {
            try {
                fromValue = lineSplitBySpace[0].toDouble()
                val indexOfPreposition = when {
                    lineSplitBySpace.indexOf("to") != -1 -> lineSplitBySpace.indexOf("to")
                    lineSplitBySpace.indexOf("in") != -1 -> lineSplitBySpace.indexOf("in")
                    else -> throw Exception()
                }
                if (indexOfPreposition == 3) {
                    fromMeasure = lineSplitBySpace[1] + " " + lineSplitBySpace[2]
                    toMeasure = lineSplitBySpace[4]
                } else {
                    fromMeasure = lineSplitBySpace[1]
                    toMeasure = lineSplitBySpace[3] + " " + lineSplitBySpace[4]
                }
            } catch (ex: Exception) {
                println("Parse error")
                return
            }
        } else {
            try {
                fromValue = lineSplitBySpace[0].toDouble()
                fromMeasure = lineSplitBySpace[1] + " " + lineSplitBySpace[2]
                toMeasure = lineSplitBySpace[4] + " " + lineSplitBySpace[5]
            } catch (ex: Exception) {
                println("Parse error")
                return
            }
        }

        if (!isKnownMeasure(fromMeasure) && !isKnownMeasure(toMeasure)) {
            printImpossibleConversion("???", "???")
            return
        }
        if (!isKnownMeasure(fromMeasure)) {
            when {
                Temperature.isOfType(toMeasure) ->
                    printImpossibleConversion("???", Temperature.parse(toMeasure).plural())
                Mass.isOfType(toMeasure) ->
                    printImpossibleConversion("???", Mass.parse(toMeasure).plural())
                Length.isOfType(toMeasure) ->
                    printImpossibleConversion("???", Length.parse(toMeasure).plural())
            }
            return
        }
        if (!isKnownMeasure(toMeasure)) {
            when {
                Temperature.isOfType(fromMeasure) ->
                    printImpossibleConversion(Temperature.parse(fromMeasure).plural(), "???")
                Mass.isOfType(fromMeasure) ->
                    printImpossibleConversion(Mass.parse(fromMeasure).plural(), "???")
                Length.isOfType(fromMeasure) ->
                    printImpossibleConversion(Length.parse(fromMeasure).plural(), "???")
            }
            return
        }
        if (!sameMeasures(fromMeasure, toMeasure)) {
            when {
                Temperature.isOfType(fromMeasure) ->
                    when {
                        Mass.isOfType(toMeasure) ->
                            printImpossibleConversion(
                                    Temperature.parse(fromMeasure).plural(),
                                    Mass.parse(toMeasure).plural())
                        Length.isOfType(toMeasure) ->
                            printImpossibleConversion(
                                    Temperature.parse(fromMeasure).plural(),
                                    Length.parse(toMeasure).plural())
                    }
                Mass.isOfType(fromMeasure) -> {
                    when {
                        Temperature.isOfType(toMeasure) ->
                            printImpossibleConversion(
                                    Mass.parse(fromMeasure).plural(),
                                    Temperature.parse(toMeasure).plural())
                        Length.isOfType(toMeasure) ->
                            printImpossibleConversion(
                                    Mass.parse(fromMeasure).plural(),
                                    Length.parse(toMeasure).plural())
                    }
                }
                Length.isOfType(fromMeasure) -> {
                    when {
                        Temperature.isOfType(toMeasure) ->
                            printImpossibleConversion(
                                    Length.parse(fromMeasure).plural(),
                                    Temperature.parse(toMeasure).plural())
                        Mass.isOfType(toMeasure) ->
                            printImpossibleConversion(
                                    Length.parse(fromMeasure).plural(),
                                    Mass.parse(toMeasure).plural())
                    }
                }
            }
            return
        }
        if (Length.isOfType(fromMeasure) && fromValue < 0.0) {
            println("Length shouldn't be negative")
            return
        }
        if (Mass.isOfType(fromMeasure) && fromValue < 0.0) {
            println("Weight shouldn't be negative")
            return
        }

        when {
            Temperature.isOfType(fromMeasure) && Temperature.isOfType(toMeasure) -> {
                val toValue = Temperature.parse(fromMeasure).convert(fromValue, Temperature.parse(toMeasure))
                printResult(fromValue, Temperature.parse(fromMeasure).singular(), Temperature.parse(fromMeasure).plural(),
                        toValue, Temperature.parse(toMeasure).singular(), Temperature.parse(toMeasure).plural())
            }
            Mass.isOfType(fromMeasure) && Mass.isOfType(toMeasure) -> {
                val toValue = Mass.parse(fromMeasure).convert(fromValue, Mass.parse(toMeasure))
                printResult(fromValue, Mass.parse(fromMeasure).singular(), Mass.parse(fromMeasure).plural(),
                        toValue, Mass.parse(toMeasure).singular(), Mass.parse(toMeasure).plural())
            }
            Length.isOfType(fromMeasure) && Length.isOfType(toMeasure) -> {
                val toValue = Length.parse(fromMeasure).convert(fromValue, Length.parse(toMeasure))
                printResult(fromValue, Length.parse(fromMeasure).singular(), Length.parse(fromMeasure).plural(),
                        toValue, Length.parse(toMeasure).singular(), Length.parse(toMeasure).plural())
            }
        }

    }


    private fun printImpossibleConversion(fromMeasure: String, toMeasure: String) {
        println("Conversion from $fromMeasure to $toMeasure is impossible")
    }

    private fun isKnownMeasure(measure: String): Boolean {
        return Temperature.isOfType(measure) ||
                Mass.isOfType(measure) ||
                Length.isOfType(measure)
    }

    private fun sameMeasures(fromMeasure: String, toMeasure: String): Boolean {
        return Temperature.isOfType(fromMeasure) && Temperature.isOfType(toMeasure) ||
                Mass.isOfType(fromMeasure) && Mass.isOfType(toMeasure) ||
                Length.isOfType(fromMeasure) && Length.isOfType(toMeasure)
    }

    private fun isSingular(number: Double): Boolean = number == 1.0

    private fun printResult(input: Double, inputMeasure: String, inputMeasurePlural: String,
                    result: Double, resultMeasure: String, resultMeasurePlural: String) {
        println("$input ${if (isSingular(input)) inputMeasure else inputMeasurePlural} " +
                "is $result ${if (isSingular(result)) resultMeasure else resultMeasurePlural}")
    }

}

fun main() {
    val scanner = Scanner(System.`in`)
    val unitConverter = UnitConverter(scanner)
    while (true) {
        print("Enter what you want to convert (or exit): ")
        val line = scanner.nextLine()
        if (line.isBlank()) {
            println("Parse error")
            continue
        }
        if (line.split(" ")[0] == "exit") break
        unitConverter.convert(line)
    }
}
