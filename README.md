# Book Store
> The general overview of this project is to list all the books from the database and add new data to database.
### Database Implementation
All the connection string required to connect with the database are written in `src/main/resources/application.properties`
#### Database Information:
* Database Provider: `Amazon RDS`
* Database Management System: `MySQL`
### Model
All the database attributes along with data validation are in `src/main/java/BookStore.books/Books.java` file.
#### 	Server-side Validation Used
* @NotNull
* @Size() with min and max attribute
* @Min()

`PageCounter.java` is another model file that keep count of page visited by user when the server is started. It has method called `incrementPageCount()` which increment the `pageCounter` value each time  it is called.

### View
> Thymeleaf is used as **Template Engine**
Both `index.html` and `addNewBook.html`found under `src/resources/templates/`
* `index.html` contains the table that populates the list of data available in database.
* `addNewBook.html` contains form that ask user to input the book information and if it pass the model validation then it is redirected to `index` page with success message. Otherwise, it is redirected to `addNewBook.html` along with the error message

For Styling `bootstrap5` is used.

    Both index and addNewBook files contain the asynchronous API
    call to "/currentcount" every 3 second using ajax to display
    the count that page has been visited. This "/currentcount" is 
    URL provided by the RestController.

### Controller
There are two controller namely `BookController.java` and `PageCountController.java` are located in `src/main/java/BookStore.books/`folder.
1. BookController.java
* contains two `GetMapping()` URL. First one is `GetMapping("/")` that render the `index.html`and second one is `GetMapping("/createbook")` that render `addNewBook.html` file.
* contains one `PostMapping("/createbook")` that handles the form submission.`RedirectAttribute` is implemented to add `FlashAttribute`to pass data while redirecting the page.

2. PageCountController.java
* Contains the `RestController` implementation that returns the current `pageCounter`value.

### Dependency Injection Implementation
Dependency injection is implemented creating an interface `BookRepository` which contains method `findByName(String name)`
that takes string as an argument.
This method is implemented in `PageCountController.java`.
It is also used in `BookController` to display the data to `index`page and to save data in database.

### Unit Test
#### BookStoreController Unit Test
Test file can be found at `src/test/java/BookStoreTest.java`
**MockMvcTesting Framework** is used.
Unit Test includes following methods:
1. **CreateBookTest() :** This method checks the `GetMapping("/createbook")` route whether `addNewBook.html` will be render correctly or not.
2. **CreatePostTest():** This method test the scenario when the  provided data pass the server-side validation. And also checks which page it will redirect after saving the data. This method is for `PostMapping("/createbook")`route.

3. **createPostTest_InvalidBookInfo():**  This method is for invalid data for `PostMapping("/createbook")` route.

5. **listUserTest():** This method test whether the saved data and data being displayed in `index` page match or not.

#### PageCountController Unit Test
Test can be found at `src/test/java/PageCountControllerTest.java`

1. **testGetBooksInfoByName():** This method test the JSON data fetched using `/book/?name=<book_name>` . It validates the response fetched actually matches the expected data.

