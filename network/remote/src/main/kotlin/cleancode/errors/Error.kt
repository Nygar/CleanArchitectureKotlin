package cleancode.errors

sealed class Error : Throwable() {
    data class NetworkError(override val message: String = "", val code: Int = 0) : Error()
}
