package com.easywebsites.app

import org.scalatra._
import com.mongodb.casbah.Imports._

class ControllerServlet extends EasywebsitesappStack {

  var is_connected = false
    val modelUser = new ModelControllerUser()
    val modelProject = new ModelControllerProject()

    get("/") {
      contentType="text/html"
        ssp("/home", "Projects" -> modelProject.getProjects())
    }
    
    get("/connect") {
      contentType="text/html"
        ssp("/home", "Projects" -> modelProject.getProjects(), "Connect" -> true)
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
        ssp("/home", "Projects" -> modelProject.getProjects())

  }

  post("/connect/addProject"){
    contentType="text/html"
      modelProject.addProject(params("project_name"), params("description"), params("author_name"), params("email"))
      contentType="text/html"
      ssp("/home", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp", "Projects" -> modelProject.getProjects(), "Add" -> true, "Connect" -> true)
  }


  get("/connection"){
    contentType="text/html"
      ssp("/connection")
  }

  get("/connect/deconnection"){
    is_connected = false
      contentType="text/html"
      ssp("/home", "Projects" -> modelProject.getProjects())
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
        ssp("/home", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp", "Projects" -> modelProject.getProjects(), "Connect" -> true)
    }  
  }

  post("/connection")
  {
    if (modelUser.matchUser(params("email"), params("password"))) {
      is_connected = true
        contentType="text/html"
        ssp("/home", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp", "Projects" -> modelProject.getProjects(), "Connect" = true)
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
          ssp("/home", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp", "Projects" -> modelProject.getProjects(), "Connect" -> true)
      }
  }



  post("/connect"){
    contentType="text/html"
      ssp("/", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp", "Connect" -> true)
  }

  post("/connect/addCounterpart"){
    contentType="text/html"
  modelProject.addCounterpart(params("title"), params("value").toInt, params("description"))
        ssp("/newProject", "layout" -> "WEB-INF/templates/layouts/layout_connected.ssp", "Counterparts" -> modelProject.getCounterparts(), "Add" -> true)

  }


}
