package com.example.scenariosgames.CreationGame
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.R
import java.util.Scanner


class CreateScenarios : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_scenarios)

    }

    data class Scenario(val description: String, val options: List<Option>)

    data class Option(val choice: String, val nextScenarioIndex: Int)

    fun main() {
        val scenarios = createScenarios()
        playGame(scenarios)
    }

    fun createScenarios(): List<Scenario> {
        return listOf(
            Scenario(
                "Vous êtes dans une forêt sombre. Que faites-vous ?",
                listOf(
                    Option("Explorez la forêt", 1),
                    Option("Rebroussez chemin", 2)
                )
            ),
            Scenario(
                "Vous trouvez un trésor caché dans un arbre. Que faites-vous ?",
                listOf(
                    Option("Ouvrez le trésor", 3),
                    Option("Ignorez le trésor et continuez à explorer", 4)
                )
            ),
            Scenario(
                "Vous rebroussez chemin et retournez à votre point de départ.",
                listOf(
                    Option("Recommencer", 0),
                    Option("Quitter", -1)
                )
            ),
            Scenario(
                "Le trésor est piégé et vous vous retrouvez emprisonné. Fin du jeu.",
                listOf(
                    Option("Recommencer", 0),
                    Option("Quitter", -1)
                )
            ),
            Scenario(
                "Vous continuez à explorer et trouvez un ruisseau. Que faites-vous ?",
                listOf(
                    Option("Boire de l'eau", 5),
                    Option("Suivre le cours du ruisseau", 6)
                )
            ),
            Scenario(
                "L'eau du ruisseau est empoisonnée. Vous mourrez. Fin du jeu.",
                listOf(
                    Option("Recommencer", 0),
                    Option("Quitter", -1)
                )
            ),
            Scenario(
                "Vous suivez le cours du ruisseau et trouvez un village paisible. Vous avez réussi ! Fin du jeu.",
                listOf(
                    Option("Recommencer", 0),
                    Option("Quitter", -1)
                )
            )
        )
    }

    fun playGame(scenarios: List<Scenario>) {
        var currentScenarioIndex = 0

        while (currentScenarioIndex != -1) {
            val currentScenario = scenarios[currentScenarioIndex]
            println(currentScenario.description)

            for (i in currentScenario.options.indices) {
                println("${i + 1}. ${currentScenario.options[i].choice}")
            }

            val scanner = Scanner(System.`in`)
            var userChoice = -1

            while (userChoice !in 1..currentScenario.options.size) {
                print("Choisissez une option : ")
                userChoice = scanner.nextInt()
            }

            val nextScenarioIndex = currentScenario.options[userChoice - 1].nextScenarioIndex
            currentScenarioIndex = nextScenarioIndex
        }

        println("Merci d'avoir joué !")
    }


}