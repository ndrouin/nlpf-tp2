package com.easywebsites.app

import com.mongodb.casbah.Imports._
import scala.util.parsing.json._
import net.liftweb.json._

case class Project (
    name: String,
    description: String,
    author: String,
    contact: String
    )

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
      val collection = db("counterparts")
        val counterpart = MongoDBObject("name" -> name,
            "value" -> value,
            "description" -> description)
        collection.insert(counterpart) 
    }

    def getProjects () : List[Project] = {
      val collection = db("project")
        val projects = collection.find()
        var jsonProjects = List[Project]() 
        for (p <- projects) {
          implicit val formats = DefaultFormats;
          var json = parse(p.toString);
          val project = json.extract[Project];
          jsonProjects = project :: jsonProjects;  
        }
      return jsonProjects         
    }

    def getProjectsItem (item : String) : List[String] = {
      var result = List[String]()
        for (p <- getProjects()) {
          if (item == "name")
            result = p.name :: result
          else if (item == "description")
            result = p.description :: result

        }   
      return result 
    }

  }
