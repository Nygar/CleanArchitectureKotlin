package cleancode

import android.content.Context
import cleancode.net.RestApiImpl
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

import androidx.test.core.app.ApplicationProvider
import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.entity.UserEntity
import cleancode.entity.UserLoggedEntity
import io.reactivex.observers.TestObserver
import org.junit.Assert

class RestApiImplTest {

    //lateinit var mockServer: MockWebServer
    lateinit var context: Context
    lateinit var api: RestApiImpl

    @Before
    fun setUp(){
        context = ApplicationProvider.getApplicationContext<Context>()
        api = RestApiImpl(context)
        //mockServer.start(800)
    }

    @After
    fun tearDown(){
        //mockServer.shutdown()
    }
    @Test
    fun userEntityList() {
        val testSub = TestObserver<List<UserEntity>>()
        api.userEntityList().subscribe(testSub)
        testSub.assertNoErrors()
        testSub.await()
        testSub.assertComplete()
    }

    @Test
    fun userEntityById() {
        val testSub = TestObserver<UserEntity>()
        api.userEntityById(1).subscribe(testSub)
        testSub.assertNoErrors()
        testSub.await()
        testSub.assertComplete()
    }

    @Test
    fun userLoggedEntity() {
        val testSub = TestObserver<UserLoggedEntity>()
        api.userLoggedEntity().subscribe(testSub)
        testSub.assertNoErrors()
        testSub.await()
        testSub.assertComplete()
    }

    @Test
    fun messageEntityList() {
        val testSub = TestObserver<List<MessageEntity>>()
        api.messageEntityList().subscribe(testSub)
        testSub.assertNoErrors()
        testSub.await()
        testSub.assertComplete()
    }

    @Test
    fun messageEntityById() {
        val testSub = TestObserver<MessageEntity>()
        api.messageEntityById(1).subscribe(testSub)
        testSub.assertNoErrors()
        testSub.await()
        testSub.assertComplete()
    }

    @Test
    fun categoryEntityList() {
        val testSub = TestObserver<List<CategoryEntity>>()
        api.categoryEntityList().subscribe(testSub)
        testSub.assertNoErrors()
        testSub.await()
        testSub.assertComplete()
    }

    @Test
    fun categoryEntityById() {
        val testSub = TestObserver<CategoryEntity>()
        api.categoryEntityById(1).subscribe(testSub)
        testSub.assertNoErrors()
        testSub.await()
        testSub.assertComplete()
    }
}