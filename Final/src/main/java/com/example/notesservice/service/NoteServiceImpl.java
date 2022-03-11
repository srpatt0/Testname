package com.example.notesservice.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import com.example.notesservice.exception.NoteIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.notesservice.dto.NotesDto;
import com.example.notesservice.model.Note;
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
           
        // List<NotesDto> notesDtoObj = new ArrayList<>();
        // List<Note> noteObj = noteRepository.findAll();
        // NotesDto dto = null;
        // for ( Note n : noteObj) {
        //     dto= new NotesDto();
        //     dto.setAuthor(n.getAuthor());
        //     dto.setDescription(n.getDescription());
        //     dto.setId(n.getId());
        //     dto.setStatus(n.getStatus());
        //     dto.setTitle(n.getTitle());
        //     notesDtoObj.add(dto);
        // }
        // return notesDtoObj;

        List<NotesDto> notesDtos = noteRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return notesDtos;
    }

    @Override
    public NotesDto findById(Integer id) {
        //return null;
        // Optional<Note> n = noteRepository.findById(id);
        // final NotesDto dto = new NotesDto();
        // if(n.isPresent())
        // {
        //     dto.setAuthor(n.get().getAuthor());
        //     dto.setDescription(n.get().getDescription());
        //     dto.setId(n.get().getId());
        //     dto.setStatus(n.get().getStatus());
        //     dto.setTitle(n.get().getTitle());
        // }
        // return dto;
         Note note = noteRepository.findById(id).orElseThrow(()->new NoteIdNotFoundException("Note not found  for this id ::"+id));
        return convertEntityToDto(note);
    }
    
    @Override
    public NotesDto addNote(NotesDto noteDto) {
        //return null;
        // Note n = new Note();
        //  n.setAuthor(noteDto.getAuthor());
        //  n.setDescription(noteDto.getDescription());
        //  n.setId(noteDto.getId());
        //  n.setStatus(noteDto.getStatus());
        //  n.setTitle(noteDto.getTitle());
        //  noteRepository.save(n);

        //  return noteDto;

        Note savedNote = noteRepository.save(convertDtoToEntity(noteDto));
        return convertEntityToDto(savedNote);
        
    }

    @Override
    public NotesDto deleteNote( Integer id) {
        //return null;
        // NotesDto  dto = new NotesDto();
        // Optional<Note> n = noteRepository.findById(id);
        //  if(n.isPresent())
        // {
        //     noteRepository.deleteById(id);
        //     dto.setAuthor(n.get().getAuthor());
        //     dto.setDescription(n.get().getDescription());
        //     dto.setId(n.get().getId());
        //     dto.setStatus(n.get().getStatus());
        //     dto.setTitle(n.get().getTitle());
        // }
        //   return dto;

        Note note = noteRepository.findById(id).orElseThrow(()->new NoteIdNotFoundException("Note not found  for this id ::"+id));
        noteRepository.delete(note);
        return convertEntityToDto(note);
    }

    @Override
    public List<NotesDto> findAllByStatus(String status) {
        //return null;
        // List<NotesDto> objNotesDto = new ArrayList<>();
        // List<Note> objNotes = noteRepository.findByStatus(status);
        // NotesDto dto = null;
        // for (Note n : objNotes) {
        // if(n.getStatus().equals(status))
        //     dto = new NotesDto();
        //     dto.setAuthor(n.getAuthor());
        //     dto.setDescription(n.getDescription());
        //     dto.setId(n.getId());
        //     dto.setStatus(n.getStatus());
        //     dto.setTitle(n.getTitle());
        //     objNotesDto.add(dto);
        // }
        // return objNotesDto;
        List<NotesDto> notesDto = noteRepository.findByStatus(status).stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return notesDto;
    }

    @Override
    public List<NotesDto> findAllByAuthor( String author) {
        //return null;
        // List<NotesDto> objNotesDto = new ArrayList<>();
        // List<Note> objNotes = noteRepository.findByAuthor(author);
        // NotesDto dto = null;
        // for (Note n : objNotes) {
        //    if(n.getAuthor().equals(author))
        //    {
        //         dto = new NotesDto();
        //         dto.setAuthor(n.getAuthor());
        //         dto.setDescription(n.getDescription());
        //         dto.setId(n.getId());
        //         dto.setStatus(n.getStatus());
        //         dto.setTitle(n.getTitle());
        //         objNotesDto.add(dto);
        //    }
        // }
        // return objNotesDto;
        List<NotesDto> notesDto = noteRepository.findByAuthor(author).stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return notesDto;
    }

    @Override
    public NotesDto updateStatus( Integer id,  String status) {
        //return null;
    //     Optional<Note> n = noteRepository.findById(id).map(note -> {
    //         note.setStatus(status);
    //         return noteRepository.save(note);
        
    //     });
    //    NotesDto  dto = new NotesDto();
       
    //     dto.setAuthor(n.get().getAuthor());
    //     dto.setDescription(n.get().getDescription());
    //     dto.setId(n.get().getId());
    //     dto.setStatus(n.get().getStatus());
    //     dto.setTitle(n.get().getTitle());
    //     return dto;
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
