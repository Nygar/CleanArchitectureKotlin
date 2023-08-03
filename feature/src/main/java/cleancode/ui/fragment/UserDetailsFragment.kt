package cleancode.ui.fragment

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import cleancode.ui.base.BaseFragmentCompose
import cleancode.ui.base.withArgs
import cleancode.viewmodel.UserDetailsViewModel
import coil.compose.AsyncImage
import com.nygar.feature.databinding.FragmentUserDetailsBinding

/**
 * Fragment that shows details of a certain user.
 */

class UserDetailsFragment : BaseFragmentCompose() {

    companion object {

        private const val USER_DETAILS_KEY:String = "categoryId"

        fun newInstance(userId: Int) = UserDetailsFragment().withArgs {
            putInt(USER_DETAILS_KEY, userId)
        }
    }

    private lateinit var binding: FragmentUserDetailsBinding

    private val viewModel: UserDetailsViewModel by viewModels()

    /*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(USER_DETAILS_KEY)?.let {
            viewModel.getUserById(it).observe(viewLifecycleOwner) { data ->
                GlideApp.with(this).load(data.coverUrl).into(binding.viewUserDetail.ivCover)
                binding.viewUserDetail.tvFullname.text = data.fullName
                binding.viewUserDetail.tvEmail.text = data.email
                binding.viewUserDetail.tvFollowers.text = data.followers.toString()
                binding.viewUserDetail.tvDescription.text = data.description
            }
        }
    }

     */

    @Composable
    override fun UI() {
        ImageFull("https://www.droidcon.com/wp-content/uploads/2021/09/1_OAMmB9eBFEAhF0HsUu6MyQ.png")
    }

    @Preview
    @Composable
    fun PreviewCompose(){
        Text(text = "Esto es una prueba")
        ImageFull("https://www.droidcon.com/wp-content/uploads/2021/09/1_OAMmB9eBFEAhF0HsUu6MyQ.png")
    }


    @Composable
    fun ImageFull(dataUrl: String){
        AsyncImage(model = dataUrl, contentDescription = null)
    }


}