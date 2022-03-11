package com.example.notesservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.notesservice.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{

    

     List<Note> findByAuthor(String author);
    List<Note> findByStatus(String status);
    

}
