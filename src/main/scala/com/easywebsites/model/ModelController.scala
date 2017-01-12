package com.easywebsites.app

import com.mongodb.casbah.Imports._

class ModelController() {

  val mongoClient = MongoClient()
  val db = mongoClient("easywebsites")

  def getUser () : String = {
    val collection = db("users")
    val user = collection.findOne(MongoDBObject("username" -> "admin"))
    return user.getOrElse("username").toString
  }
}
