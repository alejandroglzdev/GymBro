/**
 * Class that represents a User in a gym training app.
 *
 * @property email A string representing the email of the user.
 * @property username A string representing the username of the user.
 * @property phone A string representing the phone number of the user.
 * @property name A string representing the name of the user.
 * @property surnames A string representing the surnames of the user.
 * @property photoURL A string representing the URL of the user's profile photo.
 * @property description A string representing a description of the user.
 * @property birthDate A string representing the birthdate of the user.
 * @property gender A string representing the gender of the user.
 *
 */
data class User(
    var email: String? = null,
    var username: String? = null,
    var phone: String? = null,
    var name: String? = null,
    var surnames: String? = null,
    var photoURL: String? = null,
    var description: String? = null,
    var birthDate: String? = null,
    var gender: String? = null
)
