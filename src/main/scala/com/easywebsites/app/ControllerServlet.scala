package com.easywebsites.app

import org.scalatra._

class ControllerServlet extends EasywebsitesappStack {

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
  ssp("/newUser")
  }

  get("/connection"){
  contentType="text/html"
  ssp("/connection")
  }

  post("/connection"){
  contentType="text/html"

 
  email := params.get("email")
  password := params.get("password")
  //get result of authentification
  result := model.Connection(email, password)
  if result {
    ctx.Session().Set("isConnected", "true")
    ctx.Redirect("/connect/")
  } else {
    ctx.Render("connection_error.html", nil)
  }

  ssp("/connection")
  }

}
