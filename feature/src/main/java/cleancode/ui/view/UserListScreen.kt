package cleancode.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cleancode.model.CategoryModel
import cleancode.model.UserModel
import com.nygar.common.Constants
import com.nygar.designsystem.components.CharacterRow
import com.nygar.designsystem.components.skeleton.SkeletonGridRow
import com.nygar.designsystem.theme.ThemeConfig

@Composable
fun UserListScreen() {
    val userList: List<UserModel> = arrayListOf()

    LazyColumn {
        if(userList.isNullOrEmpty()) {
            items(Constants.PAGE_SIZE) {
                SkeletonGridRow(
                    modifier = Modifier.padding(all = ThemeConfig.theme.spacing.sizeSpacing8),
                )
            }
        }else {
                items(userList.size){index ->
                    userList[index]?.let { user ->
                        CharacterRow(
                            modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                            characterName = user.fullName,
                            characterAvatar = user.coverUrl,
                            numComics = user.userId,
                            onViewCLickListener = {
                                //onCharacterItemClick(character.id)
                            }
                        )
                    }
                }
            }
        }
    }