# Moovel Coding Challenge

<img src="https://i.imgur.com/ehNHaQ8.jpg" width=250>    <img src="https://i.imgur.com/yVl2zXf.jpg" width=250>


___
## Why Kotlin?
I chose __Kotlin__ because it is more concise and pragmatic than Java, in fact I wrote this app in a very smooth way, besides, extensions functions allowed to me to encapsulate recyclerViews setup encouraging code reuse in other projects. 

## Features

Into directory __extensions__ for example is located all my kotlin extension functions. In this case i decided to use two functions witch extends RecyclerView class:

* __onScrollToEnd__ : for this project i want to launch a lambda every time I am close to the end of the list.

* __recyclerview adapters using lambdas__ : I preferred to use kotlin because using Java, if I wanted to make an item tappable I should have add an interface onClick inside my adapter, with Kotlin instead I simply pass a lambda.

___
## Libraries
I used several libraries both for network and async stuff:

### Network and Async
For the network I could choose to use __Loaders__ or __jobScheduler__, at the end I chose technology I wanted to go deep more: [RxJava](https://github.com/ReactiveX/RxJava).

I already had experience with [Retrofit](http://square.github.io/retrofit/) so first requirements was to bind these two libraries and to do this I used retrofit's [RxJava Adapters](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava).

As we know __RxJava__ isn't asynchronous by default so I wanted to observe my task on a main thread as I would have done using a Loader, so to use RxJava on android on its best I used [RxAndroid](https://github.com/ReactiveX/RxAndroid) so I could observe on the main thread

### Picasso
For image loading I used latest version [Picasso](http://square.github.io/picasso/) Provides a simple loading images and relative caching. Other good alternative was __Glide__ but for now I'm more confident using Picasso.

## Bugs
* Sometims, while testing on physical device sometimes I recieve a forbidden error when I scroll down.
___
## Mentioned Libraries:
* Retrofit: http://square.github.io/retrofit/
* RxJava: https://github.com/ReactiveX/RxJava
* RxJava Adapters: https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava
* RxAndroid: https://github.com/ReactiveX/RxAndroid
* Picasso: http://square.github.io/picasso/