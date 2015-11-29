package com.artezio.novikova.ludmila

/**
 * Created by dvlar on 23.11.2015.
 */

object LoggerMonad {
  trait Log {

    def >>=(f: String => String): Log = this match {
      case DebugLog(log:String) => DebugLog(f("DEBUG: " + log))
      case InfoLog(log:String) => InfoLog(f("INFO: " + log))
      case WarnLog(log:String) => WarnLog(f("WARN: " + log))
      case ErrorLog(log:String) => ErrorLog(f("ERROR: " + log))
    }

    def printLogMessage = {
      >>=((log: String) => {println(log); log})
    }

  }

  case class DebugLog(log:String) extends Log
  case class InfoLog(log:String) extends Log
  case class WarnLog(log:String) extends Log
  case class ErrorLog(log:String) extends Log

  def debug(log: String) = DebugLog(log).printLogMessage
  def info(log: String) = InfoLog(log).printLogMessage
  def warn(log: String) = WarnLog(log).printLogMessage
  def error(log: String) = ErrorLog(log).printLogMessage

}
