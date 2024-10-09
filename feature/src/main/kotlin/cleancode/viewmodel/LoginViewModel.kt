package cleancode.viewmodel

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nygar.common.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor() : ViewModel() {
        private val auth: FirebaseAuth = FirebaseAuth.getInstance()

        var isLogingFinished by mutableStateOf(false)
        var isAnimationComplete by mutableStateOf(false)
        val isAllowLoginNavigate by derivedStateOf { isAnimationComplete && isLogingFinished }
        var isShowingAnimation by mutableStateOf(false)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(BuildConfig.GOOGLE_TOKEN)
                .requestEmail()
                .build()

        private fun firebaseAuthWithGoogle(
            acct: GoogleSignInAccount,
            auth: FirebaseAuth,
            onNavigateDelegate: () -> Unit,
        ) {
            val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        // analytics.sendAnalyticLoginGoogle(user!!.email!!)
                        onNavigateDelegate.invoke()
                    } else {
                        // If sign in fails, display a message to the user.
                        // Toast.makeText(this,"ErrorLogin", Toast.LENGTH_LONG).show()
                        Log.d("FIREBASE_LOGIN", "FAILED")
                    }
                }
        }

        fun authWithGoogle(
            data: Intent,
            onResultLogin: () -> Unit,
        ) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(
                    acct = account,
                    auth = auth,
                ) { onResultLogin.invoke() }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.d("GOOGLE_LOGIN", "FAILED")
            }
        }
    }