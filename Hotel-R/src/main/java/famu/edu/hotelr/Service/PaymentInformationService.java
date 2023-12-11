package famu.edu.hotelr.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PaymentInformationService {
    private Firestore firestore;

    public PaymentInformationService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    public PaymentInformationService getPaymentInformation(DocumentReference docRef) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(PaymentInformationService.class);
    }
}
