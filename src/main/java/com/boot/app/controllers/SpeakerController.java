package com.boot.app.controllers;

import com.boot.app.models.Speaker;
import com.boot.app.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker list(@PathVariable Long id){
        return speakerRepository.getById(id);
    }

    @PostMapping
    public Speaker create(@RequestBody Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker oldSpeaker = speakerRepository.getById(id);
        BeanUtils.copyProperties(speaker,oldSpeaker,"speaker_id");
        return speakerRepository.saveAndFlush(oldSpeaker);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

}
