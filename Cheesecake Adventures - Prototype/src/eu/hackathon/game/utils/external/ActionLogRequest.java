package eu.hackathon.game.utils.external;

final class ActionLogRequest {
    private String message;
    private int duration;

    ActionLogRequest(String message, int duration) {
        this.message = message;
        this.duration = duration;
    }

    public String message() {
        return message;
    }

    public int duration() {
        return duration;
    }
}
