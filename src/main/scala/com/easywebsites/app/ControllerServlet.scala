package com.easywebsites.app

import org.scalatra._
import com.mongodb.casbah.Imports._

class ControllerServlet extends EasywebsitesappStack {

  val model = new ModelController()

  get("/") {
    contentType="text/html"
    ssp("home", "name" -> model.getUser())
  }

}
