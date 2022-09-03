package Examples.CoreMovement

import SEBAS.core._
import scala.collection.mutable.Queue

case class Position(x:Float,y:Float) extends Situation{
    def +(o:Position):Position = new Position(x+o.x,y+o.y)
}

sealed trait MovableEffect extends Effect
case class Translation(dx:Float,dy:Float) extends MovableEffect

trait MovableAction extends Action[MovableEffect,PhysicalWorld]
case class Move(target:Movable,delta:Translation) extends MovableAction{
    def happen(m: Set[PhysicalWorld]): Unit = target.be_affected(delta)
}

class Movable(p:Position) extends Agent[MovableAction,MovableEffect,Position]{
    var situation:Position = p
    def be_affected(e: MovableEffect): Agent[MovableAction,MovableEffect,Position] = e match {
        case Translation(dx,dy) => {
            this.situation = this.situation + new Position(dx,dy)
            this
        }
    }
    def decide(): MovableAction = new Move(this,new Translation(1,1))
}

class PhysicalWorld extends Medium[Movable,MovableEffect]

class SequentialTimeline(world:PhysicalWorld) extends Plan[MovableAction,PhysicalWorld]{
    var timeline:Queue[MovableAction] = new Queue[MovableAction]
    def schedule(a: MovableAction): Plan[MovableAction,PhysicalWorld] = {
        timeline.enqueue(a)
        this
    }

    def resolve(): Plan[MovableAction,PhysicalWorld] = {
        if(!timeline.isEmpty)
            timeline.dequeue().happen(Set(world))
        timeline.enqueueAll(world.agents.map(a=>a.decide()))
        this
    }
}