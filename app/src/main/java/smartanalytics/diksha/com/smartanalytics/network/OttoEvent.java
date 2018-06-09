package smartanalytics.diksha.com.smartanalytics.network;

public class OttoEvent {

    private int id;
    private String exception;
 //   private Demo response;

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   /* public Demo getResponse() {
        return response;
    }

    public void setResponse(Demo response) {
        this.response = response;
    }*/

    @Override
    public String toString() {
        return "OttoEvent{" +
                "id=" + id +
                ", exception='" + exception + '\'' +

                '}';
    }
}
