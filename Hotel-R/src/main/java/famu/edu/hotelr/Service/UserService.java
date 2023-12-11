package famu.edu.hotelr.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import famu.edu.hotelr.Model.PaymentInformation;
import famu.edu.hotelr.Model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private Firestore firestore;

    public UserService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    private User documentSnapshotToUser(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        User users = null;
        if (document.exists()) {
            PaymentInformationService paymentInformationService = new PaymentInformationService();
            PaymentInformationService paymentInformation = paymentInformationService.getPaymentInformation((DocumentReference) document.get("paymentInformation"));
            users = new User(document.getId(), document.getString("name"), document.getString("email"), document.getString("phone"), paymentInformation, document.getTimestamp("createdAt"));
        }
        return users;
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("User");
        ApiFuture<QuerySnapshot> future = userCollection.get();
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            User user = documentSnapshotToUser(document);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    public User getUserById(String userId) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("User");
        ApiFuture<DocumentSnapshot> future = userCollection.document(userId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUser(document);
    }

    public ArrayList<User> getUsersBycreatedAtAndSort(String createdAt) throws ExecutionException, InterruptedException {
        ArrayList<User> userss = null;

        DocumentReference usersRef = firestore.collection("Users").document(createdAt);

        CollectionReference usersCollection = firestore.collection("Users");
        Query query = usersCollection.whereEqualTo("createdAt", usersRef)
                .orderBy("createdAt", Query.Direction.DESCENDING);

        ApiFuture<QuerySnapshot> future = query.get();

        for(QueryDocumentSnapshot document :  future.get().getDocuments())
        {
            User user = documentSnapshotToUser(document);
            if(user != null)
                userss.add(user);

        }
        return userss;
    }
    public String createUser(User user) throws ExecutionException, InterruptedException {
        String userId = null;
        ApiFuture<DocumentReference> future = firestore.collection("User").add(user);
        DocumentReference postRef = future.get();
        userId = postRef.getId();
        return userId;

    }
    public void updateUser(String id, Map<String, String> updatedValues){
        String[] allowed = {"name", "email", "address"};
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
        DocumentReference userDoc = firestore.collection("User").document(id);
        if(userDoc != null)
            userDoc.update(formattedValues);
    }
    public void deleteUser(String roomID) throws ExecutionException, InterruptedException {
        CollectionReference userssCollection = firestore.collection("User");
        ApiFuture<DocumentSnapshot> future = userssCollection.document(roomID).get();
        DocumentSnapshot document = future.get();
        if(document.getId().equals(roomID)){
            userssCollection.document(roomID).delete();
        }
    }


}
