package Model;

import java.util.Calendar;

public interface SubscriptionManager
{
    void subsDisplay();
    String getPaymentMethod();
    void extendSubscription(int months);

}
