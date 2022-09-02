package Examples.CoreMovement

import SEBAS.core._

class Position(x:Float,y:Float) extends Situation

trait MovableEffect extends Effect
trait MovableAction extends Action[MovableEffect,PhysicalWorld]
class Movable extends Agent[MovableAction,MovableEffect,Position]

class PhysicalWorld extends Medium[Movable,MovableEffect]