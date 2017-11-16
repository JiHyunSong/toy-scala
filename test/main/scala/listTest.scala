package main.scala

import org.scalatest.FunSuite

class listTest extends FunSuite {
  test("간단한 리스트 사용법을 살펴보자") {
    val oneTwo = List(1, 2)
    val threeFour = List(3, 4)
    val oneTwoThreeFour = List(1, 2, 3, 4)

    // 간단한 리스트 관련 함수
    assert(oneTwoThreeFour.isEmpty == false)
    assert(oneTwoThreeFour.length == 4)
    assert(oneTwoThreeFour.head == 1) // O(1)
    assert(oneTwoThreeFour.last == 4) // O(n)
    assert(oneTwoThreeFour.tail.head == 2) // O(1)
    assert(oneTwoThreeFour.tail == 2 :: 3 :: 4 :: Nil) // O(1)
    assert(oneTwoThreeFour.init == List.range(1, 4))
    assert(oneTwoThreeFour.reverse == List.apply(4, 3, 2, 1))

    // 리스트 접합
    assert(oneTwo ::: threeFour == oneTwoThreeFour)
    assert(threeFour ::: oneTwo != oneTwoThreeFour)

    // 원소 접합, ::는 콘즈(cons) 라고 부른다
    assert(1 :: 2 :: threeFour == oneTwoThreeFour)

    // 빈 리스트는?
    assert(Nil == List())

    def append[T](xs: List[T], ys: List[T]): List[T] =
      xs match {
        case List() => ys
        case x :: xs1 => x :: append(xs1, ys)
      }

    // pattern match 를 통한 리스트 접합 구현
    assert(oneTwo ::: threeFour == append(oneTwo, threeFour))

    def rev[T](xs: List[T]): List[T] = xs match {
      case List() => xs
      case x :: xs1 => rev(xs1) ::: List(x)
    } // :::이 O(n)의 시간이 걸리므로 전체는 O(n^2, ((1+n) * n / 2))

    assert(oneTwo.reverse == rev(oneTwo))
  }

  test("List 의 다양한 기능들을 살펴보자") {
    // list 생성에 관한 이슈 376p
    val abcde = List("a", "b", "c", "d", "e")
    // 359p ~ 363p 참고하여 정리중
    // drop, take, splitAt
    assert((abcde take 2) == List("a", "b"))
    assert((abcde drop 2) == List("c", "d", "e"))
    assert((abcde splitAt 2) == (List("a", "b"), List("c", "d", "e")))

    // apply, indices
    assert((abcde apply 2) == abcde(2))
    assert((abcde apply 2) == ((abcde drop 2).head))

    assert(abcde.indices == Range(0, 5))

    // flatten, 리스트의 리스트를 인자로 받아서 하나의 리스트로 만든다.
    assert(List(List(1, 2), List(), List(3)).flatten == List(1, 2, 3))

    // zip 두 리스트를 인자로 받아서 순서쌍의 리스트를 만든다, unzip 16.9절 참고
    assert((abcde zip List(1, 2, 3)) == List(("a", 1), ("b", 2), ("c", 3)))

    // toString, mkString
    // mkString와 addString은 슈퍼트레이트인 Traversalble로 부터 상속한 메소드로 모든 컬렉션에서 사용 가능
    assert((abcde mkString("[", ",", "]")) == "[a,b,c,d,e]")

    val buf = new StringBuilder
    assert((abcde addString(buf, "[", ",", "]")).toString() == "[a,b,c,d,e]")

    // iterator, toArray, copyToArray
  }

  test("List 의 고차 메소드 (higher-order operator)") {
    // 366p ~ 374p
    // map : 리스트와 T => U 타입인 함수 f를 받는다.
    assert((List(1, 2, 3) map (_ + 1)) == List(2, 3, 4))
    val words = List("the", "quick", "brown", "fox")
    assert((words map (_.length)) == List(3, 5, 5, 3))
    assert((words map (_.toList.reverse.mkString)) == List("eht", "kciuq", "nworb", "xof"))
    // flatmap : map과 유사하되 함수 f를 적용해서 나온 모든 리스트를 연결한 단일 리스트를 반환한다.
    assert((words flatMap (_.toList.reverse)) == List('e', 'h', 't', 'k', 'c', 'i', 'u', 'q', 'n', 'w', 'o', 'r', 'b', 'x', 'o', 'f'))

    assert((List.range(1, 5) flatMap (
      i => List.range(1, i) map (j => (i, j))
      )) == List((2, 1), (3, 1), (3, 2), (4, 1), (4, 2), (4, 3)))
    // (for (i <- List.range(1, 5); j <- List.range(1, i) yield (i, j))) // 이렇게도 표현이 가능하다.


    // foreach,
//    assert( (List.range(1,5) foreach( sum += _)) == 15)
    //
    //
    // filter, partition, find, takeWhile, dropWhile, span, forall, exists, /:, :\, sortWith


  }
}
