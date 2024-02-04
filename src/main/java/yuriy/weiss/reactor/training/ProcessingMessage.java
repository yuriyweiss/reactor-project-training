package yuriy.weiss.reactor.training;

public record ProcessingMessage(int orderNumber, String id, long processingTime) {
    @Override
    public String toString() {
        return "ProcessingMessage{" +
                "orderNumber=" + orderNumber +
                ", id='" + id + '\'' +
                ", processingTime=" + processingTime + " ms" +
                '}';
    }
}
