package famu.edu.hotelr.controller;


import famu.edu.hotelr.Model.Room;
import famu.edu.hotelr.Service.RoomsService;
import famu.edu.hotelr.Util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/room")
public class RoomController {


    private RoomsService roomService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRoom() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", roomService.getAllRoom(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", e.getMessage(), null));
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ApiResponse> getRoomById(@PathVariable String roomId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", roomService.getRoomById(roomId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", e.getMessage(), null));
        }

    }
        @PostMapping
        public ResponseEntity<ApiResponse> createNewRoom(@RequestBody Room room){
            try{
                return ResponseEntity.ok(new ApiResponse(true,"Success", roomService.createRoom(room),null));
            } catch (ExecutionException e){
                return ResponseEntity.status(401).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
            } catch (InterruptedException e){
                return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred", null, e.getMessage()));
            }
        }
        @PutMapping("/{room}")
        public ResponseEntity<ApiResponse> updateRoom(@PathVariable String room,@RequestBody Map<String, String> i ){
            try{
                roomService.updateRoom(room,i);
                return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
            } catch (Exception e){
                return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
            }
        }
        @DeleteMapping("/{roomID}")
        public ResponseEntity<ApiResponse> deleteRoom(@PathVariable String roomID){
            try{
                roomService.deleteRoom(roomID);
                return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
            } catch (Exception e){
                return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
            }

        }

    }



