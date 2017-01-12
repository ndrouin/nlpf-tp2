db = db.getSiblingDB('toto')
db.users.insert(
    {
      username: "admin",
      password: "admin",
      firstname: "Administrator",
      lastname: "Admin"
    }
)
