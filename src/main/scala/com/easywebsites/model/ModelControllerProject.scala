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

case class Counterpart (
    name: String,
    description: String,
    value: Int
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


    def addCounterpart (name : String, value: Int, description: String) : Unit = {
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

    def getCounterparts () : List[Counterpart] = {
      val collection = db("counterparts")
        val counterparts = collection.find()
        var jsonCounterparts = List[Counterpart]() 
        for (c <- counterparts) {
          implicit val formats = DefaultFormats;
          var json = parse(c.toString);
          val counterpart = json.extract[Counterpart];
          jsonCounterparts = counterpart :: jsonCounterparts;  
        }
      return jsonCounterparts         
    }
  
    def getCounterpartsItem (item : String) : List[String] = {
      var result = List[String]()
        for (c <- getCounterparts()) {
          if (item == "name")
            result = c.name :: result
          else if (item == "description")
            result = c.description :: result
          else if (item == "value")
            result = c.value.toString :: result

        }   
      return result 
    }


}
