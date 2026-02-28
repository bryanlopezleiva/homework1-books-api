package com.example.webbackend.controller;

import com.example.webbackend.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookController {
    private List<Book> books = new ArrayList<>();

    private Long id = 1L;

    public BookController()
    {
        books.add(new Book(id++, "Java", "Author1", 20.00));
        books.add(new Book(id++, "C++", "Author2", 30.00));
        books.add(new Book(id++, "Python", "Author3", 35.00));
        books.add(new Book(id++, "JavaScript","Author4",40.00));
    }

    //get all books at point /api/books
    @GetMapping("/books")
    public List<Book> getBooks()
    {
        return books;
    }

    //now we need to get book by id
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id)
    {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    //here we are going to create a new book
    @PostMapping("/books")
        public List<Book> createBook(@RequestBody Book book)
        {
            books.add(book);
            return books;
        }

    //search by title
    @GetMapping("/books/search")
    public List<Book> searchByTitle(@RequestParam(required = false, defaultValue = "") String title)
    {
        if(title.isEmpty())
        {
            return null;
        }
        return books.stream().filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
    }

    //price range
    @GetMapping("/books/price-range")
    public List<Book> getBooksByPrice(@RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice)
    {
        return books.stream().filter(book -> {boolean min = minPrice == null || book.getPrice() >= minPrice;
        boolean max = maxPrice == null || book.getPrice() <= maxPrice;

        return min && max;
        }).collect(Collectors.toList());


    }
    //sort the data
    @GetMapping("/books/sorted")
    public List<Book> getSortedBooks(
            @RequestParam(required = false, defaultValue = "title") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order
    ){
        Comparator<Book> bookComparator;
        switch (sortBy.toLowerCase())
        {
            case "author":
                bookComparator = Comparator.comparing(Book::getAuthor);
                break;
            case "price":
                bookComparator = Comparator.comparing(Book::getPrice);
                break;
            default:
                bookComparator = Comparator.comparing(Book::getTitle);
                break;
        }
        if("desc".equalsIgnoreCase(order))
        {
            bookComparator = bookComparator.reversed();
        }

        return books.stream().sorted(bookComparator).collect(Collectors.toList());
    }

    //update a book so PUT
    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updateBook)
    {
        for (Book book : books)
        {
            if (book.getId().equals(id))
            {
                book.setAuthor(updateBook.getAuthor());
                book.setPrice(updateBook.getPrice());
                book.setTitle(updateBook.getTitle());
                return book;
            }
        }
        return null;
    }

    //partial update so PATCH
    @PatchMapping("/books/{id}")
    public Book patchBook(@PathVariable Long id, @RequestBody Book patchBook)
    {
        for(Book book : books)
        {
            if (book.getId().equals(id))
            {
                /// here i need to check if certain changes happened to a particular id
                /// we need to check if the patch book is not null and the given field is not empty
                if (patchBook.getTitle() != null && !patchBook.getTitle().isEmpty())
                {
                    book.setTitle(patchBook.getTitle());
                }
                if (patchBook.getAuthor() != null && !patchBook.getAuthor().isEmpty())
                {
                    book.setAuthor(patchBook.getAuthor());
                }
                if (patchBook.getPrice() != null)
                {
                    book.setPrice(patchBook.getPrice());
                }
                return book;
            }
        }
        return null;
    }


    // remove a book so DELETE
    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id)
    {
        books.removeIf(book -> book.getId().equals(id));
    }

    // GET endpoint with pagination
    // here we want the size needed for an x amount of pages
    //
    @GetMapping("/books/paginated")
    public List<Book> getBooksPaginated(@RequestParam int page,
                                        @RequestParam int size)
    {
        /// we need to indicate how much should be on the starting page; essentially a start and end
        int start  = page * size;
        int end  = Math.min(start + size, books.size());

        if (start >= books.size())
        {
            return new ArrayList<>();
        }
        return books.subList(start, end);
    }

    // advanced GET endpoint with filtering, sorting, and pagination combined in the valid order
    @GetMapping("/books/advanced")
    public List<Book> getAdvanced(@RequestParam(required = false, defaultValue = "")String title,
                                  @RequestParam(required = false) Double minPrice,
                                  @RequestParam(required = false) Double maxPrice,
                                  @RequestParam(required = false, defaultValue = "title") String sortBy,
                                  @RequestParam(required = false, defaultValue = "asc") String order,
                                  @RequestParam int page,
                                  @RequestParam int size)
    {
        /// the goal is to filter books, sort, and paginate them

        /// here we are filtering the books
        List<Book> filteredBooks = books.stream()
                .filter(book -> title.isEmpty() ||
                        book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(book -> minPrice == null ||
                        (book.getPrice() != null && book.getPrice() >= minPrice))
                .filter(book -> maxPrice == null ||
                        (book.getPrice() != null && book.getPrice() <= maxPrice))
                .collect(Collectors.toList());

        /// no we need to sort
        Comparator<Book> bookComparator;
        switch (sortBy.toLowerCase())
        {
            case "author" :
                bookComparator = Comparator.comparing(Book::getAuthor);
                        break;
            case "price":
                bookComparator = Comparator.comparing(Book::getPrice);
                break;
            default:
                bookComparator = Comparator.comparing(Book::getTitle);
                break;
        }
        if ("desc".equalsIgnoreCase(order))
        {
            bookComparator = bookComparator.reversed();
        }
        filteredBooks.sort(bookComparator);

        ///now here we paginate
        int start = size * page;
        int end = Math.min(start + size, filteredBooks.size());

        if (start >= filteredBooks.size())
        {
            return new ArrayList<>();
        }
        return filteredBooks.subList(start, end);
    }
}
