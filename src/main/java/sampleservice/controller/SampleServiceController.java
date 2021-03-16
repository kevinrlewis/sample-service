package sampleservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api")
public class SampleServiceController {

    private final ObjectMapper objectMapper;

    @Autowired
    public SampleServiceController(
            ObjectMapper objectMapper
    ){
        this.objectMapper = objectMapper;
    }


    @PostMapping({"/send"})
    public ResponseEntity<JsonNode> sendEmail(
            @Valid @RequestBody JsonNode payload
    ) {
        UUID uuid = UUID.randomUUID();
        return ResponseEntity.ok(createResponseNode(200, "Nice!", uuid.toString()));
    }

    // Helper method to create a simple response node
    private ObjectNode createResponseNode(int status, String message, String uuid) {
        ObjectNode responseNode = this.objectMapper.createObjectNode();

        responseNode.put("status", status);
        responseNode.put("message", message);
        responseNode.put("uuid", uuid);

        return responseNode;
    }
}
