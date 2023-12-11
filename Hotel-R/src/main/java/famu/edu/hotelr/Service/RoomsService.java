package famu.edu.hotelr.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import famu.edu.hotelr.Model.Room;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
@Service
public class RoomsService {

    private Firestore firestore;

    public void RoomService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    private Room documentSnapshotToRoom(DocumentSnapshot document) {
        Room room = null;
        if (document.exists()) {
            ArrayList<String> images = (ArrayList<String>) document.get("images");
            room = new Room(document.getId(), document.getString("hotelID"), document.getString("roomType"), document.getLong("price"),document.getLong("capacity"), document.getString("description"), document.getString("availability"), images, document.getTimestamp("createdAt"));
        }
        return room;
    }

    public ArrayList<Room> getAllRoom() throws ExecutionException, InterruptedException {
        CollectionReference roomCollection = firestore.collection("Room");
        ApiFuture<QuerySnapshot> future = roomCollection.get();
        ArrayList<Room> aircraftList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Room room = documentSnapshotToRoom(document);
            if (room != null) {
                aircraftList.add(room);
            }
        }
        return aircraftList;
    }

    public Room getRoomById(String roomId) throws ExecutionException, InterruptedException {
        CollectionReference aircraftCollection = firestore.collection("Room");
        ApiFuture<DocumentSnapshot> future = aircraftCollection.document(roomId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToRoom(document);
    }

    public String createRoom(Room room) throws ExecutionException, InterruptedException {
        String roomId = null;
        ApiFuture<DocumentReference> future = firestore.collection("Room").add(room);
        DocumentReference postRef = future.get();
        roomId = postRef.getId();
        return roomId;

    }

    public void updateRoom(String id, Map<String, String> updatedValues) {
        String[] allowed = {"roomType", "price", "description"};
        List<String> list = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for (Map.Entry<String, String> entry : updatedValues.entrySet()) {
            String key = entry.getKey();
            if (list.contains(key)) {
                formattedValues.put(key, entry.getValue());
            }
        };//

        DocumentReference roomDoc = firestore.collection("Room").document(id);
        if (roomDoc != null)
            roomDoc.update(formattedValues);
    }

    public void deleteRoom(String roomID) throws ExecutionException, InterruptedException {
        CollectionReference roomCollection = firestore.collection("Room");
        ApiFuture<DocumentSnapshot> future = roomCollection.document(roomID).get();
        DocumentSnapshot document = future.get();
        if (document.getId().equals(roomID)) {
            roomCollection.document(roomID).delete();
        }

    }

}
