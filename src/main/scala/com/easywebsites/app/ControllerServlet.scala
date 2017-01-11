package com.easywebsites.app

import org.scalatra._

class ControllerServlet extends EasywebsitesappStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

}
