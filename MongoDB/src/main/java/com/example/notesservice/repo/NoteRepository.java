package com.example.notesservice.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import com.example.notesservice.document.Note;

public interface NoteRepository extends MongoRepository<Note, String>{
     List<Note> findByAuthor(String author);
    List<Note> findByStatus(String status);
}
