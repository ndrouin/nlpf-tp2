package com.easywebsites.app

import org.scalatra._

class ControllerServlet extends EasywebsitesappStack {

var is_connected = false

  get("/") {
    <html>
      <body>
        <h1>Production</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
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
  //get result of authentification
  /*result := model.Connection(email, password)
  if result {
    ctx.Session().Set("isConnected", "true")
    ctx.Redirect("/connect/")
  } else {
    ctx.Render("connection_error.html", nil)
  }*/

  //ssp("/connection")
  }

}
