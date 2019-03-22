package cuongduong.developer.android.stackoverflow.internal

import kotlinx.coroutines.*

//it's going to take in a generic type: fun <T>
// take in block of code, will be some kind of an embed suspending function
// which is execute on coroutine scope CoroutineScope.()
// block of code will return T -> higher order suspending function

// specify the return type of whole lazyDefferred -> that will be a Lazy. It holds inside of it <Deferred>, and
// Deferred hold type T

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy { // return Lazy to reuse
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this) // block of code is invoke with 'this' is coroutine scope
        }
    }
}

// don't want reinvent
// lazy {
//        stackExchangeRepository.getUserList()
// }

/* GlobalScope
* A global [CoroutineScope] not bound to any job.
*
* Global scope is used to launch top-level coroutines which are operating on the whole application lifetime
* and are not cancelled prematurely.*/

/* GlobalScope.async
* Creates new coroutine and returns its future result as an implementation of [Deferred].
* The running coroutine is cancelled when the resulting deferred is [cancelled][Job.cancel].*/

/*** CoroutineScope
* Defines a scope for new coroutines. Every coroutine builder
* is an extension on [CoroutineScope] and inherits its [coroutineContext][CoroutineScope.coroutineContext]
* to automatically propagate both context elements and cancellation.*/

/**
 * Deferred value is a non-blocking cancellable future &mdash; it is a [Job] that has a result.
 *
 * It is created with [async][CoroutineScope.async] coroutine builder or via constructor of [CompletableDeferred] class.
 * It is in [active][isActive] state while the value is being computed.*/

/** CoroutineScope.async
 * Creates new coroutine and returns its future result as an implementation of [Deferred].
 * The running coroutine is cancelled when the resulting deferred is [cancelled][Job.cancel]*/

/*
* A `Job` instance in the
* [coroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/coroutine-context.html)
* represents the coroutine itself.*/

/** CoroutineScope.launch()
 * Launches new coroutine without blocking current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].*/
