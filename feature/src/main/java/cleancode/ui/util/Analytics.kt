package cleancode.ui.util

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class Analytics constructor(private val firebaseAnalytics: FirebaseAnalytics) {

    fun sendAnalyticViewScreen(screenName:String ){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, screenName)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle)
    }

    fun sendAnalyticLoginGoogle(userEmail:String){
        val bundle = Bundle( )
        bundle.putString(FirebaseAnalytics.Param.METHOD, "Google")
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, userEmail)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle)
    }
}