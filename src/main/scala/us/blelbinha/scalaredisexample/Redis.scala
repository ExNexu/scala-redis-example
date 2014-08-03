package us.blelbinha.scalaredisexample

import akka.util.ByteString
import redis.{ByteStringFormatter, RedisClient}
import us.blelbinha.scalaredisexample.Model.Id

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.pickling._
import scala.pickling.json._

object Redis {
  implicit private val akkaSystem = akka.actor.ActorSystem()
  private val client = RedisClient()
  private val applicationString = "dorloch"

  def save[T <: Model[T] : FastTypeTag : SPickler : Unpickler](obj: T): Future[T] = {
    val objWithId: T = obj.id.fold {
      val newId = java.util.UUID.randomUUID.toString
      obj.withId(newId)
    } {
      _ => obj
    }
    val resultFuture = client.set(s"$applicationString:${objWithId.id}", objWithId)
    resultFuture map (_ => objWithId)
  }

  def get[T <: Model[T] : FastTypeTag : SPickler : Unpickler](id: Id): Future[Option[T]] =
    client.get(s"$applicationString:$id")

  implicit private def genericByteStringFormatter[T <: Model[T] : FastTypeTag : SPickler : Unpickler]: ByteStringFormatter[T] =
    new ByteStringFormatter[T] {
      override def deserialize(bs: ByteString): T = JSONPickle(bs.utf8String).unpickle[T]

      override def serialize(data: T): ByteString = ByteString(data.pickle.value)
    }
}
