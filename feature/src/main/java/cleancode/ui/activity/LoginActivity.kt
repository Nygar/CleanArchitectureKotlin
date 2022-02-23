package cleancode.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import cleancode.ui.base.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.nygar.feature.BuildConfig
import com.nygar.feature.databinding.ActivityLoginBinding


/**
 * Main application screen. This is the app entry point.
 */
class LoginActivity: BaseActivity() {
    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var auth: FirebaseAuth

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //HideKey.initialize(this)
        FirebaseApp.initializeApp(this)
        binding.signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            resultLogIn.launch(signInIntent)
        }

        binding.btnLogin.setOnClickListener {
            navigateToMessageList()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_TOKEN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        if(currentUser!=null){
            navigateToMessageList()
        }
    }

    /**
     * Goes to the message list screen.
     */
    private fun navigateToMessageList():Boolean {
        navigator.navigateToMain(this)
        return true
    }

    private fun googleLogInSuccessful(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account!!)
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Toast.makeText(this,"ErrorLogin", LENGTH_LONG).show()
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    analitics.sendAnaliticLoginGoogle(user!!.email!!)
                    navigateToMessageList()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"ErrorLogin", LENGTH_LONG).show()
                }
            }
    }

    private val resultLogIn = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK){
            googleLogInSuccessful(result.data)
        }
    }

}