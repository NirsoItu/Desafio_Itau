package com.itau.desafio.controller;

import com.itau.desafio.model.TimeModel;
import com.itau.desafio.service.TimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "API Rest Transações e Estatísticas")
@CrossOrigin(origins = "*")
public class TimeController {

    @Autowired
    private TimeService timeService;

    // Endpoint para buscar o tempo de estatística
    @RequestMapping(method = RequestMethod.GET, value = "/tempo")
    @ApiOperation(value = "Retorna um tempo em segundos para estatísca atual")
    public List<TimeModel> getSeconds() {
        timeService.getSeconds();
        return timeService.getSeconds();
    }

    // Endpoint para incluir um tempo de estatística
    @RequestMapping(method = RequestMethod.POST, value = "/tempo")
    @ApiOperation(value = "Cria um tempo em segundos para estatísca atual")
    public ResponseEntity<?> post(@RequestBody TimeModel timeModel) throws Exception {
        timeService.addSeconds(timeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("O tempo foi incluído com sucesso! ");
    }
}
