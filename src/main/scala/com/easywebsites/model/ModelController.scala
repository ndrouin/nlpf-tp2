package com.easywebsites.app

import com.mongodb.casbah.Imports._
import scala.util.parsing.json._
import net.liftweb.json._

case class User (
  username : String,
  password: String,
  name: String
)

class ModelController() {

  val mongoClient = MongoClient()
  val db = mongoClient("easywebsites")

  def getUser () : String = {
    val collection = db("users")
    val user = collection.findOne(MongoDBObject("username" -> "admin"))
    implicit val formats = DefaultFormats
    val json = parse(user.getOrElse(0).toString)
    val u = json.extract[User]     
    return u.name
  }

  def insertUser (username : String, password: String, name: String) : Unit = {
    val collection = db("users")
    val user = MongoDBObject("username" -> username,
                             "password" -> password,
                             "name" -> name)
    collection.insert(user) 
  }
}
