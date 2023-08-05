package cleancode.ui.view.activity

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.gms.auth.api.R

class LoginActivityView {
}

interface LoginActivityDelegate{
    fun normalLoginAction()
    fun googleLoginAction()
}

@Composable
fun LoginActivityCompose(
    delegate: LoginActivityDelegate
) {
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
            GoogleBtn(Modifier, delegate)
            NormalLoginBtn(Modifier, delegate)
        }
    }
}

//@Preview
@Composable
fun GoogleBtn(modifier: Modifier, delegate: LoginActivityDelegate){
    Button(
        onClick = {
            delegate.googleLoginAction()
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
            painter = painterResource(id = R.drawable.googleg_standard_color_18),
            contentDescription = null
        )
        Text(text = "Sign out with Google", modifier = Modifier.padding(6.dp))
    }
}
//@Preview
@Composable
fun NormalLoginBtn(modifier: Modifier, delegate: LoginActivityDelegate){
    Button(
        onClick = {
            delegate.normalLoginAction()
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
    var usernameTextState by rememberSaveable { mutableStateOf("") }
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
    var passTextState by rememberSaveable { mutableStateOf("") }
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