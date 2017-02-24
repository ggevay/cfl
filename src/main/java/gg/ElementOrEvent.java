package gg;

//TODO: check what serializer is being used. If Kryo, then I should provide a custom one instead.

public class ElementOrEvent<T> {

	public short subPartitionId; // az input operator melyik physical instance-erol jott
	public T element;
	public Event event;

	public byte splitId; // Ementen kell majd splittelni a conditional outputokhoz

	public byte logicalInputId = -1;

	public ElementOrEvent() {}

	public ElementOrEvent(short subPartitionId, T element, byte splitId) {
		this.subPartitionId = subPartitionId;
		this.element = element;
		this.splitId = splitId;
	}

	public ElementOrEvent(short subPartitionId, Event event, byte splitId) {
		this.subPartitionId = subPartitionId;
		this.event = event;
		this.splitId = splitId;
	}

	// Bag start or end
	public static class Event {

		public enum Type {START, END}

		public Type type;
		public int cflSize;

		public Event() {}

		public Event(Type type, int cflSize) {
			this.type = type;
			this.cflSize = cflSize;
		}

		@Override
		public String toString() {
			return "Event{" +
					"type=" + type +
					", cflSize=" + cflSize +
					'}';
		}
	}

	@Override
	public String toString() {
		return "ElementOrEvent{" +
				"subPartitionId=" + subPartitionId +
				", element=" + element +
				", event=" + event +
				", splitId=" + splitId +
				", logicalInputId=" + logicalInputId +
				'}';
	}
}
