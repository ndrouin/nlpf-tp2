package com.easywebsites.app

import org.scalatra._
import com.mongodb.casbah.Imports._

class ControllerServlet extends EasywebsitesappStack {

  val model = new ModelController()

  get("/") {
    contentType="text/html"
    ssp("home", "name" -> model.getUser())
  }

  get("/newUser"){
  contentType="text/html"
  ssp("/newUser")
  }

  get("/connection"){
  contentType="text/html"
  ssp("/connection")
  }

  post("/connection"){
  contentType="text/html"

 
 /* email := params.get("email")
  password := params.get("password")
  //get result of authentification
  result := model.Connection(email, password)
  if result {
    ctx.Session().Set("isConnected", "true")
    ctx.Redirect("/connect/")
  } else {
    ctx.Render("connection_error.html", nil)
  }*/

  ssp("/connection")
  }

}
