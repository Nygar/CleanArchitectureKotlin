package cleancode.viewmodel

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nygar.common.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        @ApplicationContext val context: Context,
    ) : ViewModel() {
        var isLogingFinished by mutableStateOf(false)
        var isAnimationComplete by mutableStateOf(false)
        val isAllowLoginNavigate by derivedStateOf { isAnimationComplete && isLogingFinished }
        var isShowingAnimation by mutableStateOf(false)

        private val googleIdOption: GetGoogleIdOption =
            GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(BuildConfig.GOOGLE_TOKEN)
                .build()

        private fun firebaseAuthWithGoogle(acct: GoogleIdTokenCredential) {
            val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
            val auth = Firebase.auth
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        // analytics.sendAnalyticLoginGoogle(user!!.email!!)
                        isLogingFinished = true
                    } else {
                        // If sign in fails, display a message to the user.
                        // Toast.makeText(this,"ErrorLogin", Toast.LENGTH_LONG).show()
                        Log.d("FIREBASE_LOGIN", "FAILED")
                    }
                }
        }

        fun authWithGoogle(addAccount: (() -> Unit)? = null) {
            val request: GetCredentialRequest =
                GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()
            val credentialManager = CredentialManager.create(context)

            viewModelScope.launch {
                try {
                    val result =
                        credentialManager.getCredential(
                            request = request,
                            context = context,
                        )
                    handleSignIn(result)
                } catch (e: GetCredentialException) {
                    Log.d("CREDENTIAL_LOGIN", "FAILED")
                    addAccount?.invoke()
                }
            }
        }

        private fun handleSignIn(result: GetCredentialResponse) {
            // Handle the successfully returned credential.
            when (val credential = result.credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        try {
                            // Use googleIdTokenCredential and extract id to validate and
                            // authenticate on your server.
                            val googleIdTokenCredential =
                                GoogleIdTokenCredential
                                    .createFrom(credential.data)

                            firebaseAuthWithGoogle(googleIdTokenCredential)
                        } catch (e: GoogleIdTokenParsingException) {
                            Log.d("GOOGLE_LOGIN", "FAILED")
                        }
                    }
                }

                else -> {
                    // Catch any unrecognized credential type here.
                    Log.d("GOOGLE_LOGIN", "FAILED")
                }
            }
        }

        fun getAddGoogleAccountIntent(): Intent {
            val intent = Intent(Settings.ACTION_ADD_ACCOUNT)
            intent.putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
            return intent
        }
    }
