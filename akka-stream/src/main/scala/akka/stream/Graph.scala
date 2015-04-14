/**
 * Copyright (C) 2015 Typesafe Inc. <http://www.typesafe.com>
 */
package akka.stream

import akka.stream.impl.StreamLayout
import scala.annotation.unchecked.uncheckedVariance
import akka.stream.scaladsl.OperationAttributes

trait Graph[+S <: Shape, +M] {
  /**
   * Type-level accessor for the shape parameter of this graph.
   */
  type Shape = S @uncheckedVariance
  /**
   * The shape of a graph is all that is externally visible: its inlets and outlets.
   */
  def shape: S
  /**
   * INTERNAL API.
   *
   * Every materializable element must be backed by a stream layout module
   */
  private[stream] def module: StreamLayout.Module

  def withAttributes(attr: OperationAttributes): Graph[S, M]

  def named(name: String): Graph[S, M] = withAttributes(OperationAttributes.name(name))
}
