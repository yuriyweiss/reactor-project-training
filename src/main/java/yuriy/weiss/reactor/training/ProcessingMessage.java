package yuriy.weiss.reactor.training;

public record ProcessingMessage(String id, long processingTime) {
    @Override
    public String toString() {
        return "ProcessingMessage{" +
                "id='" + id + '\'' +
                ", processingTime=" + processingTime +
                " ms}";
    }
}
