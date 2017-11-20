package main.scala.example

import rx.lang.scala.Observable

object CreateFromSource {

  def create() {
    val o1 = Observable.just("a", "b", "c")

    def list = List(5, 6, 7, 8)
    val o2 = Observable.from(list)

    val o3 = Observable.just("one object")

    println("o1")
    o1.subscribe(println(_))
    println("o2")
    o2.subscribe(println(_))
    println("o3")
    o3.subscribe(println(_))
  }

  def main(args: Array[String]): Unit = {
    create()
  }
}