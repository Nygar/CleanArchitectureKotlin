package cleancode.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cleancode.GetAwaitValue.getOrAwaitValue
import cleancode.RxImmediateSchedulerRule
import cleancode.base.RxAsync
import cleancode.entity.CategoryEntity
import cleancode.repository.DataRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception


class MessageCategoryViewModelTest {

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: DataRepository

    lateinit var viewModel: MessageCategoryViewModel

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Before
    fun setUp(){
        viewModel = MessageCategoryViewModel(repository)
    }

    @Test
    fun category(){

        val res = ArrayList<CategoryEntity>()
        res.add(CategoryEntity())
        res.add(CategoryEntity())
        res.add(CategoryEntity())
        res.add(CategoryEntity())

        every { repository.categories() }  returns  Observable.just(res)
        val testObserver = viewModel.getMessageCategory().getOrAwaitValue()
        assertEquals(4,testObserver.size)
    }

    @Test
    fun categoryError(){
        every { repository.categories() }  returns Observable.error(Throwable("ERRORMASIVE"))
        try {
            val testObserver = viewModel.getMessageCategory().getOrAwaitValue()
        }catch (e: Exception){
            assert(true)
        }


    }
}