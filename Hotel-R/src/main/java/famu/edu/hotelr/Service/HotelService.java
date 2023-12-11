package famu.edu.hotelr.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import famu.edu.hotelr.Model.Hotel;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class HotelService {

    private Firestore firestore;

    public HotelService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    private Hotel documentSnapshotToHotel(DocumentSnapshot document) {
        Hotel hotel = null;
        if(document.exists()){
            ArrayList<String> amenities = (ArrayList<String>) document.get("amenities");
            hotel = new Hotel(document.getId(), document.getString("name"), document.getString("description"), document.getDouble("rating"), document.getString("address"), document.getString("contactInformation"),amenities,document.getTimestamp("createdAt"));
        }
        return hotel;

    }

    public List<Hotel> getAllHotel() throws ExecutionException, InterruptedException {
        CollectionReference hotelCollection = firestore.collection("Hotel");
        ApiFuture<QuerySnapshot> future = hotelCollection.get();
        List<Hotel> hoteltList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Hotel hotel = documentSnapshotToHotel(document);
            if (hotel != null) {
                hoteltList.add(hotel);
            }
        }
        return hoteltList;
    }

    public Hotel getHotelById(String hotelCode) throws ExecutionException, InterruptedException {
        CollectionReference hotelCollection = firestore.collection("Hotel");
        ApiFuture<DocumentSnapshot> future = hotelCollection.document(hotelCode).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToHotel(document);
    }

    public String createHotel(Hotel hotel) throws ExecutionException, InterruptedException {
        String hotelId = null;
        ApiFuture<DocumentReference> future = firestore.collection("Hotel").add(hotel);
        DocumentReference postRef = future.get();
        hotelId = postRef.getId();
        return hotelId;

    }
    public void updateHotels(String id, Map<String, String> updatedValues){
        String[] allowed = {"name", "description", "address"};
        List<String> list = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, String> entry : updatedValues.entrySet())
        {
            String key = entry.getKey();
            if(list.contains(key))
            {
                formattedValues.put(key, entry.getValue());
            }
        }
        DocumentReference hotelDoc = firestore.collection("Hotel").document(id);
        hotelDoc.update(formattedValues);
    }
    public void deleteHotel(String hotelID) throws ExecutionException, InterruptedException {
        CollectionReference hotelCollection = firestore.collection("User");
        ApiFuture<DocumentSnapshot> future = hotelCollection.document(hotelID).get();
        DocumentSnapshot document = future.get();
        if (document.getId().equals(hotelID)) {
            hotelCollection.document(hotelID).delete();
        }

    }

    public void returnRoom(String id, String roomType, int i) {
    }

}
