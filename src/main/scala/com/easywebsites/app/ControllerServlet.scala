package com.easywebsites.app

import org.scalatra._
import com.mongodb.casbah.Imports._

class ControllerServlet extends EasywebsitesappStack {

  var is_connected = false
    val modelUser = new ModelControllerUser()

    get("/") {
      contentType="text/html"
        ssp("home")
    }

  get("/newUser"){
    contentType="text/html"

      if (is_connected == false)
        ssp("/newUser")
      else
        ssp("/newUser", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp")
  }

  get("/connection"){
    contentType="text/html"
      ssp("/connection")
  }

  post("/connection"){
    //contentType="text/html"


    is_connected = true


      <p>${params.get("email")}</p>
      <p>${params.get("password")}</p>
      <p>${is_connected}</p>
  }

  post("/registration")
  {
    if (modelUser.userExist(params("email"))) {
      contentType="text/html"
        ssp("/newUser", "Used" -> true)
    } else {
      modelUser.insertUser(params("email"), params("password"), params("firstname"), params("lastname"))
        is_connected = true
        contentType="text/html"
        ssp("/home", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp")
    }  
  }

  post("/connection")
  {
    if (modelUser.matchUser(params("email"), params("password"))) {
        contentType="text/html"
        ssp("/home", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp")
    } else {
        contentType="text/html"
        ssp("/connection", "Unmatch" -> true)
    }
  }

}
