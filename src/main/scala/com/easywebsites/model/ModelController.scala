package com.easywebsites.app

import com.mongodb.casbah.Imports._
import scala.util.parsing.json._
import net.liftweb.json._

case class User (
    username : String,
    password: String,
    firstname: String,
    lastname: String
    )

  class ModelControllerUser() {

    val mongoClient = MongoClient()
      val db = mongoClient("easywebsites")

      def getUser () : User = {
        val collection = db("users")
          val user = collection.findOne(MongoDBObject("username" -> "admin"))
          implicit val formats = DefaultFormats
          val json = parse(user.getOrElse(0).toString)
          val u = json.extract[User]     
          return u
      }

    def insertUser (username : String, password: String, firstname: String, lastname : String) : Unit = {
      val collection = db("users")
        val user = MongoDBObject("username" -> username,
            "password" -> password,
            "firstname" -> firstname,
            "lastname" -> lastname)
        collection.insert(user) 
    }

    def userExist (username : String) : Boolean = {
      val collection = db("users")
        val user = collection.findOne(MongoDBObject("username" -> username))
        if (user.isEmpty) {
          return false
        } else {
          return true
        }
    }

    def matchUser (username : String, password : String) : Boolean = {
      val collection = db("users")
        val user = collection.findOne(MongoDBObject("username" -> username,
                                                    "password" -> password))
        if (user.isEmpty) {
          return false
        } else {
          return true
        }
    }
  }

 
  def addProject (name : String, description: String, author: String, contact: String) : Unit = {
    val collection = db("project")
    val project = MongoDBObject("name" -> name,
                             "description" -> description,
                             "author" -> author,
                             "contact" -> contact)
    collection.insert(project) 
  }
 

  def addCounterpart (name : String, value: String, description: String) : Unit = {
    val collection = db("counterpart")
    val counterpart = MongoDBObject("name" -> name,
                             "value" -> value,
                             "description" -> description)
    collection.insert(counterpart) 
  }

 
}
