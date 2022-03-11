package com.example.notesservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import com.example.notesservice.annotation.IsValidStatus;
import com.example.notesservice.dto.NotesDto;
import com.example.notesservice.exception.NoteIdNotFoundException;
import com.example.notesservice.exception.NotesException;
import com.example.notesservice.service.NoteService;
@RestController
@RequestMapping("/noteservice")
public class NoteController {
    
    @Autowired
    private NoteService noteService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllnotes(){
        List<NotesDto> notes = noteService.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNote(@Valid @RequestBody NotesDto note ) throws NotesException{
         NotesDto addNote = noteService.addNote(note);
        return new ResponseEntity<>(addNote, HttpStatus.CREATED); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable String id) throws NoteIdNotFoundException{
        NotesDto notesDto = noteService.deleteNote(id);
        return new ResponseEntity<>(notesDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getNote(@PathVariable String id) throws NoteIdNotFoundException{
        NotesDto notesDto = noteService.findById(id);
        return new ResponseEntity<>(notesDto,HttpStatus.OK);
    }

    @PutMapping("/update/{id}/{status}")
     public ResponseEntity<?> UpdateStatus(@PathVariable String id, @PathVariable  @IsValidStatus(listOfValidStatus = "completed|pending") String status){
        NotesDto notesDto = noteService.updateStatus(id,status) ;
        return new ResponseEntity<>(notesDto,HttpStatus.CREATED);
    }

    @GetMapping("/findByAuthor/{author}")
    public ResponseEntity<?> findByAuthor( @PathVariable String author){
        List<NotesDto> notes = noteService.findAllByAuthor(author);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("findbyStatus/{status}")
    public ResponseEntity<?> findbyStatus(@PathVariable String status){
        List<NotesDto> notes = noteService.findAllByStatus(status);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
}