package cleancode.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cleancode.viewmodel.UserListViewModel
import com.nygar.common.Constants
import com.nygar.designsystem.components.GenericRow
import com.nygar.designsystem.components.TypeRow
import com.nygar.designsystem.components.skeleton.SkeletonRow
import com.nygar.designsystem.theme.ThemeConfig

@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel(),
    onNavigateToUserDetails: (Int) -> Unit
) {
    val userList = viewModel.userList

    LazyColumn {
        if(userList.isEmpty()) {
            items(Constants.PAGE_SIZE) {
                SkeletonRow(
                    modifier = Modifier.padding(all = ThemeConfig.theme.spacing.sizeSpacing8),
                )
            }
        }else {
                items(userList.size){index ->
                    userList[index].let { user ->
                        GenericRow(
                            modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                            id = user.userId,
                            title = user.fullName,
                            image = user.coverUrl,
                            type = TypeRow.UserRow,
                            onViewCLickListener = {
                                onNavigateToUserDetails(it)
                            }
                        )
                    }
                }
            }
        }
    }