package com.ReadCorner.Library.controller;


import com.ReadCorner.Library.dto_request.AuthenticationRequest;
import com.ReadCorner.Library.dto_request.RegisterRequest;
import com.ReadCorner.Library.dto_response.GResponse;
import com.ReadCorner.Library.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication") // for issue swagger group called Authentication and group all below endpoints under it
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<GResponse> register(@RequestBody @Valid RegisterRequest request
    ) throws MessagingException {

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<GResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    // token as params variable
    @GetMapping("/activate-account")
    public GResponse confirm(@RequestParam String token
    ) throws MessagingException {
        return service.activateAccount(token);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("-----------Log test --------------------");
        return ResponseEntity.ok("Hello from test endpoint!");
    }

}
