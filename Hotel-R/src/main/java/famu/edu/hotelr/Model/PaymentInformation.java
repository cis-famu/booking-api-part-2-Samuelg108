package famu.edu.hotelr.Model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.text.ParseException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInformation {


    @DocumentId
    private @Nullable String paymentID;
    private Timestamp paymentDate;
    private double amount;
    private String paymentMethod;
    private String transactionID;

    public void setPaymentDate(String paymentDate) throws ParseException {
        this.paymentDate = Timestamp.fromProto(Timestamps.parse(paymentDate));
    }

}
