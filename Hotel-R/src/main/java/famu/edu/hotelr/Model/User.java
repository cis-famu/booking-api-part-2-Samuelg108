package famu.edu.hotelr.Model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import famu.edu.hotelr.Service.PaymentInformationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.text.ParseException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

        @DocumentId
        private @Nullable String userID;
        private String name;
        private String email;
        private String phone;
        private String address;
        private @Nullable PaymentInformation paymentInformation;
        private @Nullable Timestamp createdAt;

        public User(String id, String name, String email, String phone, PaymentInformationService paymentInformation, Timestamp createdAt) {
        }

        public void setCreatedAt(String createdAt) throws ParseException {
                this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
        }
}
