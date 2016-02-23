package org.pongmatcher.web;

import java.util.HashMap;
import java.util.Map;

import org.pongmatcher.repositories.MatchRepository;
import org.pongmatcher.repositories.MatchRequestRepository;
import org.pongmatcher.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
final class EveryResourceController {

    private final MatchRequestRepository matchRequestRepository;

    private final MatchRepository matchRepository;

    private final ResultRepository resultRepository;

    @Autowired
    EveryResourceController(MatchRequestRepository matchRequestRepository, MatchRepository matchRepository,
                            ResultRepository resultRepository) {
        this.matchRequestRepository = matchRequestRepository;
        this.matchRepository = matchRepository;
        this.resultRepository = resultRepository;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/all")
    void delete() {
        matchRequestRepository.deleteAll();
        matchRepository.deleteAll();
        resultRepository.deleteAll();
    }
    
    /**
     * Add a new method to handle root page access
     * @lmthien
     */
    @RequestMapping(method = RequestMethod.GET, value= "/")
    public ResponseEntity<Map<String, String>> indexHello() {
    	String welcome = "Welcome to pong-matcher-spring application updated!";
    	Map<String, String> body = new HashMap<>();
    	body.put("root_page_welcome", welcome);
    	return new ResponseEntity<>(body, HttpStatus.OK);
    }
    
}

