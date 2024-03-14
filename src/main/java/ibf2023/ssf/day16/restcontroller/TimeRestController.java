package ibf2023.ssf.day16.restcontroller;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController
@RequestMapping(path="/api/time", produces=MediaType.APPLICATION_JSON_VALUE)      // or ="application/json"
public class TimeRestController {

    @GetMapping("")
    public ResponseEntity<String> getTimeAsJason() throws ParseException {
        
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Date currDate = sdf.parse(currentDate.toString());
        String currDate = sdf.format(currentDate);
        
        // Fluent-style
        JsonObject obj = Json.createObjectBuilder()
                .add("time", currDate)
                .build();

        return ResponseEntity.ok(obj.toString());
    }

    // payload string comes from above API function
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postTimeAsJsonString(@RequestBody String payload) {

        JsonReader jReader = Json.createReader(new StringReader(payload));
        JsonObject jObject = jReader.readObject();
        System.out.println("jObject payload: " + jObject.toString());

        JsonObject responsePayload = Json.createObjectBuilder()
                .add("time", jObject.get("time".toString()))
                .add("status", "unchanged")
                .add("updatedBy", "WSC")
                .build();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("WSC", new Date().toString());

        // alternative method to return response object
        return new ResponseEntity<>(responsePayload.toString(), headers, HttpStatus.OK);
    }
    
}
