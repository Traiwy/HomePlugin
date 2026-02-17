package traiwy.homePlugin.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import traiwy.homePlugin.configuration.ErrorMessageConfiguration;
import traiwy.homePlugin.error.provider.CommandErrorMessageProvider;
import traiwy.homePlugin.error.provider.HomeErrorMessageProvider;
import traiwy.homePlugin.error.provider.RequestErrorMessageProvider;

@Getter
public class ErrorService {
    private final CommandErrorMessageProvider commandErrorMessageProvider;
    private final HomeErrorMessageProvider homeErrorMessageProvider;
    private final RequestErrorMessageProvider requestErrorMessageProvider;

    private final ErrorMessageConfiguration configuration;

    public ErrorService(ErrorMessageConfiguration configuration) {
        this.configuration = configuration;
        this.commandErrorMessageProvider = new CommandErrorMessageProvider(configuration);
        this.homeErrorMessageProvider = new HomeErrorMessageProvider(configuration);
        this.requestErrorMessageProvider = new RequestErrorMessageProvider(configuration);
    }
}
