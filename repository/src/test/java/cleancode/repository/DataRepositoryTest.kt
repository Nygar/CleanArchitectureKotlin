package cleancode.repository

import cleancode.entity.CategoryEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DataRepositoryTest {

    @MockK
    lateinit var repository: DataRepositoryImpl

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Before
    fun setUp(){
    }

    @Test
    fun category(){
        val res = ArrayList<CategoryEntity>()
        res.add(CategoryEntity())
        res.add(CategoryEntity())
        res.add(CategoryEntity())
        res.add(CategoryEntity())

        val testObserver = repository.categories().test()
        val values =  testObserver.values().first()
        assertEquals(4,values.size)
    }

    @Test
    fun categoryError(){
        every { repository.categories() }  returns Observable.error(Throwable("ERRORMASIVE"))
        val testObserver = repository.categories().test()
        val values =  testObserver.errors().first().message!!
        assert(values.contains("ERRORMASIVE"))
    }
}