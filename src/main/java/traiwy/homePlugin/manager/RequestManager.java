package traiwy.homePlugin.manager;

import lombok.Getter;
import org.bukkit.entity.Player;
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

    public Request getRequestFor(Player receiver) {
        for (Request r : requests) {
            if (r.receiver().equals(receiver)) {
                return r;
            }
        }
        return null;
    }

}
