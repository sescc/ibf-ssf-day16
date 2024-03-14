package ibf2023.ssf.day16.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping("/home")
public class HomeController {
    
    // @GetMapping("/time")
    // @ResponseBody               // if don't have this, Thymeleaf will be expecting a page 
    // public ResponseEntity<String> getTimeAsJason() throws ParseException {
        
    //     Date currentDate = new Date();
    //     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //     //Date currDate = sdf.parse(currentDate.toString());
    //     String currDate = sdf.format(currentDate);
        
    //     // Fluent-style
    //     JsonObject obj = Json.createObjectBuilder()
    //             .add("time", currDate)
    //             .build();

    //     return ResponseEntity.ok(obj.toString());
    // }

    @GetMapping("/test")
    @ResponseBody               // if returning as String only, don't need
    public ResponseEntity<String> getTestEmployee() {
        JsonObject jEmployee = Json.createObjectBuilder()
                .add("firstName", "Tailor")
                .add("lastName", "Swyft")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(jEmployee.toString());      // or HttpStatus.CREATED, etc
        // since this is controller, not restcontroller, also ok to return as pure String
        // i.e. public String getTestEmployee() { ... return jEmployee.toString();}
    }
    
}
