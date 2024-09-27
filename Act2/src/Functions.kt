import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path

class Functions(val file: Path) {

    fun read(): MutableMap<String,List<String>>{
        val br: BufferedReader = Files.newBufferedReader(file)
        val dictionary: MutableMap<String,List<String>> = mutableMapOf()
        var aux = 0
        br.use {
            it.forEachLine {
                line ->
                if (aux != 0) {
                    val lineSplit = line.split(";")
                    dictionary[lineSplit[0]] = listOf(
                        lineSplit[1],
                        lineSplit[2],
                        lineSplit[3],
                        lineSplit[4],
                        lineSplit[5],
                        lineSplit[6],
                        lineSplit[7],
                        lineSplit[8]
                    )
                }else aux++
            }
        }
        return dictionary
    }

    fun finalGrade(dictionary: MutableMap<String,List<String>>): MutableMap<String,List<String>>{

        var final = 0.0
        dictionary.forEach { (clave, valor) ->
            var parcial = 0.0
            var parcial2 = 0.0
            var practica = 0.0

            if(valor[4].isEmpty() && valor[2].isEmpty()){
            }else if (valor[4].isEmpty()){
                parcial = valor[2].replace(",",".").toDouble()
            }else parcial = valor[4].replace(",",".").toDouble()

            if(valor[5].isEmpty() && valor[3].isEmpty()){
            }else if (valor[5].isEmpty()){
                parcial2 = valor[3].replace(",",".").toDouble()
            }else parcial2 = valor[5].replace(",",".").toDouble()

            if (valor[7].isEmpty() && valor[6].isEmpty()){
            }else  if (valor[7].isEmpty()){
                practica = valor[6].replace(",",".").toDouble()
            }else practica = valor[7].replace(",",".").toDouble()

            final = Math.round(parcial*0.3 + parcial2*0.3 + practica*0.4).toDouble()
            val list = valor.toMutableList()
            list.add(final.toString())
            dictionary[clave] = list
        }
        return dictionary
    }

    fun failPass(dictionary: MutableMap<String, List<String>>) : List<Map<String, List<String>>>{
        val fail: MutableMap<String, List<String>> = mutableMapOf()
        val pass: MutableMap<String, List<String>> = mutableMapOf()
        var notas: MutableList<Map<String, List<String>>> = mutableListOf()

        dictionary.forEach { (clave, valor) ->
            var parcial = 0.0
            var parcial2 = 0.0
            var practica = 0.0
            var asistance = valor[1].substring(0,valor[1].indexOf("%")).toInt()

            if(valor[4].isEmpty() && valor[2].isEmpty()){
            }else if (valor[4].isEmpty()){
                parcial = valor[2].replace(",",".").toDouble()
            }else parcial = valor[4].replace(",",".").toDouble()

            if(valor[5].isEmpty() && valor[3].isEmpty()){
            }else if (valor[5].isEmpty()){
                parcial2 = valor[3].replace(",",".").toDouble()
            }else parcial2 = valor[5].replace(",",".").toDouble()

            if (valor[7].isEmpty() && valor[6].isEmpty()){
            }else  if (valor[7].isEmpty()){
                practica = valor[6].replace(",",".").toDouble()
            }else practica = valor[7].replace(",",".").toDouble()

            val final: Double = valor[8].toDouble()

            if (asistance >= 75){
                if (parcial >= 4 || parcial2 >= 4 || practica >= 4){
                    if (final >= 5){
                        pass["Aprobado"] = listOf(clave,valor[0])
                    }else fail["Suspenso"] = listOf(clave,valor[0])
                }else fail["Suspenso"] = listOf(clave,valor[0])
            }else fail["Suspenso"] = listOf(clave,valor[0])


            notas.add(pass)
            notas.add(fail)

        }
        return notas
    }


}