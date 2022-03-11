package com.example.notesservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.notesservice.document.Note;
import com.example.notesservice.dto.NotesDto;
import com.example.notesservice.exception.NoteIdNotFoundException;
import com.example.notesservice.repo.NoteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    
    @Autowired
    private NoteRepository noteRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NotesDto> findAll() {           
        
        List<NotesDto> notesDtos = noteRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return notesDtos;
    }

    @Override
    public NotesDto findById(String id) {
                 Note note = noteRepository.findById(id).orElseThrow(()->new NoteIdNotFoundException("Note not found  for this id ::"+id));
        return convertEntityToDto(note);
    }
    
    @Override
    public NotesDto addNote(NotesDto noteDto) {
         Note savedNote = noteRepository.save(convertDtoToEntity(noteDto));
        return convertEntityToDto(savedNote);
        
    }

    @Override
    public NotesDto deleteNote( String id) {        

        Note note = noteRepository.findById(id).orElseThrow(()->new NoteIdNotFoundException("Note not found  for this id ::"+id));
        noteRepository.delete(note);
        return convertEntityToDto(note);
    }

    @Override
    public List<NotesDto> findAllByStatus(String status) {

        List<NotesDto> notesDto = noteRepository.findByStatus(status).stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return notesDto;
    }

    @Override
    public List<NotesDto> findAllByAuthor( String author) {

        List<NotesDto> notesDto = noteRepository.findByAuthor(author).stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return notesDto;
    }

    @Override
    public NotesDto updateStatus( String id,  String status) {
       
        Note note = noteRepository.findById(id).orElseThrow(()->new NoteIdNotFoundException("Note not found  for this id ::"+id));
        note.setStatus(status);
        Note updatedNote = noteRepository.save(note);
        return convertEntityToDto(updatedNote);

    }
    
    private NotesDto convertEntityToDto(Note note){
      modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
      NotesDto notesDto = modelMapper.map(note,NotesDto.class);
      return notesDto;
    }

    private Note convertDtoToEntity(NotesDto notesDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Note note= modelMapper.map(notesDto,Note.class);
        return note;
    }
}