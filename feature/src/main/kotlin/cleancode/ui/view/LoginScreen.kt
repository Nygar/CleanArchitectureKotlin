package cleancode.ui.view

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.compose.ConstraintLayout
import com.nygar.designsystem.theme.ThemeConfig

interface LoginActivityDelegate {
    fun normalLoginAction()

    fun googleLoginAction()
}

@Composable
fun LoginScreen(delegate: LoginActivityDelegate) {
    ConstraintLayout(
        modifier =
            Modifier
                .fillMaxSize()
                .absolutePadding(
                    ThemeConfig.theme.spacing.sizeSpacing4,
                    ThemeConfig.theme.spacing.sizeSpacing4,
                    ThemeConfig.theme.spacing.sizeSpacing4,
                    ThemeConfig.theme.spacing.sizeSpacing4,
                ),
    ) {
        val (username, password, contentLogin) = createRefs()

        LoginUserField(
            Modifier.constrainAs(username) {
                top.linkTo(parent.top, margin = ThemeConfig.theme.spacing.sizeSpacing16)
            },
        )
        LoginPassField(
            Modifier.constrainAs(password) {
                top.linkTo(username.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing16)
            },
        )
        Column(
            Modifier.constrainAs(contentLogin) {
                top.linkTo(password.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing16)
                bottom.linkTo(parent.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing16)
            },
        ) {
            GoogleBtn(Modifier, delegate)

            NormalLoginBtn(
                modifier =
                    Modifier.padding(
                        top = ThemeConfig.theme.spacing.sizeSpacing16,
                    ),
                delegate,
            )
        }
    }
}

@Composable
fun GoogleBtn(
    modifier: Modifier,
    delegate: LoginActivityDelegate,
) {
    Button(
        onClick = {
            delegate.googleLoginAction()
        },
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing16),
        shape = RoundedCornerShape(ThemeConfig.theme.roundBorder.roundBorder6),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
            ),
    ) {
        Image(
            painter = painterResource(id = com.google.firebase.appcheck.interop.R.drawable.googleg_standard_color_18),
            contentDescription = null,
        )
        Text(text = "Sign out with Google", modifier = Modifier.padding(ThemeConfig.theme.spacing.sizeSpacing8))
    }
}

@Composable
fun NormalLoginBtn(
    modifier: Modifier,
    delegate: LoginActivityDelegate,
) {
    Button(
        onClick = {
            delegate.normalLoginAction()
        },
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing16),
        shape = RoundedCornerShape(ThemeConfig.theme.roundBorder.roundBorder6),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
            ),
    ) {
        Text(text = "Login", modifier = Modifier.padding(ThemeConfig.theme.spacing.sizeSpacing8))
    }
}

@Composable
fun LoginUserField(modifier: Modifier) {
    var usernameTextState by rememberSaveable { mutableStateOf("") }
    TextField(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing16),
        value = usernameTextState,
        onValueChange = { usernameTextState = it },
        label = { Text(text = "Username") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
    )
}

@Composable
fun LoginPassField(modifier: Modifier) {
    var passTextState by rememberSaveable { mutableStateOf("") }
    TextField(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing16),
        value = passTextState,
        onValueChange = { passTextState = it },
        label = { Text(text = "Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
    )
}
