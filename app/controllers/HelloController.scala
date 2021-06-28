package controllers

import javax.inject.Inject
import play.api.mvc._

class HelloController @Inject() (val controllerComponents: ControllerComponents) extends BaseController {
  def fetchAll(): Action[AnyContent] = Action {
    Ok
  }
}
