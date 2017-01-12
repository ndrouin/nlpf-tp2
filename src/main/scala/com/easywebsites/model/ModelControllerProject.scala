package com.easywebsites.app

import com.mongodb.casbah.Imports._
import scala.util.parsing.json._

class ModelControllerProject() {
  val mongoClient = MongoClient()
    val db = mongoClient("easywebsites")

    def addProject (name : String, description: String, author: String, contact: String) : Unit = {
      val collection = db("projects")
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
