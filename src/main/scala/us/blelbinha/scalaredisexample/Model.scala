package us.blelbinha.scalaredisexample

import us.blelbinha.scalaredisexample.Model.Id

trait Model[T] {
  def id: Option[Id]
  def withId(id: Id): T
}

object Model {
  type Id = String
}
