import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet, SQLException, SQLSyntaxErrorException}
import java.util.{InputMismatchException, Scanner}
import scala.io.StdIn.readLine;

object project0 {
  val url = "jdbc:mysql://localhost:3306/project0"
  val driver = "com.mysql.cj.jdbc.Driver"
  val username = "root"
  val password = "R3v@tu43"
  val scanner = new Scanner(System.in)
  var connection:Connection = DriverManager.getConnection(url, username, password)


  def main(args: Array[String]): Unit = {




    def greetingMenu() : Unit ={
      println("Welcome to your personal book log. Here you can either: \n1) Browse the library database for a book information \n2) Check your reading list \n" +
        "3) Add a book to your reading list \n4) delete a book from your reading list \n5) update the read status \n" +
        "6) add a book to the library database \n8) quit program")
    }

    //this will allow you to search for a book depending on the author, title, publisher, isbn. it will be able to call the add book method
    def searchBook(): Unit = {
      var x = ""
      println("Search by:\n(1) title\n(2) author\n(3) publisher\n(4) isbn number\n(5) list everything")
      var i = scanner.nextInt()
      i match {
        case 1 => val statement = connection.createStatement()
          println("Type in the title of your book.")
          var j = readLine()
          val resultSet = statement.executeQuery("SELECT * FROM library WHERE title LIKE '%"+j+"%';")
          println("Books containing word(s): "+j+"")
          while ( resultSet.next() ) {
            println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3) +" || "+ resultSet.getString(4) + " || "+resultSet.getString(5))
          }
        case 2 => val statement = connection.createStatement()
          println("Type in your author's name.")
          var j = readLine()
          val resultSet = statement.executeQuery("SELECT * FROM library WHERE authors LIKE '%"+j+"%';")
          println("Books with author names containing: "+j+"")
          while ( resultSet.next() ) {
            println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3)+" || "+ resultSet.getString(4)+" || "+resultSet.getString(5))
          }
        case 3 => val statement = connection.createStatement()
          println("Type in publisher name.")
          var j = readLine()
          val resultSet = statement.executeQuery("SELECT * FROM library WHERE publisher LIKE '%"+j+"%';")
          println("Books with publishers names containing: "+j+"")
          while ( resultSet.next() ) {
            println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3) +" || "+ resultSet.getString(4) +" || "+resultSet.getString(5))
          }
        case 4 => val statement = connection.createStatement()
          println("Type in isbn.")
          var j = scanner.nextInt()
          val resultSet = statement.executeQuery("SELECT * FROM library WHERE isbn = " + j + ";")
          println("Book with matching ISBN: "+j+"")
          while ( resultSet.next() ) {
            println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3) +" || "+ resultSet.getString(4)+" || "+resultSet.getString(5))
          }
        case 5 => val statement = connection.createStatement()
          val resultSet = statement.executeQuery("SELECT * FROM library;")
          println("All books located in database: ")
          while ( resultSet.next() ) {
            println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3) +" || "+ resultSet.getString(4)+" || "+resultSet.getString(5))
          }

        case default => println("Invalid, taking you to the beginning")
      }
    }

    def readList(): Unit ={
      try{

        var x = ""
        println("Search by:\n(1) title\n(2) author\n(3) publisher\n(4) isbn number\n(5) read status\n(6) list everything")
        var i = scanner.nextInt()
        i match {
          case 1 => val statement = connection.createStatement()
            println("Type in the title of your book.")
            val j = readLine()
            val resultSet = statement.executeQuery("SELECT * FROM booklist WHERE booktitle LIKE '%"+j+"%';")
            println("Books in your list with title containing word(s): "+j+"")
            while ( resultSet.next() ) {
              println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3) +" || "+ resultSet.getString(4) + " || "+resultSet.getString(5) +" || "+ resultSet.getString(6))
            }
          case 2 => val statement = connection.createStatement()
            println("Type in your author's name.")
            var j = readLine()
            val resultSet = statement.executeQuery("SELECT * FROM booklist WHERE authors LIKE '%"+j+"%';")
            println("Books in your list with author names containing: "+j+"")
            while ( resultSet.next() ) {
              println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3)+" || "+ resultSet.getString(4)+" || "+resultSet.getString(5)+" || "+ resultSet.getString(6))
            }
          case 3 => val statement = connection.createStatement()
            println("Type in publisher name.")
            var j = readLine()
            val resultSet = statement.executeQuery("SELECT * FROM booklist WHERE publisher LIKE '%"+j+"%';")
            println("Books in your list with publisher name containing: "+j+"")
            while ( resultSet.next() ) {
              println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3) +" || "+ resultSet.getString(4) +" || "+resultSet.getString(5)+" || "+ resultSet.getString(6))
            }
          case 4 => val statement = connection.createStatement()
            println("Type in isbn.")
            val j = scanner.nextInt()
            val resultSet = statement.executeQuery("SELECT * FROM booklist WHERE isbn = " + j + ";")
            println("Books in your list with ISBN matching: "+j+"")
            while ( resultSet.next() ) {
              println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3) +" || "+ resultSet.getString(4)+" || "+resultSet.getString(5)+" || "+ resultSet.getString(6))
            }
          case 5 => val statement = connection.createStatement()
            println("Type either 'read', 'reading', 'to read', or 'dropped'")
            val j = readLine()
            val resultSet = statement.executeQuery("SELECT * FROM booklist WHERE readstatus ='"+j+"';")
            println("Books currently in your reading list under '"+j+"' status:")
            while ( resultSet.next() ) {
              println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3) +" || "+ resultSet.getString(4)+" || "+resultSet.getString(5)+" || "+ resultSet.getString(6))
            }
          case 6 => val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM booklist;")
            println("Showing all books currently in your library...")
            while ( resultSet.next() ) {
              println(resultSet.getString(1)+" || " +resultSet.getString(2) +" || " +resultSet.getString(3) +" || "+ resultSet.getString(4)+" || "+resultSet.getString(5)+" || "+ resultSet.getString(6))
            }

          case default => println("Invalid, taking you to the beginning")
        }
      }catch{
        case e: SQLSyntaxErrorException => println("This title is not currently in your reading list...")
      }
    }
    def addBook(): Unit = {

        println("Here you may add new entries to you reading list.\n(1) Add book by title \n(2) Add book by isbn\n" +
          "(3) Return to the main menu \n(4) Search the current library database")
        val i = scanner.nextInt()
        i match {
          case 1 =>
            println("Type in the title of your book.")
            val j = readLine()
            val statement = connection.createStatement()
            statement.executeUpdate("INSERT INTO booklist(IDbooklist,booktitle,authors,isbn, publisher)\nSELECT bookID, title, authors, isbn, publisher FROM library WHERE title LIKE '%"+j+"%'")
            println("Booklist updated.")

          case 2 =>
            println("Type in the isbn.")
            val j = scanner.nextLong()
            val statement = connection.createStatement()
            statement.executeUpdate("INSERT INTO booklist(IDbooklist,booktitle,authors,isbn, publisher)\nSELECT bookID, title, authors, isbn, publisher FROM library WHERE isbn = "+j+"")
            println("Booklist updated.")
          case 3 => println("Returning to menu...")
          case 4 => searchBook()

          case default => println("Invalid, taking you to the beginning")
        }
    }


    //this will allow you to delete a title in the booklog. we need the delete from query, and to know what you're search for
    def deleteBook(): Unit ={
      println("Type in isbn exactly: ")
      val isbn = scanner.nextLong()
      val statement = connection.createStatement()
      statement.executeUpdate("DELETE FROM booklist WHERE isbn = "+ isbn + ";")
      println("Entry deleted...")

    }

    //this will update the readstatuscolumn to read, will read, reading, or dropped
    def updateStatus(): Unit ={
      println("Type in your book's isbn.")
      val isbnInt = scanner.nextLong()
      println("Selected ISBN is " + isbnInt + "\nNow change your status using:\n(1) read\n(2) to read\n(3) reading\n(4) dropped")
      val k = scanner.nextInt()

      k match{
        case 1 => val statusString = "read"
          val statement = connection.createStatement()
          statement.executeUpdate("UPDATE booklist SET readstatus = '"+statusString+"' WHERE isbn = "+ isbnInt + ";")
        case 2 => val statusString = "to read"
          val statement = connection.createStatement()
          statement.executeUpdate("UPDATE booklist SET readstatus = '"+statusString+"' WHERE isbn = "+ isbnInt + ";")
        case 3 => val statusString = "reading"
          val statement = connection.createStatement()
          statement.executeUpdate("UPDATE booklist SET readstatus = '"+statusString+"' WHERE isbn = "+ isbnInt + ";")
        case 4 => val statusString = "dropped"
          val statement = connection.createStatement()
          statement.executeUpdate("UPDATE booklist SET readstatus = '"+statusString+"' WHERE isbn = "+ isbnInt + ";")
        case default => println("Invalid")
      }


    }

    //
    def addBookLibrary(): Unit ={
      println("Please enter book information one field at a time.")
      print("Title: ")
      val titleString = readLine()
      print("Author(s): ")
      val authorsString = readLine()
      print("ISBN: ")
      val isbnInt = scanner.nextLong()
      print("Publisher: ")
      val publisherInt = readLine()
      val statement = connection.createStatement()
      statement.executeUpdate("INSERT INTO library(title,authors, isbn, publisher) VALUES ('"+titleString+"', '"+authorsString+"',"+isbnInt+",'"+publisherInt+"');")
      val statement2 = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM library WHERE isbn = "+isbnInt+";")
      println("Added " + titleString + " to library.")


    }


    var quit = false
    while(quit == false){
      greetingMenu()
      try{
        val i = scanner.nextInt()
        i match {
          case 1 => searchBook()
          case 2 => readList()
          case 3 => addBook()
          case 4 => deleteBook()
          case 5 => updateStatus()
          case 6 => addBookLibrary()
          case 8 => quit = true
          case default => println("Invalid option, please try again.")
        }
      } catch {
        case e: InputMismatchException => println("Non-Integer entered. Exiting menu")
          quit = true
        case e: Exception => e.printStackTrace
        //case e: Exception => println("Unknown error occurred. Start from the beginning")
          //quit = false;
      }
    }
    println("Exiting Menu and shutting down connection...")
    connection.close()

  }
}

