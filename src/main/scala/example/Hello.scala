package main.scala.example

import rx.lang.scala.Observable

object Hello {
  def hello(names: String*) {
    Observable.from(names) subscribe { n =>
      println(s"Hello $n!")
    }
  }

  def main(args: Array[String]): Unit = {
    hello("jenny")
  }
}