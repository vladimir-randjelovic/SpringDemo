package application.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/rest")
@RestController
public class HelloController {

    @GetMapping("")
    public String index() {
        return "Hello world!";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> getMethod(@RequestParam String name) {
        return new ResponseEntity<>("/test GET method response. Name: "+name, HttpStatus.OK);
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postMethod(@PathVariable(value = "id") int id, @RequestBody String body) {

        return new ResponseEntity<>("/test POST method response. ID: "+id+"      Body: "+body, HttpStatus.OK);
    }

}
