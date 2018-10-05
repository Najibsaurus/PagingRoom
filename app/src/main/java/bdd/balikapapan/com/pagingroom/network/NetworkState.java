package bdd.balikapapan.com.pagingroom.network;

public class NetworkState {

    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    private final Status status;
    private final String msg;

    public static final NetworkState LOADED;
    static final NetworkState LOADING;


    public NetworkState(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    static {
        LOADED = new NetworkState(Status.SUCCESS, "SUCCESS");
        LOADING = new NetworkState(Status.RUNNING, "RUNNING");
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public static NetworkState getLOADED() {
        return LOADED;
    }

    public static NetworkState getLOADING() {
        return LOADING;
    }
}