package smartanalytics.diksha.com.smartanalytics.network;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static smartanalytics.diksha.com.smartanalytics.network.StringVollyRequest.ID_GET_BILL_DETAILS;


public class TypeTokenParam {
    public static Type getTypeToken(int id) {
        Type type = null;

        switch (id) {
            case ID_GET_BILL_DETAILS:
             //   type = new TypeToken<Demo>() {
              //  }.getType();
                break;

        }
        return type;
    }
}
