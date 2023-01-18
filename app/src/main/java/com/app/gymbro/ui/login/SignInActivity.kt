    package com.app.gymbro
    
    import android.content.ContentValues
    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.util.Log
    import android.util.Log.INFO
    import android.widget.Toast
    import com.app.gymbro.databinding.ActivitySignInBinding
    import com.app.gymbro.ui.main.MainActivity
    import com.google.firebase.auth.EmailAuthProvider
    import com.google.firebase.auth.FirebaseAuth
    import com.google.firebase.auth.FirebaseUser
    import java.util.logging.Level.INFO

    /**
     * SignInActivity is an Activity class that handles the Sign In process of the app.
     *
     * It uses FirebaseAuth to authenticate users and check if their email is verified before
     * allowing access to the MainActivity.
     *
     * On Start it checks if the user is already logged in and if the email is verified, if yes,
     * redirects the user to MainActivity.
     *
     * It also has functionality to redirect the user to ResetPasswordActivity and SignUpActivity
     * by clicking on the corresponding buttons.
     *
     * @author Gymbro Team
     */
    class SignInActivity : AppCompatActivity() {

        /**
         *  Initializes the view binding object and FirebaseAuth instance.
         */
        private lateinit var binding: ActivitySignInBinding
        val firebaseAuth = FirebaseAuth.getInstance()
        var firebaseUser = firebaseAuth.currentUser

        /**
         *  Inflates the layout, sets the content view and calls configureUI method.
         */
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySignInBinding.inflate(layoutInflater)
            setContentView(binding.root)

            configureUI()
        }

        /**
         *  When the activity starts it checks if the user is already logged in and if their email is verified.
         *  If yes, it redirects the user to MainActivity.
         */
        override fun onStart() {
            super.onStart()
            val user = firebaseAuth.currentUser
            val intent = Intent(this, MainActivity::class.java)
            if (user != null){
                if (user.isEmailVerified){
                    startActivity(intent)
                }
            }
        }

        /**
         *  Configures the UI by setting click listeners on buttons.
         */
        private fun configureUI() {
            binding.forgotPasswordButtonSignIn.setOnClickListener {
                val intent = Intent(this, ResetPasswordActivity::class.java)
                startActivity(intent)
            }

            binding.loginButtonSignIn.setOnClickListener {
                logInUser()
            }

            binding.signUpTextButton.setOnClickListener {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }

        }

        /**
         *  Logs in the user using email and password provided by the user.
         *  If the login is successful and the email is verified, redirects the user to MainActivity.
         *  If the login fails, shows a Toast message.
         */
    
        private fun logInUser() {
    
            if (binding.usernameEditTextSignIn.text.toString().trim()
                    .isNotEmpty() && binding.passwordEditTextSignIn.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        binding.usernameEditTextSignIn.text.toString().trim(),
                        binding.passwordEditTextSignIn.text.toString()
                    )
                    .addOnCompleteListener {
    
                        if (it.isSuccessful) {
                            this.firebaseUser = FirebaseAuth.getInstance().currentUser
                            if (firebaseUser?.isEmailVerified == true) {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Mail isn't verified", Toast.LENGTH_SHORT).show()
                            }
    
                        } else {
                            //si el login falla entra aqui
                            Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
                        }
    
    
                    }
            } else {
                binding.usernameEditTextSignIn.setError("Credentials must not be null")
            }
    
    
        }
    }