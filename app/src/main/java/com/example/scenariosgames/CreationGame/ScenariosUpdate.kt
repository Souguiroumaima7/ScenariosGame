package com.example.scenariosgames.CreationGame
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.R

class ScenariosUpdate(val name: String, val description: String, val steps: List<Step>) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_scenarios)

    }

    abstract class Step {
        // Méthode abstraite à implémenter par les classes concrètes
        abstract fun execute()
    }
    class ActionStep(val action: String) : Step() {
        override fun execute() {
            // Logique d'exécution de l'action spécifique
        }
    }

    class InstructionStep(val instruction: String) : Step() {
        override fun execute() {
            // Logique d'exécution de l'instruction spécifique
        }
    }
    class ScenarioManager {
        private val scenarios: MutableList<CreateScenarios.Scenario> = mutableListOf()

        fun loadScenarios() {
            // Charger les scénarios à partir d'une source de données
        }
    }

}


fun main() {
    val scenarioManager = ScenariosUpdate.ScenarioManager()
    scenarioManager.loadScenarios()
}

