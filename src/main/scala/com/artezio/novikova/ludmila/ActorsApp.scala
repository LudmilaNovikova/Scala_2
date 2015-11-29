package com.artezio.novikova.ludmila

import akka.actor.{ActorLogging, Actor}

import akka.actor._
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.duration._
/**
 * Created by dvlar on 23.11.2015.
 */
object ActorsApp extends App {
  LoggerMonad.info("Start actors example")

  object MyActorActions extends Enumeration {
    type MyActorActions = Value
    val Ping = Value("Ping")
    val Pong = Value("Pong")
  }

  trait PingPongAction {
    val action: MyActorActions.Value
  }

  class PongActor extends Actor {
    this: PongActor with PingPongAction =>
    def receive = {
      case ping =>
        LoggerMonad.info("Pong")
        sender ! action
      case r =>
        LoggerMonad.error(s"Unexpected: $r")
    }
  }

  class PingActor extends Actor {
    this: PingActor with PingPongAction =>
    var pingCount = 0
    val pingCountLimit = 5
    implicit val timeout = Timeout(5 seconds)
    val pongActor = context.actorOf(Props(new PongComponent()), "pongActor")

    override def preStart() {
      LoggerMonad.info(s"Ping $pingCount")
      pongActor ! action
    }

    def receive = {
      case pong =>
        if(pingCount < pingCountLimit){
          pingCount = pingCount + 1
          LoggerMonad.info(s"Ping $pingCount")
          sender ! action
        }
        else{
          LoggerMonad.info("Finish ping pong application")
          context.system.shutdown()
        }
      case r =>
        LoggerMonad.error(s"Unexpected: $r")
        context.system.shutdown()
    }
  }

  class PingComponent
    extends PingActor
    with PingPongAction {
    val action = MyActorActions.Ping
  }

  class PongComponent
    extends PongActor
    with PingPongAction {
    val action = MyActorActions.Pong
  }

  val system = ActorSystem("system")
  val pingActor = system.actorOf(Props(new PingComponent()), "pingActor")
  system.awaitTermination()
}


