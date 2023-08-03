package cleancode.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import cleancode.ui.base.BaseActivityCompose
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.nygar.feature.BuildConfig
import dagger.hilt.android.AndroidEntryPoint


/**
 * Main application screen. This is the app entry point.
 */
@AndroidEntryPoint
class LoginActivity: BaseActivityCompose() {
    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    @Preview(showBackground = true)
    @Composable
    override fun UI() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(5.dp, 5.dp, 5.dp, 5.dp)
        ){
            val (username, password, contentLogin) = createRefs()

            LoginUserField(Modifier.constrainAs(username){
                top.linkTo(parent.top, margin = 16.dp)
            })
            LoginPassField(Modifier.constrainAs(password){
                top.linkTo(username.bottom, margin = 16.dp)
            })
            Column(
                Modifier.constrainAs(contentLogin){
                    top.linkTo(password.bottom, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }) {
                GoogleBtn(Modifier)
                NormalLoginBtn(Modifier)
            }
        }
    }

    //@Preview
    @Composable
    fun GoogleBtn(modifier: Modifier){
        Button(
            onClick = {
                val signInIntent = mGoogleSignInClient.signInIntent
                resultLogIn.launch(signInIntent)
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Image(
                painter = painterResource(id = com.google.android.gms.auth.api.R.drawable.googleg_standard_color_18),
                contentDescription = ""
            )
            Text(text = "Sign out with Google", modifier = Modifier.padding(6.dp))
        }
    }
    //@Preview
    @Composable
    fun NormalLoginBtn(modifier: Modifier){
        Button(
            onClick = {
                navigateToMessageList()
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(text = "Login", modifier = Modifier.padding(6.dp))
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginUserField(modifier: Modifier){
        var usernameTextState by remember { mutableStateOf("") }
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            value = usernameTextState,
            onValueChange = { usernameTextState = it },
            label = { Text(text = "Username") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginPassField(modifier: Modifier){
        var passTextState by remember { mutableStateOf("") }
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            value = passTextState,
            onValueChange = { passTextState = it },
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
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
    private fun navigateToMessageList() {
        navigator.navigateToMain(this)
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
                    analytics.sendAnalyticLoginGoogle(user!!.email!!)
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