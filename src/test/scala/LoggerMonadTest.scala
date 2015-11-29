import com.artezio.novikova.ludmila.LoggerMonad
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
 * Created by dvlar on 29.11.2015.
 */
@RunWith(classOf[JUnitRunner])
class LoggerMonadTest extends FunSuite{
  test("Log debug"){
    LoggerMonad.debug("debug message")
  }
  test("Log info"){
    LoggerMonad.info("info message")
  }
  test("Log warning"){
    LoggerMonad.warn("warning message")
  }
  test("Log error"){
    LoggerMonad.error("error message")
  }
}
