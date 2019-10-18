# GitHub Users

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

## Tests
I'm not very able to implementing tests (for now), I've never tested rxJava and Retrofit (always for now), however this project was a great starting point for me to learn a couple of things about Tests on Android.

### Unit Tests
In this app there's a little business logic, I used __JUnit__ to test a pair functionalities.

I mocked my request using [Mockito](http://site.mockito.org/) to simulate requests to server and __TestObserver__ to simulate observers.

### UI Tests
Unfortunately in this app I was not able to write a concise and useful UI test, but if I had  decided to implement tests i would have use these libraries:

* __Mockito__ to use mocked data, through a stub that simulates the http call
* __Espresso__ for UI Tests,
* [Roboelectric](http://robolectric.org/) to allow me to test the app without start my device app without starting my device whether it's an emulator or a physical device
___

## Bugs
* Sometims, while testing on physical device sometimes I recieve a forbidden error when I scroll down. I suppose there is a limit for Github if i call services without an api key
___
## Mentioned Libraries:
* Retrofit: http://square.github.io/retrofit/
* RxJava: https://github.com/ReactiveX/RxJava
* RxJava Adapters: https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava
* Roboelectric: http://robolectric.org/
* Mockito: http://site.mockito.org/
* RxAndroid: https://github.com/ReactiveX/RxAndroid
* Picasso: http://square.github.io/picasso/
