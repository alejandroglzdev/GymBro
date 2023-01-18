/**
 * Class that represents a personal record (PR) in a gym training app.
 *
 * @property ejercicio A string representing the name of the exercise of the PR.
 * @property fecha A string representing the date the PR was achieved.
 * @property peso A string representing the weight used in the PR.
 * @property repeticiones A string representing the number of reps achieved in the PR.
 *
 */
data class PR(
    var ejercicio: String? = null,
    var fecha: String? = null,
    var peso: String? = null,
    var repeticiones: String? = null,
)
