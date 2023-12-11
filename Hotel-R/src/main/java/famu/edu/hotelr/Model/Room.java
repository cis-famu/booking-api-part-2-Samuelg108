package famu.edu.hotelr.Model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @DocumentId
    private	@Nullable String roomID;
    private	String hotelID;
    private String roomType;
    private long price;
    private long capacity;
    private String	description;
    private String	availability;
    private ArrayList<String> images;
    private Timestamp createdAt;


    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }
    public Room(String id, String hotelID, String roomType, Long price, Long capacity, String description, String availability, ArrayList<String> images, Timestamp createdAt) {
    }
}
