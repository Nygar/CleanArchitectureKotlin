package cleancode.ui.fragment

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import cleancode.model.UserModel
import cleancode.ui.view.ImageFull
import cleancode.ui.view.UserDetailsGenericField
import cleancode.viewmodel.UserDetailsViewModel
import com.nygar.designsystem.R

/**
 * Fragment that shows details of a certain user.
 */
class UserDetailsFragment {

    companion object {

        private const val USER_DETAILS_KEY:String = "categoryId"

        /*
        fun newInstance(userId: Int) = UserDetailsFragment().withArgs {
            putInt(USER_DETAILS_KEY, userId)
        }

         */
    }

    private val viewModel: UserDetailsViewModel?  = null

    @Preview(showBackground = true)
    @Composable
    fun UI() {
        //val user by viewModel.userSingle ?: null

        /*
        DisposableEffect(Unit){

            arguments?.getInt(USER_DETAILS_KEY)?.let {
                viewModel.getUserById(it)
            }
            onDispose {  }


        }

         */

        //PaintedUi(user = user)
    }

    @Composable
    fun PaintedUi(user: UserModel){
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ){
            val (ivCover, tvFullname, tvEmail, tvFollowers, tvDescription) = createRefs()

            ImageFull(user.coverUrl,
                Modifier.constrainAs(ivCover){
                    top.linkTo(parent.top)
                })
            Text(text = user.fullName ,
                Modifier
                    .absolutePadding(15.dp, 0.dp, 15.dp, 0.dp)
                    .constrainAs(tvFullname) {
                        top.linkTo(ivCover.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            UserDetailsGenericField(
                stringResource(id = R.string.view_text_email),
                user.email,
                Modifier
                    .absolutePadding(15.dp, 0.dp, 15.dp, 0.dp)
                    .constrainAs(tvEmail) {
                        top.linkTo(tvFullname.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                    })
            UserDetailsGenericField(
                stringResource(id = R.string.view_text_followers),
                user.followers.toString(),
                Modifier
                    .absolutePadding(15.dp, 0.dp, 15.dp, 0.dp)
                    .constrainAs(tvFollowers) {
                        top.linkTo(tvEmail.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                    })
            UserDetailsGenericField(
                stringResource(id = R.string.view_text_description),
                user.description,
                Modifier
                    .absolutePadding(15.dp, 0.dp, 15.dp, 0.dp)
                    .constrainAs(tvDescription) {
                        top.linkTo(tvFollowers.bottom, margin = 5.dp)
                    })
        }
    }
}
