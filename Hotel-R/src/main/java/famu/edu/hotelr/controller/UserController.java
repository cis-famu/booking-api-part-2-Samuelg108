package famu.edu.hotelr.controller;

import famu.edu.hotelr.Model.User;
import famu.edu.hotelr.Service.UserService;
import famu.edu.hotelr.Util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/User")
public class UserController {

    //@Autowired
    private UserService userService;

    public UserController(UserService userService){this.userService = userService;}

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", userService.getAllUsers(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", userService.getUserById(userId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createNewUsers(@RequestBody User user){
        try{
            return ResponseEntity.ok(new ApiResponse(true,"Success", userService.createUser(user),null));
        } catch (ExecutionException e){
            return ResponseEntity.status(401).body(new ApiResponse(false, "An error occured", null, e.getMessage()));
        } catch (InterruptedException e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred", null, e.getMessage()));
        }
    }
    @PutMapping("/{user}")
    public ResponseEntity<ApiResponse> updateUsers(@PathVariable String user,@RequestBody Map<String, String> j ){
        try{
            userService.updateUser(user,j);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }
    @DeleteMapping("/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userID){
        try{
            userService.deleteUser(userID);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }


}
