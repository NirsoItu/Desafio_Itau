package com.itau.desafio.controller;

import com.itau.desafio.model.TimeModel;
import com.itau.desafio.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TimeController {

    @Autowired
    private TimeService timeService;

    // Endpoint para buscar o tempo de estatística
    @RequestMapping(method = RequestMethod.GET, value = "/tempo")
    public List<TimeModel> getSeconds() {
        timeService.getSeconds();
        return timeService.getSeconds();
    }

    // Endpoint para incluir um tempo de estatística
    @RequestMapping(method = RequestMethod.POST, value = "/tempo")
    public ResponseEntity<?> post(@RequestBody TimeModel timeModel) throws Exception {
        timeService.addSeconds(timeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("O tempo foi incluído com sucesso! ");
    }
}
