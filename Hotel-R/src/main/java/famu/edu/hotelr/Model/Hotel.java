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

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hotel {
    @DocumentId
    private @Nullable String hotelID;
    private String name;
    private String description;
    private double rating;
    private String address;
    private String contactInformation;
    private ArrayList<String> amenities;
    private @Nullable Timestamp createdAt;

    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }
}
