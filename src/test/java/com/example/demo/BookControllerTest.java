//package com.example.demo;
//
//import com.example.demo.DTO.Book;
//import com.example.demo.repository.BookRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.awt.*;
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//public class BookControllerTest {
//
//    private static final ObjectMapper om = new ObjectMapper();
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookRepository mockRepository;
//
//    @Before
//    public void init(){
//        Book book = new Book(1L,"SQL", "Thong", new BigDecimal("10.00"));
//        when(mockRepository.findById(1L)).thenReturn(Optional.of(book));
//    }
//
//    @Test
//    public void find_bookID_ok() throws Exception{
//
//        mockMvc.perform(get("/books/1"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1L)))
//                .andExpect(jsonPath("$.name", is("SQL")))
//                .andExpect(jsonPath("$.author", is("Thong")))
//                .andExpect(jsonPath("$.price", is(10.00)));
//
//        verify(mockRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void find_allBook_OK() throws Exception {
//        List<Book> books = Arrays.asList(
//                new Book(1L, "Book A", "Ah Pig", new BigDecimal("1.99")),
//                new Book(2L, "Book B", "Ah Dog", new BigDecimal("2.99")));
//
//        when(mockRepository.findAll()).thenReturn(books);
//
//        mockMvc.perform(get("/books"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].name", is("Book A")))
//                .andExpect(jsonPath("$[0].author", is("Ah Pig")))
//                .andExpect(jsonPath("$[0].price", is(1.99)))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].name", is("Book B")))
//                .andExpect(jsonPath("$[1].author", is("Ah Dog")))
//                .andExpect(jsonPath("$[1].price", is(2.99)));
//
//        verify(mockRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void find_bookIdnotFound_Ok() throws Exception{
//        mockMvc.perform(get("/books/5")).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void save_book_Ok() throws Exception{
//        Book newBook = new Book(1L, "Java", "Toan", new BigDecimal("22.00"));
//        when(mockRepository.save(any(Book.class))).thenReturn(newBook);
//
//        mockMvc.perform(post("/books")
//                        .content(om.writeValueAsString(newBook))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Java")))
//                .andExpect(jsonPath("$.author", is("Toan")))
//                .andExpect(jsonPath("$.price", is(22.00)));
//
//        verify(mockRepository, times(1)).save(any(Book.class));
//    }
//
//    @Test
//    public void update_book_Ok() throws Exception{
//        Book updateBook = new Book(1L, "Thuat Toan", "Luong", new BigDecimal("13.99"));
//        when(mockRepository.save(any(Book.class))).thenReturn(updateBook);
//
//        mockMvc.perform(put("/books/1")
//                        .content(om.writeValueAsString(updateBook))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Thuat Toan")))
//                .andExpect(jsonPath("$.author", is("Luong")))
//                .andExpect(jsonPath("$.price", is(13.99)));
//    }
//
//    @Test
//    public void patch_bookAuthor_Ok() throws Exception {
//
//        when(mockRepository.save(any(Book.class))).thenReturn(new Book());
//        String patchInJson = "{\"author\":\"Thong\"}";
//
//        mockMvc.perform(patch("/books/1")
//                .content(patchInJson)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//        verify(mockRepository, times(1)).findById(1L);
//        verify(mockRepository, times(1)).save(any(Book.class));
//    }
//
//    @Test
//    public void patch_BookPrice_405() throws Exception {
//
//        String pathInJson = "{\"price\":\"100\"}";
//
//        mockMvc.perform(patch("/books/1")
//                .content(pathInJson)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(status().isMethodNotAllowed());
//
//        verify(mockRepository, times(1)).findById(1L);
//        verify(mockRepository, times(0)).save(any(Book.class));
//    }
//
//    @Test
//    public void delete_book_Ok() throws Exception {
//        doNothing().when(mockRepository).deleteById(1L);
//
//        mockMvc.perform(delete("/books/1"))
//                .andExpect(status().isOk());
//
//        verify(mockRepository, times(1)).deleteById(1L);
//    }
//
//    private static void printJSON(Object object) {
//        String result;
//
//        try{
//            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
//        } catch (JsonProcessingException e){
//            e.printStackTrace();
//        }
//    }
//}
