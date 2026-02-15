package JTime.Controllers.Handlers;

import javafx.event.ActionEvent;

/**
 * Base interface for all event handlers.
 * Defines a contract for handling UI events in a consistent manner.
 */
public interface IEventHandler {
    /**
     * Handles the triggered action event.
     * @param event The action event triggered by the UI component.
     */
    void handle(ActionEvent event);
}

