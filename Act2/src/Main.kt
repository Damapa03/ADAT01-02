import java.nio.file.Path

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val file: Path = Path.of("src/ficheros/calificaciones.csv")
    val fuction = Functions(file)

    println(fuction.failPass(fuction.finalGrade(fuction.read())))
}