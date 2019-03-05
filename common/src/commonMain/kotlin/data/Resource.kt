package data

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class NetworkError<out T>(val throwable: Throwable = Throwable()) : Resource<T>()
    data class DatabaseError<out T>(val throwable: Throwable = Throwable()) : Resource<T>()
    data class Failure<out T>(
        val throwable: Throwable = Throwable(),
        val message: String = "Something unexpected happen"
    ) : Resource<T>()
}