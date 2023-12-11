package famu.edu.hotelr.controller;

import famu.edu.hotelr.Model.Hotel;
import famu.edu.hotelr.Service.HotelService;
import famu.edu.hotelr.Util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllHotel() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelService.getAllHotel(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", e.getMessage(), null));
        }
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> getHotelById(@PathVariable String hotelId){
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelService.getHotelById(hotelId), null));
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred.", null, e.getMessage()));
        }

    }
    @PostMapping
    public ResponseEntity<ApiResponse> createNewHotel(@RequestBody Hotel hotel){
        try{
            return ResponseEntity.ok(new ApiResponse(true,"Success", hotelService.createHotel(hotel),null));
        } catch (ExecutionException e){
            return ResponseEntity.status(401).body(new ApiResponse(false, "An error occured", null, e.getMessage()));
        } catch (InterruptedException e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred", null, e.getMessage()));
        }
    }
    @PutMapping("/{hotels}")
    public ResponseEntity<ApiResponse> updateHotel(@PathVariable String hotels,@RequestBody Map<String, String> j ){
        try{
            hotelService.updateHotels(hotels,j);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }
    @DeleteMapping("/{hotelID}")
    public ResponseEntity<ApiResponse> deleteHotel(@PathVariable String hotelID){
        try{
            hotelService.deleteHotel(hotelID);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }


}
