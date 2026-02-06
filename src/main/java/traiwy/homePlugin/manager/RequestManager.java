package traiwy.homePlugin.manager;

import lombok.Getter;
import traiwy.homePlugin.share.Request;

import java.util.HashSet;
import java.util.Set;

@Getter
public class RequestManager {
    private final Set<Request> requests = new HashSet<>();

    public void  addRequest(Request request) {
        requests.add(request);
    }
    public void  removeRequest(Request request) {
        requests.remove(request);
    }
    public boolean hasRequest(Request request) {
        return requests.contains(request);
    }

}
