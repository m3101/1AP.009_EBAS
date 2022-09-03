import Examples.CoreMovement._
object Main extends App {
  var world = new PhysicalWorld()
  var timeline = new SequentialTimeline(world)
  world.agents = world.agents+new Movable(new Position(0,0))

  timeline.resolve()
  println(world.agents.map(m=>m.situation))
  timeline.resolve()
  println(world.agents.map(m=>m.situation))
  world.agents = world.agents+new Movable(new Position(0,0))
  timeline.resolve()
  println(world.agents.map(m=>m.situation))
  world.agents = world.agents+new Movable(new Position(0,0))
  timeline.resolve()
  println(world.agents.map(m=>m.situation))
  timeline.resolve()
  println(world.agents.map(m=>m.situation))
  timeline.resolve()
  println(world.agents.map(m=>m.situation))
}