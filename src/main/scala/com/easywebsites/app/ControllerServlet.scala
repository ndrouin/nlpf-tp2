package com.easywebsites.app

import org.scalatra._
import com.mongodb.casbah.Imports._

class ControllerServlet extends EasywebsitesappStack {

  var is_connected = false
  val modelUser = new ModelControllerUser()
  val modelProject = new ModelControllerProject()

  get("/") {
    contentType="text/html"
      ssp("/home")
  }

  get("/newUser"){
    contentType="text/html"

      if (is_connected == false)
        ssp("/newUser")
      else
        ssp("/newUser", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp")
  }

  get("/connect/newProject"){
    contentType="text/html"

    val add = false
    val delete = false
    //val conterparts = model.getCounterparts()

    if (is_connected == true)
      ssp("/newProject", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp")
    else
      ssp("/home")

  }
    
  post("/connect/addProject"){
    contentType="text/html"
    modelProject.addProject(params("project_name"), params("description"), params("author_name"), params("email"))
    contentType="text/html"
      ssp("/home", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp", "Add" -> true)
  }


  get("/connection"){
    contentType="text/html"
      ssp("/connection")
  }

  get("/connect/deconnection"){
    is_connected = false
      contentType="text/html"
      ssp("/home")
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
      is_connected = true
        contentType="text/html"
        ssp("/home", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp")
    } else {
      contentType="text/html"
        ssp("/connection", "Unmatch" -> true)
    }
  }


  post("/addProject"){
      contentType="text/html"
    if (is_connected == true)
    { 
      modelProject.addProject(params("project_name"), params("description"), params("author_name"), params("email"))
      ssp("/home", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp")
    }
  }



  post("/connect"){
    contentType="text/html"
    ssp("/", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp", "Connect" -> true)
  }

 /* post("addCounterpart"){

  //Saving of the counterparts
  model.addCounterpart(params("name"), params("value"), params("description"))

  }*/
  

}
